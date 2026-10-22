package com.maxgcoding.lex4j.Automata.compile;

import java.util.Stack;

import com.maxgcoding.lex4j.Automata.NFA;
import com.maxgcoding.lex4j.Automata.States.NFAState;
import com.maxgcoding.lex4j.Automata.Transitions.CharClassTransition;
import com.maxgcoding.lex4j.Automata.Transitions.CharacterTransition;
import com.maxgcoding.lex4j.Automata.Transitions.EpsilonTransition;
import com.maxgcoding.lex4j.Automata.compile.parse.AST;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.CharClassNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.ConcatNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.LiteralNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.OperatorNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.OrNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.PlusClosureNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.QuestClosureNode;
import com.maxgcoding.lex4j.Automata.compile.parse.ast.StarClosureNode;

public class NFABuilder {
 private final Stack<NFA> st;
    private int nextLabel;

    private int makeLabel() {
        return nextLabel++;
    }

    public NFABuilder() {
        st = new Stack<>();
    }

    public NFA build(AST node) {
        compile(node.getLeft());
        if (st.isEmpty())
            return null;
        return st.pop();
    }

    private NFA makeAtomic(Character str) {
        NFAState ns = new NFAState(makeLabel());
        NFAState ts = new NFAState(makeLabel());
        ns.addTransition(new CharacterTransition(str, ts));
        return new NFA(ns,ts);
    }

    private NFA makeCCLAtomic(String str) {
        NFAState ns = new NFAState(makeLabel());
        NFAState ts = new NFAState(makeLabel());
        ns.addTransition(new CharClassTransition(str, ts));
        return new NFA(ns,ts);
    }

    private NFA makeEpsilonAtomic() {
        NFAState ns = new NFAState(makeLabel());
        NFAState ts = new NFAState(makeLabel());
        ns.addTransition(new EpsilonTransition(ts));
        return new NFA(ns,ts);
    }

    private NFA makeConcat(NFA lhs, NFA rhs) {
        lhs.getAccept().addTransition(new EpsilonTransition(rhs.getStart()));
        lhs.setAccept(rhs.getAccept());
        return lhs;
    }

    private NFA makeAlternate(NFA lhs, NFA rhs) {
        NFAState ns = new NFAState(makeLabel());
        NFAState ts = new NFAState(makeLabel());
        ns.addTransition(new EpsilonTransition(lhs.getStart()));
        ns.addTransition(new EpsilonTransition(rhs.getStart()));
        lhs.getAccept().addTransition(new EpsilonTransition(ts));
        rhs.getAccept().addTransition(new EpsilonTransition(ts));
        return new NFA(ns, ts);
    }

    private NFA makeKleene(NFA lhs, boolean mustMatch) {
        NFAState ns = new NFAState(makeLabel());
        NFAState ts = new NFAState(makeLabel());
        if (!mustMatch)
            ns.addTransition(new EpsilonTransition(ts));
        ns.addTransition(new EpsilonTransition(lhs.getStart()));
        lhs.getAccept().addTransition(new EpsilonTransition(lhs.getStart()));
        lhs.getAccept().addTransition(new EpsilonTransition(ts));
        return new NFA(ns, ts);
    }

    private void compile(AST curr) {
        switch (curr) {
            case null -> { }
            case LiteralNode node -> st.push(makeAtomic(node.getData()));
            case CharClassNode node -> st.push(makeCCLAtomic(node.getCcl()));
            case OperatorNode node -> compileOperator(node);
            default -> { }
        }
    }

    private void compileOperator(OperatorNode operatorNode) {
        switch (operatorNode) {
            case OrNode node -> {
                compile(node.getLeft());
                compile(node.getRight());
                NFA rhs = st.pop();
                NFA lhs = st.pop();
                st.push(makeAlternate(lhs, rhs));
                break;
            }
            case ConcatNode node -> {
                compile(node.getLeft());
                compile(node.getRight());
                NFA rhs = st.pop();
                NFA lhs = st.pop();
                st.push(makeConcat(lhs, rhs));
                break;
            }
            case StarClosureNode node -> {
                compile(node.getLeft());
                NFA lhs = st.pop();
                st.push(makeKleene(lhs, false));
                break;
            }
            case PlusClosureNode node -> {
                compile(node.getLeft());
                NFA lhs = st.pop();
                st.push(makeKleene(lhs, true));
                break;
            }
            case QuestClosureNode node -> {
                compile(node.getLeft());
                NFA lhs = st.pop();
                st.push(makeAlternate(lhs, makeEpsilonAtomic()));
                break;
            }
            default -> { }
        }
    }
}
