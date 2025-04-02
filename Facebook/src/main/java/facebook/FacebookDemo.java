package facebook;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FacebookDemo {
    public static void main(String[] args) {
        Facebook facebook=Facebook.getInstance();

        User user1 = new User("1", "Mayank", "mnk@gmail.com", "password", "profile1.jpg", "I love coding!", new ArrayList<>(), new ArrayList<>());
        User user2 = new User("2", "Kush", "kush@gmail.com", "password", "profile1.jpg", "Exploring the world!!", new ArrayList<>(), new ArrayList<>());

        facebook.registerUser(user1);
        facebook.registerUser(user2);

        // User login
        User loggedInUser = facebook.loginUser("mnk@gmail.com", "password");

        if(loggedInUser != null){
            System.out.println("User Logged in : "+loggedInUser.getName());
        }else {
            System.out.println("Invalid email or password.");
        }
        facebook.sendFriendRequest(loggedInUser.getId(), user2.getId());
        // Send friend request

        // Accept friend request
        facebook.acceptFriendRequest(user2.getId(), user1.getId());

        // Create posts
        Post post1 = new Post("post1", user1.getId(), "My first post!", new ArrayList<>(), new ArrayList<>(), new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        Post post2 = new Post("post2", user2.getId(), "Having a great day!", new ArrayList<>(), new ArrayList<>(), new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        facebook.createPost(post1);
        facebook.createPost(post2);


        // Like a post
        facebook.likePost(user2.getId(), post1.getId());

        // Comment on a post
        Comment comment = new Comment("comment1", user2.getId(), post1.getId(), "Great post!", new Timestamp(System.currentTimeMillis()));
        facebook.commentOnPost(comment);

        // Get newsfeed
        List<Post> newsfeed = facebook.getNewsfeed(user1.getId());
        System.out.println("Newsfeed:");
        for (Post post : newsfeed) {
            System.out.println("Post: " + post.getContent());
            System.out.println("Likes: " + post.getLikes().size());
            System.out.println("Comments: " + post.getComments().size());
            System.out.println();
        }

        // Get notifications
        List<Notification> notifications = facebook.getNotifications(user1.getId());
        System.out.println("Notifications:");
        for (Notification notification : notifications) {
            System.out.println("Type: " + notification.getNotificationType());
            System.out.println("Content: " + notification.getContent());
            System.out.println();
        }





    }
}
