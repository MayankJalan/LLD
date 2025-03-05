package CarRental;

public class User {
    int id;
    String name;
    String driving_lisence;

    public User(int id,String name, String driving_lisence) {
        this.id=id;
        this.name = name;
        this.driving_lisence = driving_lisence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriving_lisence() {
        return driving_lisence;
    }

    public void setDriving_lisence(String driving_lisence) {
        this.driving_lisence = driving_lisence;
    }
}
