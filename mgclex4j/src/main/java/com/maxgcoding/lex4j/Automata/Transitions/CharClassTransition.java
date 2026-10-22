package com.maxgcoding.lex4j.Automata.Transitions;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class CharClassTransition implements Transition {
    private AutomataState destination;
    private String label;
    public CharClassTransition(String label, AutomataState dest) {
        this.label = label; this.destination = dest;
    } 
    public AutomataState getDestination() {
        return destination;
    }
    public Boolean match(Character ch) {
        char[] raw = label.toCharArray();
        for (int i = 0; i < raw.length; i++) {
            Character c = raw[i];
            if (i+2 < raw.length && raw[i+1] == '-') {
                return ch.compareTo(c) >= 0 && ch.compareTo(raw[i+2]) <= 0;
            } else if (c.equals(ch)) {
                return true;
            }
        }
        return false;
    }
    public String edgeLabel() {
        return label;
    }
}
