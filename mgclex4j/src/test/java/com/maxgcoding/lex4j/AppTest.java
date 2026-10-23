package com.maxgcoding.lex4j;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.maxgcoding.lex4j.Analyzer.Lexer;
import com.maxgcoding.lex4j.Analyzer.MatchResult;
import com.maxgcoding.lex4j.Analyzer.Token;
import com.maxgcoding.lex4j.Generator.LexGen;
import com.maxgcoding.lex4j.Input.TokenPatternRule;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        List<TokenPatternRule> rules = List.of(
                                          new TokenPatternRule("([A-Za-z][A-Za-z0-9]*)", Token.define("ID")),
                                          new TokenPatternRule("([0-9]+)", Token.define("NUMBER")), 
                                          new TokenPatternRule("if", Token.define("IF")), 
                                          new TokenPatternRule("print", Token.define("PRINT")), 
                                          new TokenPatternRule("[+]",Token.define("PLUS")), 
                                          new TokenPatternRule("[(]",Token.define("LPAR")), 
                                          new TokenPatternRule("[)]",Token.define("RPAR")), 
                                          new TokenPatternRule("<",Token.define("LESS")) );
        LexGen gen = new LexGen(Boolean.FALSE);
        Lexer lexer = gen.generate(rules);
        String ex = "if (1 + 1 < 5) print 42";
        List<MatchResult> tokens = lexer.lex(ex);
        assertEquals(tokens.size(), 10);
    }
}
