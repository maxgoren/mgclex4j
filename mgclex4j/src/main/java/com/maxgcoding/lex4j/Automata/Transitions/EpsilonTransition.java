package com.maxgcoding.lex4j.Automata.Transitions;

import com.maxgcoding.lex4j.Automata.AutomataState;
import com.maxgcoding.lex4j.Automata.Transition;

public class EpsilonTransition implements Transition {
    private AutomataState destination;
    public EpsilonTransition(AutomataState dest) {
        this.destination = dest;
    } 
    public AutomataState getDestination() {
        return destination;
    }
    public Boolean match(Character ch) {
        return false;
    }
    public Character edgeLabel() {
        return '&';
    }
}
