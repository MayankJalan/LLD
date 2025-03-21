import java.sql.Time;
import java.sql.Timestamp;

public class Notification {
    private final String id;
    private final NotificationType notificationType;
    private final User user;
    private final String content;
    private final Timestamp timestamp;

    public Notification(String id, NotificationType notificationType, User user, String content, Timestamp timestamp) {
        this.id = id;
        this.notificationType = notificationType;
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
