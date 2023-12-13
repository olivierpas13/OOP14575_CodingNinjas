package ec.edu.espe.dailyDev.model;

import ec.edu.espe.dailyDev.view.LandingPage;
import com.google.gson.Gson;
import ec.edu.espe.dailyDev.utils.PasswordHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author CodingNinjas 
 */

public class User {

    private final UUID id;
    private String username;
    private String encryptedPassword;
    private ArrayList<Task> assignedTasks;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", encryptedPassword=" + encryptedPassword + ", assignedTasks=" + assignedTasks + '}';
    }
    

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.encryptedPassword = PasswordHandler.encryptPassword(password, 0);
    }

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.encryptedPassword = password;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        // Desencriptar la contraseña después de leer desde el JSON
        String decryptedPassword = PasswordHandler.decryptPassword(user.encryptedPassword, 0);
        user.encryptedPassword = decryptedPassword;

        return user;
    }
    
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("user_data.json", true))) {
            // Encriptar la contraseña antes de guardarla en el JSON
            String encryptedPassword = PasswordHandler.encryptPassword(this.encryptedPassword, 0);
            this.encryptedPassword = encryptedPassword;

            // Crear un objeto temporal solo para el guardado
            User tempUser = new User(this.id, this.username, this.encryptedPassword);

            String jsonData = tempUser.toJson();
            writer.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromJson(line);

                // No necesitas desencriptar la contraseña aquí; se hará en el método fromJson

                // Verificar la contraseña
                if (user.getUsername().equals(username) && user.encryptedPassword.equals(password)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid username or password");
    }
    
    private static boolean isUsernameTaken(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromJson(line);
                if (user.getUsername().equals(username)) {
                    return true; // El usuario ya existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El usuario no existe
    }
    
    public static User register(String username, String password) {
        // Encriptar la contraseña antes de guardarla en el archivo JSON
        String encryptedPassword = PasswordHandler.encryptPassword(password, 0);

        // Verificar si el usuario ya existe
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            // Volver al menú principal
            LandingPage.showLandingPage();
            return null;  // Aseguramos que no se retorne un nuevo usuario en este caso
        }

        User newUser = new User(username, encryptedPassword);
        newUser.saveToFile();
        return newUser;
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

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(ArrayList<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}