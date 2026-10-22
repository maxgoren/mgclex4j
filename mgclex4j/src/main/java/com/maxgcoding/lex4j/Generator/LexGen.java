package com.maxgcoding.lex4j.Generator;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Analyzer.Lexer;
import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.NFA;
import com.maxgcoding.lex4j.Automata.States.NFAState;
import com.maxgcoding.lex4j.Automata.Transitions.EpsilonTransition;
import com.maxgcoding.lex4j.Automata.compile.DFABuilder;
import com.maxgcoding.lex4j.Automata.compile.NFABuilder;
import com.maxgcoding.lex4j.Automata.compile.parse.AST;
import com.maxgcoding.lex4j.Automata.compile.parse.Parser;
import com.maxgcoding.lex4j.Automata.compile.parse.PrettyPrint;
import com.maxgcoding.lex4j.Input.TokenPatternRule;

public class LexGen {
    private Parser parser;
    private NFABuilder nfaBuilder;
    private DFABuilder dfaBuilder;
    public LexGen() {
        parser = new Parser();
        nfaBuilder = new NFABuilder();
        dfaBuilder = new DFABuilder();
    }
    public Lexer generate(List<TokenPatternRule> patternRules) {
        NFA final_nfa = new NFA(new NFAState(1000), new NFAState(1001));
        StringBuilder final_expr = new StringBuilder();
        List<NFAState> acceptStates = new ArrayList<>();
        for (TokenPatternRule rule : patternRules) {
            AST node = parser.parse(rule.getPattern());
            PrettyPrint.traverse(node);
            NFA nfa = nfaBuilder.build(node);
            acceptStates.add(nfa.getAccept());
            final_nfa.getStart().addTransition(new EpsilonTransition(nfa.getStart()));
            nfa.getAccept().addTransition(new EpsilonTransition(final_nfa.getAccept()));
            nfa.getAccept().setToken(rule.getNumber());
            System.out.println(nfa.getAccept().getLabel() + " should recognize rule " + rule.getNumber());
            final_expr.append(rule.getPattern()).append("|");
        }
        final_expr.setCharAt(final_expr.lastIndexOf("|"), ')');
        DFA dfa = buildDFA(final_nfa, final_expr.toString());
        return new Lexer(dfa, patternRules);
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