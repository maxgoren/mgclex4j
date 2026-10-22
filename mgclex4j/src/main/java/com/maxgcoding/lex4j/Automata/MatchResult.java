package com.maxgcoding.lex4j.Automata;

public class MatchResult {
    private final String text;
    private final int acceptedBy;
    public MatchResult(String text, int token) {
        this.text = text;
        this.acceptedBy = token;
    }
    public int tokenNumber() {
        return acceptedBy;
    }
    public String getMatch() {
        return text;
    }
}
