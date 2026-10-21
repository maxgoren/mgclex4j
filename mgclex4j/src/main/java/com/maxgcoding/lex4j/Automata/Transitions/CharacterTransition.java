package com.maxgcoding.lex4j.Automata.Transitions;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class CharacterTransition implements Transition {
    private AutomataState destination;
    private Character label;
    public CharacterTransition(Character edgeLabel, AutomataState dest) {
        this.label = edgeLabel; this.destination = dest;
    } 
    public Character getLabel() {
        return label;
    }
    public AutomataState getDestination() {
        return destination;
    }
    public Boolean match(Character ch) {
        return label.equals(ch);
    }
    public String edgeLabel() {
        return label.toString();
    }
}
