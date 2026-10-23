package com.maxgcoding.lex4j.Analyzer;

public class MatchResult {
    private final String text;
    private final Token acceptedBy;
    public MatchResult(String text, Token token) {
        this.text = text;
        this.acceptedBy = token;
    }
    public Token getToken() {
        return acceptedBy;
    }
    public String getMatch() {
        return text;
    }
}
