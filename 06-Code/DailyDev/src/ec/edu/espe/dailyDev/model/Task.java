package ec.edu.espe.dailyDev.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.dailyDev.utils.FileHandler;
import ec.edu.espe.dailyDev.utils.MenuUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CodingNinjas 
 */
public class Task {
    private UUID id;
    private String name;
    private String description;
    private Date dueDate;
    private Date creationDate;
    
    private static List<String> taskList = new ArrayList<>();

    public Task(UUID id, String name, String description, Date dueDate, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.creationDate = creationDate;
    }
    
    public static ArrayList<Task> getTasksFromFile(String fileAddress) {
        return FileHandler.readFile(fileAddress, new TypeToken<ArrayList<Task>>() {}.getType());
    }

    public static void writeTasksToFile(String fileAddress, ArrayList<Task> tasks) {
        FileHandler.writeFile(fileAddress, tasks);
    }
    
    public static void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating a new task...");

        UUID taskId = UUID.randomUUID();

        System.out.println("Enter the task name:");
        String taskName = scanner.nextLine();

        System.out.println("Enter the task description:");
        String taskDescription = scanner.nextLine();

        System.out.println("Enter the due date (yyyy-MM-dd):");
        String dueDateInput = scanner.nextLine();
        Date dueDate;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using the current date as the due date.");
            dueDate = new Date();
        }

        Task newTask = new Task(taskId, taskName, taskDescription, dueDate, new Date());

        ArrayList<Task> existingTasks = getTasksFromFile("tasks.json");

        existingTasks.add(newTask);

        writeTasksToFile("tasks.json", existingTasks);

        System.out.println("Task created!");
        System.out.println(newTask.toString());

        MenuUtils.backToMainMenu();
    }

    public static void show() {
        System.out.println("Showing tasks...");
        // Lógica para mostrar reuniones
        System.out.println("No tasks found.");  // Ajusta según tu lógica real
        MenuUtils.backToMainMenu();
    }

    public static void update() {
        System.out.println("Updating a task...");
        // Lógica para la actualización de reuniones
        System.out.println("Task updated!");
        MenuUtils.backToMainMenu();
    }

    public static void complete() {
        System.out.println("Completing a task...");
        // Lógica para completar reuniones
        System.out.println("Task completed!");
        MenuUtils.backToMainMenu();
    }

    public static void delete() {
        System.out.println("Deleting a task...");
        // Lógica para eliminar reuniones
        System.out.println("Task deleted!");
        MenuUtils.backToMainMenu();
    }
    
    @Override
    public String toString() {
        return "Task{" + "id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", dueDate=" + getDueDate() + ", creationDate=" + getCreationDate() + '}';
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

    
     
}
