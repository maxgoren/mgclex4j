package com.maxgcoding.lex4j.Input;

import com.maxgcoding.lex4j.Analyzer.Token;

public class TokenPatternRule {
    private final String pattern;
    private final Token token;
    public TokenPatternRule(String pattern, Token token) {
        this.pattern = pattern; 
        this.token = token; 
    }
    public String getPattern() {
        return pattern;
    }
    public Token getToken() {
        return token;
    }
}
