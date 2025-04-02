package facebook;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final String profilePicture;
    private final String bio;
    private final List<Post> posts;
    private final List<String> friends;

    public User(String id, String name, String email, String password, String profilePicture, String bio, List<Post> posts, List<String> friends) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.posts = posts;
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<String> getFriends() {
        return friends;
    }
}
