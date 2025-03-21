import java.sql.Timestamp;
import java.util.Date;

public class Connection {
    private final User user;
    private final Timestamp connectionDate;

    public Connection(User user, Timestamp connectionDate) {
        this.user = user;
        this.connectionDate = connectionDate;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getConnectionDate() {
        return connectionDate;
    }


}
