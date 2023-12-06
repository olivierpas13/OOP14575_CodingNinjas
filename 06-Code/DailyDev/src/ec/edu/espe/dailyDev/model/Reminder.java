/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.dailyDev.model;

import java.util.Date;

/**
 *
 * @author Olivier Paspuel
 */
public class Reminder {
    String name;
    String description;
    Date remindDate;
    Date deadline;
    Date creationTime;
    Task task = associatedTask;
}
