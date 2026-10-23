package com.maxgcoding.lex4j.Analyzer;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Token {
    private static final Map<String, Token> REGISTRY = new ConcurrentHashMap<>();
    private final String name;
    private final int ordinal;

    private Token(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public static synchronized Token define(String name) {
        if (REGISTRY.containsKey(name))
            return REGISTRY.get(name);
        Token nt = new Token(name, REGISTRY.size());
        REGISTRY.put(name, nt);
        return nt;
    }

    public static Token valueOf(String name) {
        Token value = REGISTRY.get(name);
        return value;
    }

    public static Collection<Token> values() {
        return Collections.unmodifiableCollection(REGISTRY.values());
    }

    @Override 
    public int hashCode() {
        return name.hashCode();
    }
    @Override 
    public boolean equals(Object obj) {
        return this == obj;
    }
    @Override 
    public String toString() {
        return name;
    }
    public int ordinal() {
        return ordinal;
    }
}
