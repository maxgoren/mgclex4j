package com.maxgcoding.lex4j.Automata;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.maxgcoding.lex4j.Automata.Transitions.CharClassTransition;
import com.maxgcoding.lex4j.Automata.Transitions.CharacterTransition;
import com.maxgcoding.lex4j.Automata.Transitions.EpsilonTransition;

public class Closure {
    public Closure() {

    }
    public String match(NFA nfa, String text) {
        Set<AutomataState> next = e_closure(Set.of(nfa.getStart()));
        String matchstring = null;
        int i = 0;
        for (Character c : text.toCharArray()) {
            next = move(c, next);
            next = e_closure(next);
            if (next.contains(nfa.getAccept())) {
                matchstring = text.substring(0, i+1);
            }
            if (next.isEmpty()) {
                return matchstring;
            }
            i++;
        }
        return matchstring;
    }

    public Set<AutomataState> e_closure(Set<AutomataState> states) {
        Set<AutomataState> next = new HashSet<>();
        Stack<AutomataState> st = new Stack<>();
        states.forEach(state -> {next.add(state); st.add(state); });
        while (!st.isEmpty()) {
            AutomataState currState = st.pop();
            for (Transition trans : currState.getTrans()) {
                switch (trans) {
                    case EpsilonTransition ct -> {
                        if (!next.contains(ct.getDestination())) {
                            //System.out.println(currState.getLabel() + "-(" + ct.edgeLabel() + ")-> " + ct.getDestination().getLabel());
                            next.add(ct.getDestination());
                            st.push(ct.getDestination());
                        }
                    }
                    case CharacterTransition _ -> { }
                    case CharClassTransition _ -> { }
                    default -> { }
                }
            }
        }
        return next;
    }


    public Set<AutomataState> move(Character c, Set<AutomataState> states) {
        Set<AutomataState> next = new HashSet<>();
        for (AutomataState state : states) {
            for (Transition trans : state.getTrans()) {
                switch (trans) {
                    case EpsilonTransition _ -> { }
                    case CharacterTransition ct -> {
                        if (ct.match(c) && !next.contains(ct.getDestination())) {
                            //System.out.println(state.getLabel() + "-(" + ct.edgeLabel() + ")-> " + ct.getDestination().getLabel());
                            next.add(ct.getDestination());
                        }
                     }
                    case CharClassTransition ct -> {
                        if (ct.match(c) && !next.contains(ct.getDestination())) {
                            //System.out.println(state.getLabel() + "-(" + ct.edgeLabel() + ")-> " + ct.getDestination().getLabel());
                            next.add(ct.getDestination());
                        }
                    }
                    default -> { }
                }
            }
        }
        return next;
    }
}
