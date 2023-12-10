
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

        System.out.println("Daily Dev System\n");
        System.out.println("Select one option please\n");
        System.out.println("1. Login to an existing account\n2. Create a new account");

        do {
            option = scanner.nextInt();

        } while (option != 1 && option != 2);
        // do it on a while
        for (int i = 0; i < 50; i++) {
            System.out.print("\n\n\n");

        }

        scanner.nextLine();

        switch (option) {
            case 1 ->
                showLogin();
            case 2 ->
                showRegistration();
            default ->
                throw new AssertionError();
        }
    }

    private static User showLogin() {

        User user = null;

        System.out.println("Login\n");

        System.out.println("Username:\n");
        String username = scanner.nextLine();

        System.out.println("Password:\n");
        String password = scanner.nextLine();

        try {
            user = User.login(username, password);
        } catch (Exception e) {

            System.err.println(e);
        }
        return user;
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
                System.out.println("Succesful registration");
                showLogin();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
