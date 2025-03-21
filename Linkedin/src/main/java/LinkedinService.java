import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class LinkedinService {
    private static LinkedinService instance;
    private Map<String, User> users;
    private Map<String, List<Notification>> notifications;
    private Map<String, JobPosting> jobPostings;

    private LinkedinService() {
        users = new ConcurrentHashMap<>();
        notifications = new ConcurrentHashMap<>();
        jobPostings = new ConcurrentHashMap<>();
    }

    public static synchronized LinkedinService getInstance() {
        if (instance == null) {
            instance = new LinkedinService();
        }
        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User loginUser(String email, String password) {
        User user = users.values().stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst().get();
        return user;
    }

    public void updateUserProfile(User user) {
        users.put(user.getId(), user);
    }

    public void sendConnectionRequest(User sender, User receiver) {
        Connection connection = new Connection(sender, new Timestamp(System.currentTimeMillis()));
        sender.getConnections().add(connection);

        Notification notification = new Notification(generateNotificationId(), NotificationType.CONNECTION_REQUEST,
                receiver, "New Connection Request from : " + sender.getName(),
                new Timestamp(System.currentTimeMillis()));
        addNotification(receiver.getId(), notification);

    }

    public void acceptConnectionRequest(User user, User connectionUser) {
        user.getConnections().add(new Connection(connectionUser, new Timestamp(System.currentTimeMillis())));
    }

    public List<User> searchUsers(String keyword) {
        return users.values().stream().filter(u -> u.getName().contains(keyword)).collect(Collectors.toList());
    }

    public void postJobListing(JobPosting jobPosting) {
        jobPostings.put(jobPosting.getId(), jobPosting);
        for (User user : users.values()) {
            Notification notification = new Notification(generateNotificationId(), NotificationType.JOB_POSTING,
                    user, "New Job Posting " + jobPosting.getTitle(),
                    new Timestamp(System.currentTimeMillis()));

            addNotification(user.getId(), notification);

        }

    }

    public List<JobPosting> searchJobPostings(String keyword) {
           return jobPostings.values().stream().filter(jp -> jp.getDescription().contains(keyword) || jp.getTitle().contains(keyword)).collect(Collectors.toList());
    }

    public void sendMessage(User sender, User receiver, String content) {
        Message message=new Message(generateMessageId(),sender, receiver,content,new Timestamp(System.currentTimeMillis()));

        receiver.getInbox().add(message);
        sender.getSentMessages().add(message);
        Notification notification = new Notification(generateNotificationId(),NotificationType.MESSAGE, receiver
                , "New message from " + sender.getName(),
                new Timestamp(System.currentTimeMillis()));
        addNotification(receiver.getId(), notification);


    }

    private String generateMessageId() {
        return UUID.randomUUID().toString();
    }


    private void addNotification(String userId, Notification notification) {
        notifications.computeIfAbsent(userId ,k -> new CopyOnWriteArrayList<>()).add(notification);
    }

    public List<Notification> getNotifications(String userId) {
        return notifications.getOrDefault(userId, new ArrayList<>());
    }

    private String generateNotificationId() {
        return UUID.randomUUID().toString();
    }
}

