package ec.edu.espe.dailyDev.view;

import ec.edu.espe.dailyDev.model.Administrator;
import ec.edu.espe.dailyDev.model.Developer;
import ec.edu.espe.dailyDev.model.Meeting;
import ec.edu.espe.dailyDev.model.Message;
import ec.edu.espe.dailyDev.model.Task;
import ec.edu.espe.dailyDev.model.User;
import ec.edu.espe.dailyDev.utils.MenuUtils;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author CodingNinjas
 */
public class LandingPage {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showLandingPage() throws Exception {
        int option;

        System.out.println("Daily Dev System\n");
        System.out.println("Select one option please\n");
        System.out.println("1. Login to an existing account\n2. Create a new account");

        do {
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea pendiente después de nextInt
        } while (option != 1 && option != 2);

        for (int i = 0; i < 50; i++) {
            System.out.print("\n\n\n");
        }

        switch (option) {
            case 1 ->
                showLogin();
            case 2 ->
                showRegistration();
            default ->
                throw new AssertionError();
        }
    }

    private static void showLogin() throws Exception {
        User user = null;
        int option;
        do {
            System.out.println("Select your user type:\n");
            System.out.println("1. Developer\n2. Administrator");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea pendiente después de nextInt
        } while (option != 1 && option != 2);

        do {
            System.out.println("Login\n");

            System.out.println("Username:\n");
            String username = scanner.nextLine();

            System.out.println("Password:\n");
            String password = scanner.nextLine();

            try {
                user = User.login(username, password, option == 1 ? "dev": "admin");
                if (user != null) {
                    System.out.println("Login successful");
                    showMainMenu();
                }
            } catch (User.InvalidCredentialsException e) {
                System.err.println(e.getMessage());
                System.out.println("Invalid username or password. Please try again.");
            }
        } while (user == null);
    }

    private static void showRegistration() {
        int option;

        System.out.println("Registration Menu\n");
        System.out.println("1. Register as an administrator\n");
        System.out.println("2. Register as a developer");
        do {
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea pendiente después de nextInt
        } while (option != 1 && option != 2);

        switch (option) {
            case 1 ->
                showAdminRegistration();
            case 2 ->
                showDevRegistration();
            default ->
                throw new AssertionError();
        }
    }

    private static void showAdminRegistration() {
        System.out.println("Username:\n");
        String username = scanner.nextLine();

        System.out.println("Password:\n");
        String password = scanner.nextLine();

        try {
            Administrator admin = Administrator.registerAdmin(username, password);
            if (admin != null) {
                System.out.println("Successful registration");
                showLogin();  // Después de registrar, permite iniciar sesión directamente
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showDevRegistration() {
        System.out.println("Enter your organization code (your administrator should give it to you):\n");
        String orgCode = scanner.nextLine();

        System.out.println("Username:\n");
        String username = scanner.nextLine();

        System.out.println("Password:\n");
        String password = scanner.nextLine();
        
        
        try {
            Developer dev = Developer.registerDev(username, password, UUID.fromString(orgCode));
            if (dev != null) {
                System.out.println("Successful registration");
                showLogin();  // Después de registrar, permite iniciar sesión directamente
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
 public static void showMainMenu() {
        int optionMain;

        do {
            System.out.println("\nMain Menu");

            // Verificar el estado de las tareas y mostrar mensajes
            ArrayList<Task> tasks = Task.getTasksFromFile("tasks.json");
            if (Task.areAllTasksCompleted(tasks)) {
                Message allTasksCompletedMessage = Task.createAllTasksCompletedMessage();
                System.out.println("\nMessage: " + allTasksCompletedMessage.getTitle());
                System.out.println(allTasksCompletedMessage.getDescription());
            } else {
                Message tasksPendingMessage = Task.createTasksPendingMessage();
                System.out.println("\nMessage: " + tasksPendingMessage.getTitle());
                System.out.println(tasksPendingMessage.getDescription());
            }

            System.out.println("\nSelect one option:\n");
            System.out.println("1. Task\n2. Meeting\n3. Logout");

            optionMain = scanner.nextInt();
            scanner.nextLine();

            switch (optionMain) {
                case 1 -> taskMenu();
                case 2 -> meetingMenu(); 
                case 3 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionMain != 3);
    }

    private static void taskMenu() {
        String[] taskOptions = {
            "Create Task",
            "Show Tasks",
            "Show today's pending tasks",
            "Complete Task",
            "Delete Task",
            "Back to Main Menu"
        };

        int optionTask;

        do {
            optionTask = MenuUtils.getUserOption("Task", taskOptions);

            switch (optionTask) {
                case 1 ->
                    Task.create();
                case 2 ->
                    Task.show();
                case 3 ->
                    Task.showTasksTodays();
                case 4 ->
                    Task.complete();
                case 5 ->
                    Task.delete();
                case 6 ->
                    MenuUtils.backToMainMenu();
                default ->
                    System.out.println("Invalid option. Please try again.");
            }
        } while (optionTask != 6);
    }
    
        private static void meetingMenu() {
        String[] meetingOptions = {
            "Create meeting",
            "Show metings",
            "Show today' meeting",
            "Complete Task",
            "Delete Task",
            "Back to Main Menu"
        };

        int optionMeeting;

        do {
            optionMeeting = MenuUtils.getUserOption("Task", meetingOptions);

            switch (optionMeeting) {
                case 1 -> Meeting.create();
                case 2 -> Meeting.show();
                case 3 -> Meeting.showMeetingTodays();
                case 4 -> Meeting.complete();
                case 5 -> Meeting.delete();
                case 6 -> MenuUtils.backToMainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionMeeting != 6);
    }
    
    
}
