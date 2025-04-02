package facebook;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Facebook {
    private static Facebook instance;
    private final Map<String, User> users;
    private final Map<String, Post> posts;
    private final Map<String, List<Notification>> notifications;

    private Facebook() {
        users = new ConcurrentHashMap<>();
        posts = new ConcurrentHashMap<>();
        notifications = new ConcurrentHashMap<>();
    }

    public synchronized static Facebook getInstance() {
        if (instance == null)
            instance = new Facebook();
        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User loginUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public void updateUserProfile(User user) {
        users.put(user.getId(), user);
    }

    public List<Post> getNewsfeed(String userId) {
        List<Post> feed=new ArrayList<>();
        User user=users.get(userId);
        for(String followerId : user.getFriends()){
            User friend=users.get(followerId);
            feed.addAll(friend.getPosts());
        }
        feed.addAll(user.getPosts());
        Collections.sort(feed,(a,b) -> b.getTimestamp().compareTo(a.getTimestamp()));
        return feed;
    }


    public void sendFriendRequest(String senderId, String receiverId) {
        User receiver = users.get(receiverId);
        if (receiver != null) {
            String id = generateNotificationId();
            Notification notification = new Notification(id, receiverId, NotificationType.FRIEND_REQUEST,
                    "Friend request from " + senderId, new Timestamp(System.currentTimeMillis()));
            addNotification(receiverId, notification);
        }
    }

    public void acceptFriendRequest(String userId, String friendId) {
        User receiver = users.get(userId);
        User friend = users.get(friendId);
        if (friend != null && receiver != null) {
            if (!receiver.getFriends().contains(friendId)) {
                friend.getFriends().add(userId);
                receiver.getFriends().add(friendId);

                String id = generateNotificationId();
                Notification notification = new Notification(id, friendId, NotificationType.FRIEND_REQUEST_ACCEPTED,
                        "Friend request accepted by  " + userId, new Timestamp(System.currentTimeMillis()));
                addNotification(friendId, notification);
            }
        }
    }

    public void createPost(Post post) {
        posts.put(post.getId(), post);
        User user = users.get(post.getUserId());
        if (user != null) {
            user.getPosts().add(post);
        }
    }

    public void likePost(String userId, String postId) {
        Post post = posts.get(postId);
        User user = users.get(userId);
        if (post != null && !post.getLikes().contains(userId)) {
            post.getLikes().add(userId);
            String id = generateNotificationId();
            Notification notification = new Notification(id, post.getUserId(), NotificationType.LIKE,
                    "Post Liked By  " + userId, new Timestamp(System.currentTimeMillis()));
            addNotification(post.getUserId(), notification);
        }
    }

    public void commentOnPost(Comment comment) {

        Post post = posts.get(comment.getPostId());
        User user = users.get(comment.getUserId());
        if (post != null) {
            post.getComments().add(comment);
            String id = generateNotificationId();
            Notification notification = new Notification(id, post.getUserId(), NotificationType.COMMENT,
                    "Post Commented By  " + comment.getPostId(), new Timestamp(System.currentTimeMillis()));
            addNotification(post.getUserId(), notification);
        }
    }

    private String generateNotificationId() {
        return UUID.randomUUID().toString();
    }

    private void addNotification(String userId, Notification notification) {
        notifications.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(notification);
    }

    public List<Notification> getNotifications(String userId) {
        return notifications.get(userId);
    }


}
