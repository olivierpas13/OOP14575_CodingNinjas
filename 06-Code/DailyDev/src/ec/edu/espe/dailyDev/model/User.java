package ec.edu.espe.dailyDev.model;

import ec.edu.espe.dailyDev.view.LandingPage;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.dailyDev.utils.FileHandler;
import ec.edu.espe.dailyDev.utils.PasswordHandler;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author CodingNinjas
 */
public class User {

    private final UUID id;
    private String username;
    private final String encryptedPassword;
    private ArrayList<Task> assignedTasks;
    private static User currentUser; 

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", encryptedPassword=" + encryptedPassword + ", assignedTasks=" + assignedTasks + '}';
    }

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.encryptedPassword = PasswordHandler.encryptPassword(password);
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
        
    public static UUID getCurrentUserId() {
        if (currentUser != null) {
            return currentUser.getId();
        }
        return null;  
    }

    public static ArrayList<User> getUsersFromFile() {
        return FileHandler.readFile("users.json", new TypeToken<ArrayList<User>>() {
        }.getType());
    }
    
    public static User login(String username, String password) throws Exception {
        ArrayList<User> users = getUsersFromFile();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getEncryptedPassword().equals(PasswordHandler.encryptPassword(password))) {
                currentUser = user;  // Almacena el usuario actualmente conectado
                return user;
            }
        }
        System.err.println("Invalid credentials for username: " + username);
        return null;
    }

    private static boolean isUsernameTaken(String username) {
        ArrayList<User> users = getUsersFromFile();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true; // Username is taken
            }
        }
        return false;
    }

    public static User register(String username, String password) throws Exception {
        ArrayList<User> users = getUsersFromFile();

        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            // Volver al menú principal
            LandingPage.showLandingPage();
            return null;  // Aseguramos que no se retorne un nuevo usuario en este caso
        }
        User newUser = new User(username, password);

        users.add(newUser);

        FileHandler.writeFile("users.json", users);
        return newUser;
    }

    public static void logout() {
        currentUser = null;  
    }
        
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(ArrayList<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}