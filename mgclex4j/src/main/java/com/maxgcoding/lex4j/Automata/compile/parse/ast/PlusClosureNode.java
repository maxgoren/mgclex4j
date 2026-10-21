package com.maxgcoding.lex4j.Automata.compile.parse.ast;

import com.maxgcoding.lex4j.Automata.compile.parse.AST;

public class PlusClosureNode extends OperatorNode {
    private AST left;
    private AST right;
    private Integer captureGroup;

    @Override
    public AST setGroup(Integer g) {
        captureGroup = g;
        return this;
    }

    @Override
    public Integer getGroup() {
        return captureGroup;
    }
    
    @Override
    public AST getLeft() {
        return left;
    }

    @Override
    public AST getRight() {
        return right;
    }

    @Override
    public AST setLeft(AST l) {
        left = l;
        return this;
    }

    @Override
    public AST setRight(AST r) {
        right = r;
        return this;
    }

    @Override
    public Character getData() {
        return '+';
    }

    @Override
    public AST setData(Character ch) {
        return this;
    }
}
