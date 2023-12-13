package ec.edu.espe.dailyDev.utils;

import java.util.Scanner;

/**
 *
 * @author revil
 */

public class MenuUtils {
    private static Scanner scanner = new Scanner(System.in);

    static int getUserOption(String entityName) {
        System.out.println("\n" + entityName + " Menu\n");
        System.out.println("1. Create " + entityName + "\n2. Show " + entityName +
                "s\n3. Update " + entityName + "\n4. Complete " + entityName +
                "\n5. Delete " + entityName + "\n6. Back to Main Menu");

        return scanner.nextInt();
    }

    public static void backToMainMenu() {
        System.out.println("Returning to Main Menu...");
    }
}