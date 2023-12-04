/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
public class Meeting {
    UUID id;
    String title;
    Date startTime;
    Date endTime;
    List<User> participants;
    String connectionLink;
}
