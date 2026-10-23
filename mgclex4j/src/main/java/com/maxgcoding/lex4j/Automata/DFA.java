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
    public MatchResult match(String text) {
        DFAState s = getStart();
        String matched = null;
        int matchedBy = -1;
        for (int i = 0; i < text.length(); i++) {
            s = s.transOn(text.charAt(i));
            if (s == null) {
                break;
            } else if (s.isAccept()) {
                matched = text.substring(0, i+1);
                matchedBy = s.getToken();
            }
        }
        if (matched == null)
            return null;
        return new MatchResult(matched, matchedBy);
    }
}
