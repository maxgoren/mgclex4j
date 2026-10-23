package com.maxgcoding.lex4j.Automata.States;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class DFAState implements AutomataState {
    private Set<AutomataState> nfastates;
    private int label;
    private Map<Character, Transition> transitions;
    private int tokenNumber;
    private boolean acceptState;
    public DFAState(int label) {
        this.label = label;
        this.nfastates = new HashSet<>();
        this.acceptState = false;
        transitions = new HashMap<>();
    }
    public int getLabel() {
        return label;
    }
    public Set<AutomataState> getStates() {
        return nfastates;
    }
    public List<Transition> getTrans() {
        return transitions.entrySet().stream().map(es -> es.getValue()).toList();
    }
    public void addTransition(Transition tr) {
        transitions.put(tr.edgeLabel(), tr);
    }
    public DFAState transOn(Character ch) {
        Transition t = transitions.get(ch);
        if (t != null && t.match(ch)) {
            return (DFAState) t.getDestination();
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
