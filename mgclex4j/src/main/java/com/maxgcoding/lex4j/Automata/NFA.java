package com.maxgcoding.lex4j.Automata;

import com.maxgcoding.lex4j.Automata.States.NFAState;

public class NFA {
    private NFAState start;
    private NFAState accept;
    private int token_number;
    public NFA(NFAState start, NFAState accept) {
        this.start = start;
        this.accept = accept;
    }
    public void setStart(NFAState st) {
        this.start = st;
    }
    public void setAccept(NFAState ac) {
        this.accept = ac;
    }
    public NFAState getStart() {
        return this.start;
    }
    public NFAState getAccept() {
        return this.accept;
    }
    public int getTokenNumber() {
        return token_number;
    }
    public void setTokenNumber(int tn) {
        token_number = tn;
    }
}
