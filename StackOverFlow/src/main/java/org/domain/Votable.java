package org.domain;

public interface Votable {
    void vote(User user, int value);
    int getVoteCount();
}
