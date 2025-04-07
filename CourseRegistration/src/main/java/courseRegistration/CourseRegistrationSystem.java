package courseRegistration;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class CourseRegistrationSystem {
    private static CourseRegistrationSystem instance;
    private final Map<String, Student> students;
    private final Map<String, Course> courses;
    private final List<Registration> registrations;

    private CourseRegistrationSystem() {
        students = new ConcurrentHashMap<>();
        courses = new ConcurrentHashMap<>();
        registrations = new CopyOnWriteArrayList<>();
    }

    public synchronized static CourseRegistrationSystem getInstance() {
        if (instance == null)
            instance = new CourseRegistrationSystem();
        return instance;
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public void addCourse(Course course) {
        courses.put(course.getId(), course);
    }

    public Course getCourse(String id) {
        return courses.get(id);
    }

    public synchronized boolean registerCourse(Student student, Course course) {
        if (course.getEnrolledStudents() < course.getMaxCapacity()) {
            course.setEnrolledStudents(course.getEnrolledStudents() + 1);
            student.getCourses().add(course);
            Registration registration = new Registration(student, course, new Timestamp(System.currentTimeMillis()));
            registrations.add(registration);
            notifyObservers(course,student);
            return true;
        } else {
            return false;
        }
    }

    private void notifyObservers(Course course,Student student) {
        System.out.println(course.getName()+" is registered by "+ student.getName());
    }

    public List<Course> searchCourses(String query) {
        return courses.values().stream().filter(c -> c.getName().contains(query) ||
                c.getId().contains(query)).collect(Collectors.toList());
    }


    public List<Course> getRegisteredCourses(Student student) {
        return student.getCourses();
    }


}
