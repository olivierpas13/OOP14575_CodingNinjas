<<<<<<< HEAD

=======
>>>>>>> a3c3d14373b8cf26ec2b9bb0f6f98e67fefba536
package ec.edu.espe.dailyDev.utils;

import ec.edu.espe.dailyDev.model.User;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author CodingNinjas 
 */

public class LandingPage {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showLandingPage() {
        int option;

        do {
            System.out.println("Daily Dev System\n");
            System.out.println("Select one option please\n");
            System.out.println("1. Login to an existing account\n2. Create a new account");

            option = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea pendiente

            switch (option) {
                case 1 -> showLogin();
                case 2 -> showRegistration();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 1 && option != 2);
    }

    private static void showLogin() {
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

    public static void main(String[] args) {
        showLandingPage();
    }
}
