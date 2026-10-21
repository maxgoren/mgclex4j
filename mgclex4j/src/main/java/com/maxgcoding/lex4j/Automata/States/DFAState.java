package com.maxgcoding.lex4j.Automata.States;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class DFAState implements AutomataState {
    private Set<AutomataState> nfastates;
    private boolean accepts;
    private int label;
    private List<Transition> transitions;
    public DFAState(int label) {
        this.label = label;
        this.nfastates = new HashSet<>();
        transitions = new ArrayList<>();
    }
    public int getLabel() {
        return label;
    }
    public void setAccept(Boolean accept) {
        this.accepts = accept;
    }
    public Boolean getAccepts() {
        return accepts;
    }
    public Set<AutomataState> getStates() {
        return nfastates;
    }
    public List<Transition> getTrans() {
        return transitions;
    }
    public void addTransition(Transition tr) {
        transitions.add(tr);
    }
    public DFAState transOn(Character ch) {
        for (Transition t : transitions) {
            if (t.match(ch)) {
                System.out.println("Transition on " + t.edgeLabel());
                return (DFAState) t.getDestination();
            }
        }
        return null;
    }
}
