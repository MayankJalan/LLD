package courseRegistration;

public class Course {
    private final String id;
    private final String name;
    private final String instructor;
    private final int maxCapacity;
    private int enrolledStudents;

    public Course(String id, String name, String instructor, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        enrolledStudents=0;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }
}
