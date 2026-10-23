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
                                          new TokenPatternRule("[a-zA-Z]([a-z]|[0-9])*", "ID", 1),
                                          new TokenPatternRule("([0-9]+)", "NUMBER", 2),
                                          new TokenPatternRule("if", "IF", 3), 
                                          new TokenPatternRule("print", "PRINT", 4), 
                                          new TokenPatternRule("def", "DEF", 5),
                                          new TokenPatternRule("while", "WHILE", 6),
                                          new TokenPatternRule("var", "VAR", 7),
                                          new TokenPatternRule("return","RETURN", 8),
                                          new TokenPatternRule("else", "ELSE", 9),
                                          new TokenPatternRule("[(]","LPAR", 10), 
                                          new TokenPatternRule("[)]","RPAR", 11),
                                          new TokenPatternRule("{", "LCURLY", 12),
                                          new TokenPatternRule("}", "RCURLY",13),
                                          new TokenPatternRule("[+]","PLUS", 14), 
                                          new TokenPatternRule("[*]", "MULT",15),
                                          new TokenPatternRule("[-]", "MINUS", 16),
                                          new TokenPatternRule("[/]","DIV", 17),
                                          new TokenPatternRule("<","LESS",18),
                                          new TokenPatternRule(";","SEMI",19),
                                          new TokenPatternRule(":=", "ASSIGN", 20) );
        LexGen gen = new LexGen(Boolean.FALSE);
        Lexer lexer = gen.generate(rules);
        String ex = "if (1 + 1 < 5) { var qq := 42; print qq; } else { var yy := 1; while (yy < 10) { print yy; yy := yy * 2; } }";
        List<MatchResult> tokens = lexer.lex(ex);
        for (MatchResult mr : tokens) {
            System.out.println(mr.getMatch() + " matched by " + rules.get(mr.tokenNumber()-1).getToken());
        }
    }
}
