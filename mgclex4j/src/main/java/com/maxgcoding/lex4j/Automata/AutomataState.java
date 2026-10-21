package com.maxgcoding.lex4j.Automata;

import java.util.List;

public interface AutomataState {
    List<Transition> getTrans();
    int getLabel();
}
