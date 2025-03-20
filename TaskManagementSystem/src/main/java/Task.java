import java.util.Date;

public class Task {
    private final int id;
    private String title;
    private String description;
    private Date dueDate;
    private int priority;
    private TaskStatus taskstatus;
    private final User assignedUser;

    public Task(int id,String title, String description, Date dueDate, int priority, User assignedUser) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.taskstatus = TaskStatus.PENDING;
        this.assignedUser = assignedUser;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskStatus getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(TaskStatus taskstatus) {
        this.taskstatus = taskstatus;
    }

    public User getAssignedUser() {
        return assignedUser;
    }
}
