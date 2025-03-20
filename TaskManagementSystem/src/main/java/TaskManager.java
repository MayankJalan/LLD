import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    private static TaskManager instance;
    private final Map<Integer, Task> tasks;
    private final Map<Integer, List<Task>> userTasks;

    private TaskManager() {
        tasks = new ConcurrentHashMap<>();
        userTasks = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        assignTaskToUser(task.getAssignedUser(), task);
    }

    public void updateTask(Task updatedTask) {
        Task exitingTask = tasks.get(updatedTask.getId());
        if (exitingTask != null) {
            synchronized (exitingTask) {
                exitingTask.setTitle(updatedTask.getTitle());
                exitingTask.setDescription(updatedTask.getDescription());
                exitingTask.setPriority(updatedTask.getPriority());
                exitingTask.setDueDate(updatedTask.getDueDate());
                exitingTask.setTaskstatus(updatedTask.getTaskstatus());

                User previousUser = exitingTask.getAssignedUser();
                User newUser = updatedTask.getAssignedUser();
                if (previousUser.getId() != newUser.getId()) {
                    assignTaskToUser(newUser, updatedTask);
                    unAssignTaskToUser(previousUser, exitingTask);
                }
            }
        }
    }

    private void unAssignTaskToUser(User user, Task task) {
        List<Task> tasks = userTasks.get(user.getId());
        if (tasks != null) {
            tasks.remove(task);
        }
    }

    private void assignTaskToUser(User user, Task task) {
        userTasks.computeIfAbsent(user.getId(), k -> new CopyOnWriteArrayList<>()).add(task);
    }

    public void markTaskAsCompleted(Integer taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            synchronized (task) {
                task.setTaskstatus(TaskStatus.COMPLETED);
            }
        }
    }

    public List<Task> getTaskHistory(User user) {
        return new ArrayList<>(userTasks.getOrDefault(user.getId(), new ArrayList<>()));
    }

    public void deleteTask(String taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            tasks.remove(task);
            unAssignTaskToUser(task.getAssignedUser(), task);
        }
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.values().stream().filter(t ->
                t.getTitle().contains(keyword) ||
                        t.getDescription().contains(keyword)).collect(Collectors.toList());
    }

    public List<Task> filterTasks(TaskStatus status, Date startDate, Date endDate, int priority) {
        return tasks.values().stream().filter(t ->
                t.getTaskstatus().equals(status) &&
                t.getDueDate().compareTo(startDate) >= 0 &&
                t.getDueDate().compareTo(endDate) <= 0 &&
                t.getPriority()==priority).collect(Collectors.toList());
    }
}


