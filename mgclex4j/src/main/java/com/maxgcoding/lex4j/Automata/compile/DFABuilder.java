package com.maxgcoding.lex4j.Automata.compile;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
                if (nfa.getAccept().equals(nf)) {
                    System.out.println("Marked " + state.getLabel() + " as accepting.");
                    state.setAccept(Boolean.TRUE);
                }
            }
        }
        return result;
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
