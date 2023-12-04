import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private String username;
    private String password;
    private List<Task> assignedTasks;
    private List<Notification> notifications;

    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.assignedTasks = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void assignTask(Task task) {
        assignedTasks.add(task);
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void receiveNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> viewNotifications() {
        return notifications;
    }
}
