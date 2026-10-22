package com.maxgcoding.lex4j.Automata;

import java.util.List;

public interface AutomataState {
    int getLabel();
    int getToken();
    boolean isAccept();
    List<Transition> getTrans();
}
