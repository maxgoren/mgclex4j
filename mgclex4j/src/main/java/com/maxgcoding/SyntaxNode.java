package com.maxgcoding;

public class SyntaxNode {
    private String data;
    private String token;
    private SyntaxNode[] child;
    public SyntaxNode() {
        child = new SyntaxNode[3];
    }
    public String getData() {
        return data;
    }
    public void setData(String d) {
        data = d;
    }
    public SyntaxNode[] getChildren() {
        return child;
    }
    public void setChild(int i, SyntaxNode sn) {
        child[i] = sn;
    }
    public SyntaxNode getChild(int i) {
        return child[i];
    }
    public void setToken(String t) {
        token = t;
    }
    public String getToken() {
        return token;
    }
}
