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
                System.out.println("Login successful");
                showMainMenu();
            } catch (IllegalArgumentException e) {
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

//    private static void showRegistration() {
//        User user = null;
//
//        do {
//            System.out.println("Registration\n");
//
//            System.out.println("Username:\n");
//            String username = scanner.nextLine();
//
//            // Verificar si el usuario ya existe en la lista en memoria
//            if (usernameExistsInMemory(username)) {
//                System.out.println("Username already exists. Please choose another username.");
//                continue;
//            }
//
//            try {
//                // Obtener una contraseña válida
//                String password = getPassword();
//
//                // Encriptar la contraseña antes de guardarla en el JSON
//                EncryptedPassword encryptedPassword = PasswordHandler.encryptPassword(password);
//
//                // Crear el objeto de usuario
//                user = new User(username, encryptedPassword.getEncryptedPassword());
//                user.setSalt(encryptedPassword.getSalt());
//
//                // Guardar el usuario en la lista en memoria y en el archivo JSON
//                users.add(user);
//                user.saveToFile();
//
//                System.out.println("Registration successful");
//            } catch (IllegalArgumentException e) {
//                System.err.println(e.getMessage());
//                System.out.println("Registration failed. Please try again.");
//            }
//        } while (user == null);
//    }
    
    public static void showMainMenu() {
        int optionMain;

        do {
            System.out.println("\nMain Menu\n");
            System.out.println("--Hello--\nSelect one option:\n");
            System.out.println("1. Task\n2. Meeting\n3. Project\n4. Reminder\n5. Sprint\n6. Team\n7. Logout");

            optionMain = scanner.nextInt();
            scanner.nextLine();

            switch (optionMain) {
                case 1 -> taskMenu();
                case 2 -> meetingMenu();
                case 3 -> projectMenu();
                case 4 -> reminderMenu();
                case 5 -> sprintMenu();
                case 6 -> teamMenu();
                case 7 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionMain != 7);
    }
    
    private static void taskMenu() {
        int optionTask;

        do {
            optionTask = MenuUtils.getUserOption("Task");

            switch (optionTask) {
                case 1 -> Task.create();
                case 2 -> Task.show();
                case 3 -> Task.update();
                case 4 -> Task.complete();
                case 5 -> Task.delete();
                case 6 -> MenuUtils.backToMainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionTask != 6);
    }
    
    private static void meetingMenu() {
        int optionMeeting;

        do {
            optionMeeting = MenuUtils.getUserOption("Meeting");

            switch (optionMeeting) {
                case 1 -> Meeting.create();
                case 2 -> Meeting.show();
                case 3 -> Meeting.update();
                case 4 -> Meeting.complete();
                case 5 -> Meeting.delete();
                case 6 -> MenuUtils.backToMainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (optionMeeting != 6);
    }
    
    private static void projectMenu() {
    int optionProject;

    do {
        optionProject = MenuUtils.getUserOption("Project");

        switch (optionProject) {
            case 1 -> Project.create();
            case 2 -> Project.show();
            case 3 -> Project.update();
            case 4 -> Project.complete();
            case 5 -> Project.delete();
            case 6 -> MenuUtils.backToMainMenu();
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (optionProject != 6);
    }

    private static void reminderMenu() {
    int optionReminder;

    do {
        optionReminder = MenuUtils.getUserOption("Reminder");

        switch (optionReminder) {
            case 1 -> Reminder.create();
            case 2 -> Reminder.show();
            case 3 -> Reminder.update();
            case 4 -> Reminder.complete();
            case 5 -> Reminder.delete();
            case 6 -> MenuUtils.backToMainMenu();
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (optionReminder != 6);
    }
    
    private static void sprintMenu() {
    int optionSprint;

    do {
        optionSprint = MenuUtils.getUserOption("Sprint");

        switch (optionSprint) {
            case 1 -> Sprint.create();
            case 2 -> Sprint.show();
            case 3 -> Sprint.update();
            case 4 -> Sprint.complete();
            case 5 -> Sprint.delete();
            case 6 -> MenuUtils.backToMainMenu();
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (optionSprint != 6);
    }
    
    private static void teamMenu() {
    int optionTeam;

    do {
        optionTeam = MenuUtils.getUserOption("Team");

        switch (optionTeam) {
            case 1 -> Team.create();
            case 2 -> Team.show();
            case 3 -> Team.update();
            case 4 -> Team.complete();
            case 5 -> Team.delete();
            case 6 -> MenuUtils.backToMainMenu();
            default -> System.out.println("Invalid option. Please try again.");
        }
    } while (optionTeam != 6);
    }}

//    private static boolean usernameExistsInMemory(String username) {
//        return users.stream().anyMatch(user -> user.getUsername().equals(username));
//    }

//    private static String getPassword() {
//        String password;
//        do {
//            System.out.println("Password:\n");
//            password = scanner.nextLine();
//        } while (password == null || password.trim().isEmpty());
//        return password;
//    }
//}