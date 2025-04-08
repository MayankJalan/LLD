package musicstreamingservice;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private final String userName;
    private final String password;
    private final List<Playlist> playlists;
    public User(String id, String userName, String password ) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.playlists = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
