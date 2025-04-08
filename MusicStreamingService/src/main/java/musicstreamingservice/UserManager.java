package musicstreamingservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static UserManager instance;
    private final Map<String, User> users;

    private UserManager() {
        users = new ConcurrentHashMap<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User loginUser(String username, String password){
        return users.values()
                .stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password)).
                findFirst()
                .orElse(null);
    }

}
