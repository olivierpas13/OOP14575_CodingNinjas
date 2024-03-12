package ec.edu.espe.dailyDev.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.dailyDev.utils.FileHandler;
import ec.edu.espe.dailyDev.utils.MenuUtils;
import com.google.gson.GsonBuilder;
import static com.mongodb.client.model.Updates.set;
import static ec.edu.espe.dailyDev.utils.MongoDBHandler.createDocument;
import static ec.edu.espe.dailyDev.utils.MongoDBHandler.deleteDocument;
import static ec.edu.espe.dailyDev.utils.MongoDBHandler.replaceDocument;
import static ec.edu.espe.dailyDev.utils.MongoDBHandler.updateDocument;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Team Number: 4 - CodingNinjas
 */
public class Task {

    private UUID id;
    private String name;
    private String description;
    private String priority;
    private Date dueDate;
    private Date creationDate;
    private UUID userId;
    private boolean completed;
    private static List<String> taskList = new ArrayList<>();

    public Task(UUID id, String name, String description, String priority, Date dueDate, Date creationDate, UUID userId, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.creationDate = creationDate;
        this.userId = userId;
        this.completed = completed;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public static Message createAllTasksCompletedMessage() {
        return new Message(UUID.randomUUID(), "All Tasks Completed", "Congratulations! All tasks are completed.\n");
    }

    public static Message createTasksPendingMessage() {
        return new Message(UUID.randomUUID(), "Tasks Pending", "You have tasks that are not completed yet.\n");
    }

    public static boolean areAllTasksCompleted(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public static void printFormattedTaskHeader() {
        String format = "| %-36s | %-20s | %-35s | %-15s | %-15s | %-10s |%n";
        System.out.format(format, "ID", "Name", "Description", "Due Date", "Creation Date", "Completed");
    }

    public void printFormattedTask() {
        String format = "| %-36s | %-20s | %-35s | %-15s | %-15s | %-10s |%n";
        System.out.format(format, getId(), getName(), getDescription(), formatDate(getDueDate()), formatDate(getCreationDate()), isCompleted());
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static ArrayList<Task> getTasksFromFile(String fileAddress) {
        return FileHandler.readFile(fileAddress, new TypeToken<ArrayList<Task>>() {
        }.getType());
    }

    public static void writeTasksToFile(String filePath, ArrayList<Task> tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(tasks);

        try {
            File file = new File("./db/" + filePath); // Ruta completa al archivo
            file.getParentFile().mkdirs(); // Asegura que la carpeta exista
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
        }
    }
    
    public static Document updateTask(String taskId, String newTitle, String newDescription, String newStatus) {
        // Construct the query to find the task document by its ID
        Document query = new Document("id", taskId);

        Document replacement = new Document();
        replacement.put("title", newTitle);
        replacement.put("description", newDescription);
        replacement.put("status", newStatus);
        
        return replaceDocument(query, replacement, "Tasks");
    }

    public static Document completeTask(String taskId) {
        Document query = new Document("id", taskId);

        Bson updates = set("completed", true);

        Document updatedDocument = updateDocument(query, updates, "Tasks");

        return updatedDocument;
    }

    public static boolean deleteTask(String taskId) {

        Document query = new Document("id", taskId);

        return deleteDocument(query, "Tasks");
    }

    public static boolean createTask(String title, String description, String priority, String dueDate) {

        UUID id = UUID.randomUUID();

        Date dueDateObj = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Adjust date format as per your formatted field
            dueDateObj = dateFormat.parse(dueDate);
        } catch (ParseException e) {
            System.err.println("Error parsing due date: " + e.getMessage());
            return false;
        }
        System.out.println(dueDateObj);

        Document newTask = new Document("id", id)
                .append("name", title)
                .append("description", description)
                .append("priority", priority.toLowerCase())
                .append("dueDate", dueDateObj)
                .append("userId", User.getCurrentUserId().toString())
                .append("creationDate", new Date())
                .append("completed", false);

        return createDocument(newTask, "Tasks");
    }

    public static void showTasksTodays() {
        System.out.println("Showing tasks for today...");

        UUID userId = User.getCurrentUserId();
        if (userId == null) {
            System.out.println("No user logged in. Please log in to view tasks.");
            MenuUtils.backToMainMenu();
            return;
        }

        ArrayList<Task> tasks = getTasksFromFile("./db/tasks.json");

        System.out.println("Tasks due today:");

        Task.printFormattedTaskHeader();

        Date currentDate = truncateTime(new Date());

        for (Task task : tasks) {
            if (task.getUserId() != null && task.getUserId().equals(userId)
                    && !task.isCompleted() && isSameDay(task.getDueDate(), currentDate)) {
                task.printFormattedTask();
            }
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found for the current user.");
        }

        MenuUtils.backToMainMenu();
    }

    private static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(truncateTime(date1)).equals(fmt.format(truncateTime(date2)));
    }

    private static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return "Task{" + "name=" + name + ", description=" + description + ", dueDate=" + dueDate + ", completed=" + completed + '}';
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * @return the taskList
     */
    public static List<String> getTaskList() {
        return taskList;
    }

    /**
     * @param aTaskList the taskList to set
     */
    public static void setTaskList(List<String> aTaskList) {
        taskList = aTaskList;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

}
