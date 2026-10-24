package com.maxgcoding.lex4j.Analyzer;

import java.util.ArrayList;
import java.util.List;

import com.maxgcoding.lex4j.Automata.DFA;
import com.maxgcoding.lex4j.Automata.States.DFAState;
import com.maxgcoding.lex4j.Automata.Transition;
import com.maxgcoding.lex4j.Input.TokenPatternRule;

public class Lexer {
    private int[][] dtrans;
    private int[] accept;
    int nstates;
    private List<TokenPatternRule> rules;
    public Lexer(DFA dfa, List<TokenPatternRule> rules) {
        this.rules = rules;
        this.nstates = dfa.getStates().size();
        this.dtrans = new int[nstates][128];
        this.accept = new int[nstates];
        for (int i = 0; i < nstates; i++) {
            dtrans[i] = new int[128];
            for (int j = 0; j < 128; j++)
                dtrans[i][j] = -1;
        }
        for (DFAState state : dfa.getStates()) {
            //System.out.print(state.getLabel() + ": ");
            for (Transition t : state.getTrans()) {
                dtrans[state.getLabel()][t.edgeLabel()] = t.getDestination().getLabel();
                //System.out.print(t.edgeLabel() + "(" + t.getDestination().getLabel() + ")");
            }
            //System.out.println(", Accepts: " + (state.isAccept() ? state.getToken():-1));
            accept[state.getLabel()] = state.isAccept() ? state.getToken()-1:-1;
        }
    }
    public List<MatchResult> lex(String input) {
        ArrayList<MatchResult> results = new ArrayList<>(); 
        for (int i = 0; i < input.length(); i++) {
            while (input.charAt(i) == ' ') i++;
            MatchResult mr = match(input.substring(i));
            if (mr != null) {
                i += mr.getMatch().length()-1;       
                results.add(mr);
            }
        }
        return results;
    }
    private MatchResult match(String text) {
        int s = 0;
        String matched = null;
        int matchedBy = -1;
        for (int i = 0; i < text.length(); i++) {
            s = dtrans[s][text.charAt(i)];
            if (s == -1) {
                break;
            } else if (accept[s] > -1) {
                matched = text.substring(0, i+1);
                matchedBy = accept[s];
            }
        }
        if (matched == null)
            return null;
        return new MatchResult(matched, rules.get(matchedBy).getToken());
    }
}
