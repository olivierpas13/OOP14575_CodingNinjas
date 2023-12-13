package ec.edu.espe.dailyDev.model;

import ec.edu.espe.dailyDev.utils.PasswordHandler;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 *
 * @author CodingNinjas 
 */

public class User {

    private static byte[] hexStringToBytes(String salt) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private static User fromJson(String line) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private final UUID id;
    private String username;
    private String encryptedPassword;
    private String salt;
    private ArrayList<Task> assignedTasks;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", encryptedPassword=" + encryptedPassword + ", assignedTasks=" + assignedTasks + '}';
    }
    

    public User(String username, String password) {
    public User(String username, String encryptedPassword) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("user_data.json", true))) {
            // Guardar el usuario en el archivo JSON sin encriptar la contraseña
            String jsonData = toJsonWithoutEncryption();
            writer.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toJsonWithoutEncryption() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public static User login(String username, String password) {
    try (BufferedReader reader = new BufferedReader(new FileReader("user_data.json"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            User user = User.fromJson(line);

            // Verificar si el usuario es nulo antes de acceder a sus propiedades
            if (user != null && user.getUsername().equals(username)) {
                // Verificar la contraseña
                if (PasswordHandler.verifyPassword(password, user.getEncryptedPassword(), user.getSalt())) {
                    return user;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    throw new IllegalArgumentException("Invalid username or password");
}


    public static boolean verifyPassword(String password, String encryptedPassword, String salt) {
        try {
            String hashedPassword = encryptPasswordWithSalt(password, salt);
            return hashedPassword.equals(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error verifying password.");
        }
    }

    private static String encryptPasswordWithSalt(String password, String salt) {
        try {
            byte[] saltBytes = hexStringToBytes(salt);
            byte[] passwordWithSalt = new byte[password.length() + saltBytes.length];
            System.arraycopy(password.getBytes(), 0, passwordWithSalt, 0, password.length());
            System.arraycopy(saltBytes, 0, passwordWithSalt, password.length(), saltBytes.length);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(passwordWithSalt);

            StringBuilder hexPassword = new StringBuilder();
            for (byte b : hashedPassword) {
                hexPassword.append(String.format("%02x", b));
            }

            return hexPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting password with salt.");
        }
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(ArrayList<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}