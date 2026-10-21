package com.maxgcoding.lex4j.Input;

public class TokenPatternRule {
    private final String pattern;
    private final String token;
    private final int number;
    public TokenPatternRule(String pattern, String token, int number) {
        this.pattern = pattern; 
        this.token = token; 
        this.number = number;
    }
    public String getPattern() {
        return pattern;
    }
    public String getToken() {
        return token;
    }
    public int getNumber() {
        return number;
    }
}
