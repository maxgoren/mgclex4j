package com.maxgcoding.lex4j.Automata;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Automata.States.DFAState;

public class DFA {
    private ArrayList<DFAState> states;
    public DFA() {
        states = new ArrayList<>();
    }
    
    public List<DFAState> getStates() {
        return states;
    }
    public DFAState getStart() {
        return states.get(0);
    }
}
