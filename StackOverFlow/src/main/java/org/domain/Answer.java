package org.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Commentable,Votable{
    private final int id;
    private final String content;
    private User author;
    private final Date creationDate;
    private final Question question;
    private boolean isAccepted;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(String content,Question question, User author) {
        this.id=generateId();
        this.content = content;
        this.author = author;
        this.question=question;
        this.creationDate=new Date();
        this.comments=new ArrayList<>();
        this.votes=new ArrayList<>();
        this.isAccepted = false;
    }
    private int generateId() {
        return (int) System.currentTimeMillis() %Integer.MAX_VALUE;
    }

    public void markAsAccepted() {
        if (isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);  // +15 reputation for accepted answer
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void vote(User user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }
        votes.removeIf(v -> v.getUser()==user);
        votes.add(new Vote(user,value));
        author.updateReputation(value * 10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote :: getValue).sum();
    }

    public int getId() { return id; }
    public User getAuthor() { return author; }
    public Question getQuestion() { return question; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public boolean isAccepted() { return isAccepted; }



}
