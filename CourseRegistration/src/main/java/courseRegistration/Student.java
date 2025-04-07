package courseRegistration;

import java.util.List;

public class Student {
    private final String id;
    private final String name;
    private final String email;

    private final List<Course> courses;

    public Student(String id, String name, String email, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
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

    public List<Course> getCourses() {
        return courses;
    }
}
