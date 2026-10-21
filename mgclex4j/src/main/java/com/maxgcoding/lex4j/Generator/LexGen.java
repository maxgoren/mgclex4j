package com.maxgcoding.lex4j.Generator;

import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.NFA;
import com.maxgcoding.lex4j.Automata.compile.DFABuilder;
import com.maxgcoding.lex4j.Automata.compile.NFABuilder;
import com.maxgcoding.lex4j.Automata.compile.parse.AST;
import com.maxgcoding.lex4j.Automata.compile.parse.Parser;
import com.maxgcoding.lex4j.Automata.compile.parse.PrettyPrint;

public class LexGen {
    private Parser parser;
    private NFABuilder nfaBuilder;
    private DFABuilder dfaBuilder;
    public LexGen() {
        parser = new Parser();
        nfaBuilder = new NFABuilder();
        dfaBuilder = new DFABuilder();
    }

    public DFA generate(String expr) {
        return buildDFA(buildNFA(expr), expr);
    }
    
    public NFA buildNFA(String expr) {
        AST ast = parser.parse(expr);
        PrettyPrint.traverse(ast);
        return nfaBuilder.build(ast);
    }

    public DFA buildDFA(NFA nfa, String expr) {
        return dfaBuilder.fromNFA(nfa, expr);
    }
}