package ec.edu.espe.dailyDev.view;

import ec.edu.espe.dailyDev.model.Meeting;
import ec.edu.espe.dailyDev.model.Project;
import ec.edu.espe.dailyDev.model.Reminder;
import ec.edu.espe.dailyDev.model.Sprint;
import ec.edu.espe.dailyDev.model.Task;
import ec.edu.espe.dailyDev.model.Team;
import ec.edu.espe.dailyDev.model.User;
import ec.edu.espe.dailyDev.utils.MenuUtils;
import java.util.Scanner;


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
            case 1 -> showLogin();
            case 2 -> showRegistration();
            default -> throw new AssertionError();
        }
    }

    private static void showLogin() throws Exception {
        User user = null;

        do {
            System.out.println("Login\n");

            System.out.println("Username:\n");
            String username = scanner.nextLine();

            System.out.println("Password:\n");
            String password = scanner.nextLine();

            try {
                user = User.login(username, password);
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
        System.out.println("Registration\n");

        System.out.println("Username:\n");
        String username = scanner.nextLine();

        System.out.println("Password:\n");
        String password = scanner.nextLine();

        try {
            User user = User.register(username, password);
            if (user != null) {
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
            System.out.println("\nMain Menu\n");
            System.out.println("--Hello--\nSelect one option:\n");
            System.out.println("1. Task\n2. Meeting\n3. Logout");

            optionMain = scanner.nextInt();
            scanner.nextLine();

            switch (optionMain) {
                case 1 -> taskMenu();
//                case 2 -> meetingMenu();
//                case 3 -> projectMenu();
//                case 4 -> reminderMenu();
//                case 5 -> sprintMenu();
//                case 6 -> teamMenu();
                case 7 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionMain != 7);
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
                case 1 -> Task.create();
                case 2 -> Task.show();
                case 3 -> Task.showTasksTodays();
                case 4 -> Task.complete();
                case 5 -> Task.delete();
                case 6 -> MenuUtils.backToMainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionTask != 6);
    }
}
