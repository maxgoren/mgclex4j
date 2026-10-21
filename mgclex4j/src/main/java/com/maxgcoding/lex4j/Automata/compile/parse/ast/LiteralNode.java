package com.maxgcoding.lex4j.Automata.compile.parse.ast;

import com.maxgcoding.lex4j.Automata.compile.parse.AST;

public class LiteralNode extends AST {

    private Character data;
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
        return null;
    }

    @Override
    public AST getRight() {
        return null;
    }

    @Override
    public AST setLeft(AST l) {
        return this;
    }

    @Override
    public AST setRight(AST r) {
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
}
