package facebook;

import java.sql.Time;
import java.sql.Timestamp;

public class Comment {
    private final String id;
    private final String userId;
    private final String postId;
    private final String comment;
    private final Timestamp timestamp;

    public Comment(String id, String userId, String post, String comment, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.postId = post;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
