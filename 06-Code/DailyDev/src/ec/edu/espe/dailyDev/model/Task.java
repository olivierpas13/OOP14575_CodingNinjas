package ec.edu.espe.dailyDev.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.dailyDev.utils.FileHandler;
import ec.edu.espe.dailyDev.utils.MenuUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CodingNinjas 
 */
public class Task{
    private UUID id;
    private String name;
    private String description;
    private Date dueDate;
    private Date creationDate;
    private UUID userId;
    private boolean completed;
    private static List<String> taskList = new ArrayList<>();
    
    public Task(UUID id, String name, String description, Date dueDate, Date creationDate, UUID userId, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.creationDate = creationDate;
        this.userId = userId;
        this.completed = completed;
    }
    
    public void showMenu() {
        String[] taskOptions = {
                "Create Task",
                "Show Tasks",
                "Update Task",
                "Complete Task",
                "Delete Task",
                "Back to Main Menu"
        };

        int option;

        do {
            option = MenuUtils.getUserOption("Task", taskOptions);

            switch (option) {
                case 1 -> create();
                case 2 -> show();
                case 3 -> showTasksTodays();
                case 4 -> complete();
                case 5 -> delete();
                case 6 -> MenuUtils.backToMainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
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
            dueDate = truncateTime(new SimpleDateFormat("yyyy-MM-dd").parse(dueDateInput));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using the current date as the due date.");
            dueDate = truncateTime(new Date());
        }

        // Estado de completitud predeterminado al crear una nueva tarea
        boolean completed = false;

        UUID userId = User.getCurrentUserId();

        // Validar que la fecha de vencimiento sea después de la fecha de creación
        Date creationDate = truncateTime(new Date());
        if (dueDate.before(creationDate)) {
            System.out.println("Error: Due date must be after the creation date.");
            MenuUtils.backToMainMenu();
            return;
        }

        Task newTask = new Task(taskId, taskName, taskDescription, dueDate, creationDate, userId, completed);

        newTask.setUserId(userId);

        ArrayList<Task> existingTasks = getTasksFromFile("tasks.json");

        existingTasks.add(newTask);

        writeTasksToFile("tasks.json", existingTasks);

        System.out.println("Task created!");
        System.out.println("UserID: " + userId);
        System.out.println(newTask);
        MenuUtils.backToMainMenu();
    }

    public static void show() {
        System.out.println("\nShowing tasks...");

        UUID userId = User.getCurrentUserId();
        if (userId == null) {
            System.out.println("No user logged in. Please log in to view tasks.");
            MenuUtils.backToMainMenu();
            return;
        }

        ArrayList<Task> tasks = getTasksFromFile("tasks.json");

        System.out.println("Tasks created by the current user:");

        // Imprimir la cabecera una vez antes de las tareas
        Task.printFormattedTaskHeader();

        for (Task task : tasks) {
            if (task.getUserId() != null && task.getUserId().equals(userId)) {
                task.printFormattedTask();
            }
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found for the current user.");
        } else {
            System.out.println("\nOptions:");

            int option = MenuUtils.getUserOption("Select an option", new String[]{" Complete Tasks", " Back to Menu"});

            switch (option) {
                case 1:
                    completeTask(tasks);
                    break;
                case 2:
                    MenuUtils.backToMainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Returning to the main menu.");
                    MenuUtils.backToMainMenu();
            }
        }
    }

    public static void completeTask(ArrayList<Task> tasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nComplete Task:");

        System.out.println("Enter the ID of the task to complete:");
        UUID taskIdToComplete = UUID.fromString(scanner.nextLine());

        // Buscar la tarea por ID
        Task selectedTask = null;
        for (Task task : tasks) {
            if (task.getId().equals(taskIdToComplete)) {
                selectedTask = task;
                break;
            }
        }

        if (selectedTask != null) {
            // Actualizar el estado de completitud de la tarea seleccionada
            selectedTask.setCompleted(true);

            // Guardar los cambios en el archivo
            writeTasksToFile("tasks.json", tasks);

            System.out.println("Task completed successfully!");
        } else {
            System.out.println("Task not found with the specified ID. Returning to the main menu.");
        }

        show();
    }

    public static void showTasksTodays() {
        System.out.println("Showing tasks for today...");

        UUID userId = User.getCurrentUserId();
        if (userId == null) {
            System.out.println("No user logged in. Please log in to view tasks.");
            MenuUtils.backToMainMenu();
            return;
        }

        ArrayList<Task> tasks = getTasksFromFile("tasks.json");

        System.out.println("Tasks due today:");

        // Imprimir la cabecera una vez antes de las tareas
        Task.printFormattedTaskHeader();

        // Obtener la fecha actual sin tener en cuenta la parte de hora
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

    // Método auxiliar para verificar si dos fechas son del mismo día (sin tener en cuenta la parte de hora)
    private static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(truncateTime(date1)).equals(fmt.format(truncateTime(date2)));
    }

    // Método auxiliar para truncar la parte de hora de una fecha
    private static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
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
        return "\nNew Task  " + "\nId = " + id + "\nName = " + name + "\nDescription = " + description + "\nDue Date = " + formatDate(dueDate) + "\nCreation Date = " + formatDate(creationDate) + "\nUser Id = " + userId + "\nCompleted = " + completed + '.';
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
    
}