package facebook;

import java.sql.Timestamp;

public class Notification {
    private final String id;
    private final String userId;
    private final NotificationType notificationType;
    private final String content;
    private final Timestamp timestamp;

    public Notification(String id, String userId, NotificationType notificationType, String content, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
