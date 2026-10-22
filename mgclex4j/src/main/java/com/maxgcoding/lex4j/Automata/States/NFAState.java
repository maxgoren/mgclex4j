package com.maxgcoding.lex4j.Automata.States;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class NFAState implements AutomataState {
    private int label;
    private int tokenNumber;
    private boolean acceptState;
    private List<Transition> transitions;
    public NFAState(int label) {
        this.label = label;
        this.acceptState = false;
        this.tokenNumber = 0;
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
