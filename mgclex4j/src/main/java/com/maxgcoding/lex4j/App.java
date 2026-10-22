package com.maxgcoding.lex4j;

import java.util.List;

import com.maxgcoding.lex4j.Analyzer.Lexer;
import com.maxgcoding.lex4j.Automata.MatchResult;
import com.maxgcoding.lex4j.Generator.LexGen;
import com.maxgcoding.lex4j.Input.TokenPatternRule;

public class App {
    /* A simple example of generating/using Lexer with MGCLex4j */
    public static void main(String[] args) {

        List<TokenPatternRule> rules = List.of(
                                          new TokenPatternRule("([A-Za-z][A-Za-z0-9]*)", "ID", 1),
                                          new TokenPatternRule("([0-9]+)", "NUMBER", 2), 
                                          new TokenPatternRule("if", "IF", 3), 
                                          new TokenPatternRule("print", "PRINT", 4), 
                                          new TokenPatternRule("[+]","PLUS", 5), 
                                          new TokenPatternRule("[(]","LPAR", 6), 
                                          new TokenPatternRule("[)]","RPAR", 7), 
                                          new TokenPatternRule("<","LESS",8) );
        LexGen gen = new LexGen();
        Lexer lexer = gen.generate(rules);
        String ex = "if (1 + 1 < 5) print 42";
        List<MatchResult> tokens = lexer.lex(ex);
        for (MatchResult mr : tokens) {
            System.out.println(mr.getMatch() + " matched by " + rules.get(mr.tokenNumber()-1).getToken());

        }
    }
}
