package com.maxgcoding.lex4j.Automata.compile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collector;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Closure;
import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.NFA;
import com.maxgcoding.lex4j.Automata.States.DFAState;
import com.maxgcoding.lex4j.Automata.States.NFAState;
import com.maxgcoding.lex4j.Automata.Transitions.CharacterTransition;

public class DFABuilder {
    
    public DFABuilder() {

    }
    
    private DFAState findStatByPositions(DFA dfa, Set<AutomataState> positions) {
        for (DFAState state : dfa.getStates()) {
            if (state.getStates().equals(positions)) {
                return state;
            }
        }
        return null;
    }
    
    public DFA fromNFA(NFA nfa, String expr) {
        Set<Character> alpha = buildAlphabet(expr);
        DFA result = new DFA();
        Closure closure = new Closure();
        Set<AutomataState> startSet = closure.e_closure(Set.of(nfa.getStart()));
        Queue<DFAState> fq = new LinkedList<>();
        DFAState s = new DFAState(result.getStates().size());
        s.getStates().addAll(startSet);
        result.getStates().add(s);
        fq.add(s);
        while (!fq.isEmpty()) {
            s = fq.remove();
            for (Character ch : alpha) {
                Set<AutomataState> next = closure.move(ch, s.getStates());
                if (!next.isEmpty()) {
                    next = closure.e_closure(next);
                    DFAState p = findStatByPositions(result, next);
                    if (p == null) {
                        p = new DFAState(result.getStates().size());
                        p.getStates().addAll(next);
                        result.getStates().add(p);
                        fq.add(p);
                        s.addTransition(new CharacterTransition(ch, p));
                    } else {
                        s.addTransition(new CharacterTransition(ch, p));
                    }
                }
            }
        }
        for (DFAState state : result.getStates()) {
           for (AutomataState nfs : state.getStates()) {
                NFAState nf = (NFAState)nfs;
                if (nf.getToken() > 0) {
                    System.out.println("Marked " + state.getLabel() + " as accepting token " + nf.getToken());
                    state.setAccept(Boolean.TRUE);
                    state.setToken(nf.getToken());
                    break;
                }
            }
        }
        return minimize(result,alpha);
    }

    DFA minimize(DFA dfa, Set<Character> alphabet) {
        DFA result = new DFA();
        Map<Integer,Integer> inGroup = new HashMap<>();
        List<Set<Integer>> groups = initGroups(dfa, inGroup);
        boolean didchange = true;
        while (didchange) {
            didchange = false;
            for (Set<Integer> group : groups) {
                Set<Integer> ng = new HashSet<>();
                var iter = group.iterator();
                Integer first = iter.next();
                while (iter.hasNext()) {
                    Integer next = iter.next();
                    for (char c : alphabet) {
                        DFAState stf = dfa.getStates().get(first).transOn(c);
                        DFAState stn = dfa.getStates().get(next).transOn(c);
                        if (stf != null && stn != null) {
                            if (!inGroup.get(stf.getLabel()).equals(inGroup.get(stn.getLabel()))) {
                                ng.add(next);
                            }
                        }
                    }
                }
                if (!ng.isEmpty()) {
                    group.removeAll(ng);
                    for (int s : ng) inGroup.put(s, groups.size());
                    groups.add(ng);
                    didchange = true;
                    break; 
                } else {
                    didchange = false;
                }
            }
        }
        for (int i = 0; i < groups.size(); i++) {
            result.getStates().add(new DFAState(i));
            result.getStates().get(i).setAccept(false);
        }
        for (int i = 0; i < groups.size(); i++) {
            int rep = groups.get(i).stream().limit(1).toList().getFirst();
            for (char c : alphabet) {
                DFAState state = dfa.getStates().get(rep).transOn(c);
                if (state != null) {
                    result.getStates().get(i).addTransition(new CharacterTransition(c, state));
                }
            }
            for (int os : groups.get(i)) {
                if (dfa.getStates().get(os).isAccept()) {
                    result.getStates().get(i).setAccept(true);
                    result.getStates().get(i).setToken(dfa.getStates().get(os).getToken());
                    System.out.println("And updated " + result.getStates().get(i).getLabel() + " as accepting token " + dfa.getStates().get(os).getToken());
                    break;
                }
            }
        }
        return result;
    }
    public List<Set<Integer>> initGroups(DFA dfa, Map<Integer,Integer> inGroup) {
        List<Set<Integer>> groups = new ArrayList<>();
        groups.add(new HashSet<>());
        for (DFAState state : dfa.getStates()) {
            if (state.isAccept()) {
                groups.add(new HashSet<>());
                groups.getLast().add(state.getLabel());
                inGroup.put(state.getLabel(), groups.size()-1);
            } else {
                groups.get(0).add(state.getLabel());
                inGroup.put(state.getLabel(), 0);
            }
        }
        return groups;
    }
    
    public static Set<Character> buildAlphabet(String expr) {
        HashSet<Character> alpha = new HashSet<>();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '\\') {
                alpha.add(expr.charAt(i));
                i++;
            } else if (expr.charAt(i) == '[') {
                i++;
                while (expr.charAt(i) != ']') {
                    if (i+1 < expr.length() && expr.charAt(i+1) == '-') {
                        char lo = expr.charAt(i);
                        char hi = expr.charAt(i+2);
                        for (char c = lo; c <= hi; c++) {
                            alpha.add(c);
                        }
                        i += 2;    
                    } else {
                        alpha.add(expr.charAt(i));
                        i++;
                    }
                }
            } else {
                if (expr.charAt(i) == '.') {
                    for (Character c = (char)28; c < 127; c++) {
                        alpha.add(c);
                    }
                } else if (expr.charAt(i) != '(' && expr.charAt(i) != ')' && expr.charAt(i) != '+' && expr.charAt(i) != '*' &&  expr.charAt(i) != '|'  && expr.charAt(i) != '?') {
                    alpha.add(expr.charAt(i));
                }
            }
        }
        return alpha;
    }
}
