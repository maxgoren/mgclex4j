package com.maxgcoding.lex4j.Match;

import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.States.DFAState;

public class DFAMatcher {
    public Boolean match(DFA dfa, String text) {
        DFAState s = dfa.getStart();
        for (int i = 0; i < text.length(); i++) {
            s = s.transOn(text.charAt(i));
            if (s == null) {
                break;
            } else if (s.getAccepts()) {
                return true;
            }
        }
        return false;
    }
}
