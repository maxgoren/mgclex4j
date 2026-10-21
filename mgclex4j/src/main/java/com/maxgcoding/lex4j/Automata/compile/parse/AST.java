package com.maxgcoding.lex4j.Automata.compile.parse;

public abstract class AST {
    abstract public Character getData();
    abstract public Integer getGroup(); 
    abstract public AST getLeft();
    abstract public AST getRight();
    abstract public AST setGroup(Integer group);
    abstract public AST setLeft(AST l);
    abstract public AST setRight(AST r);
    abstract public AST setData(Character ch);
}
