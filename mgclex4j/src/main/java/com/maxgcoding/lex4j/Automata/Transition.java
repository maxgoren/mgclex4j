package com.maxgcoding.lex4j.Automata;

public interface Transition {
    AutomataState getDestination();
    Boolean match(Character ch);
    String edgeLabel();
}
