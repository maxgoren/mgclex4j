package com.maxgcoding.lex4j.Automata.States;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class NFAState implements AutomataState {
    private int label;
    private List<Transition> transitions;
    public NFAState(int label) {
        this.label = label;
        transitions = new ArrayList<>();
    }
    public int getLabel() {
        return label;
    }
    public List<Transition> getTrans() {
        return transitions;
    }
    public void addTransition(Transition t) {
        transitions.add(t);
    }
    public int compareTo(NFAState other) {
        return label - other.label;
    }
}
