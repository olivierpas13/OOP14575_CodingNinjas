/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.dailyDev.model;

import ec.edu.espe.dailyDev.utils.PasswordHandler;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
public class User {

    private final UUID id;
    private String username;
    private String encryptedPassword;
    private ArrayList<Task> assignedTasks;

    private User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.encryptedPassword = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", encryptedPassword=" + encryptedPassword + ", assignedTasks=" + assignedTasks + '}';
    }

    public static User login(String username, String password) {
        // TODO
        PasswordHandler.decryptPassword(password);

        System.out.println("Logged in\n");
        return new User("test", "password");
    }

    public static User register(String username, String password) {
        String encryptedPassword = PasswordHandler.encryptPassword(password);
        return new User(username, encryptedPassword);

    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the encryptedPassword
     */
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * @param encryptedPassword the encryptedPassword to set
     */
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    /**
     * @return the assignedTasks
     */
    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    /**
     * @param assignedTasks the assignedTasks to set
     */
    public void setAssignedTasks(ArrayList<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

}
