package io.swagger.model;

public class FinalVote {
    public int vote = 0;
    public String selection;

    public FinalVote(String selection) {
        this.selection = selection;
    }

    public FinalVote(int vote) {
        this.vote = vote;
    }

    public FinalVote() {
    }
}
