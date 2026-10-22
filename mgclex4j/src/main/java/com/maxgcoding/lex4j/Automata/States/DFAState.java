package com.maxgcoding.lex4j.Automata.States;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class DFAState implements AutomataState {
    private Set<AutomataState> nfastates;
    private int label;
    private List<Transition> transitions;
    private int tokenNumber;
    private boolean acceptState;
    public DFAState(int label) {
        this.label = label;
        this.nfastates = new HashSet<>();
        this.acceptState = false;
        transitions = new ArrayList<>();
    }
    public int getLabel() {
        return label;
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
                return (DFAState) t.getDestination();
            }
        }
        return null;
    }
    public void setAccept(Boolean accept) {
        acceptState = accept;
    }
    public boolean isAccept() {
        return acceptState;
    }
    public void setToken(int tn) {
        tokenNumber = tn;
    }
    public int getToken() {
        return tokenNumber;
    }
}
