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

    private UUID id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Task> tasks;

    public Sprint(UUID id, String name, Date startDate, Date endDate, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Sprint{" + "id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", tasks=" + tasks + '}';
    }
    
}
