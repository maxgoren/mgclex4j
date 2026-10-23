package com.maxgcoding;

import java.util.Iterator;
import java.util.List;

import com.maxgcoding.lex4j.Automata.MatchResult;

public class RDParser {
    private List<MatchResult> tokens;
    private Iterator<MatchResult> iter;
    MatchResult current;
    private MatchResult lookahead() {
        return current;
    }
    private void advance() {
        if (iter.hasNext()) {
            current = iter.next();
        }
    }
    private boolean expect(String str) {
        return current.getMatch().equals(str);
    } 
    private boolean match(String str) {
        if (expect(str)) {
            advance();
            return true;
        }
        advance();
        return false;
    }
    private RDParser() {

    }
    public SyntaxNode parse(List<MatchResult> tokens) {
        this.tokens = tokens;
        this.iter = this.tokens.iterator();
        SyntaxNode root = null; //statement();
        return root;
    }
    /* 
    private SyntaxNode statement() {
        switch (lookahead().tokenNumber()) {
             case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
        }
    }
    private SyntaxNode expression() {
        
    }*/
}
