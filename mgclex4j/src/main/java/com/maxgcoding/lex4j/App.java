package com.maxgcoding.lex4j;

import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Generator.LexGen;
import com.maxgcoding.lex4j.Match.DFAMatcher;

public class App {
    
    public static void main(String[] args) {
        String expr = "((a*b|ac)d)";
        String text = "aaaaaaaaaabdoo";
        LexGen gen = new LexGen();
        DFA dfa = gen.generate(expr);
        DFAMatcher dm = new DFAMatcher();
        if (dm.match(dfa, text)) {
            System.out.println("Matched.");
        }
    }
}
