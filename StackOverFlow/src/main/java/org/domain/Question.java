package org.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;

public class Question implements Votable, Commentable {
    private final int id;
    private final String title;
    private final String content;
    private User author;
    private final Date creationDate;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;


    public Question(String title, String description, User author, List<String> tagNames) {
        this.id = generateId();
        this.title = title;
        this.content = description;
        this.author = author;
        this.tags = new ArrayList<>();
        this.creationDate = new Date();
        comments = new ArrayList<>();
        answers = new ArrayList<>();
        votes = new ArrayList<>();

        for (String tagName : tagNames) {
            this.tags.add(new Tag(tagName));
        }
    }

    private int generateId() {
        return (int) System.currentTimeMillis() % Integer.MAX_VALUE;
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
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 5); // +5 for upvote, -5 for downvote
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    // Getters
    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }
}

