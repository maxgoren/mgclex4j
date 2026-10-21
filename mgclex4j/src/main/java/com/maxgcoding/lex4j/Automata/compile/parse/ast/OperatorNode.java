package com.maxgcoding.lex4j.Automata.compile.parse.ast;

import com.maxgcoding.lex4j.Automata.compile.parse.AST;

public class OperatorNode extends AST {

    private AST left;
    private AST right;
    private Character data;
    private Boolean lazy;
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
        return data;
    }

    @Override
    public AST setData(Character ch) {
        this.data = ch;
        return this;
    }
    public OperatorNode setLazy(Boolean islazy) {
        this.lazy = islazy;
        return this;
    }
    public Boolean isLazy() {
        return lazy;
    }
}
