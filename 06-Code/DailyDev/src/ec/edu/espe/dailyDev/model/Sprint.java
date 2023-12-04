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
public class Sprint {

    UUID id;
    String name;
    Date startDate;
    Date endDate;
    List<Task> tasks;
}
