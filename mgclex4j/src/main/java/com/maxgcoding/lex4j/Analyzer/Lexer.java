package com.maxgcoding.lex4j.Analyzer;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.MatchResult;
import com.maxgcoding.lex4j.Input.TokenPatternRule;

public class Lexer {
    private final DFA dfa;
    private List<TokenPatternRule> rules;
    public Lexer(DFA dfa, List<TokenPatternRule> rules) {
        this.dfa = dfa;
        this.rules = rules;
    }
    public List<MatchResult> lex(String input) {
        ArrayList<MatchResult> results = new ArrayList<>(); 
        for (int i = 0; i < input.length(); i++) {
            while (input.charAt(i) == ' ') i++;
            MatchResult mr = dfa.match(input.substring(i));
            if (mr != null) {
                i += mr.getMatch().length()-1;
                results.add(mr);
            }
        }
        return results;
    }
}
