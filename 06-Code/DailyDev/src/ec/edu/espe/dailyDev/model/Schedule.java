/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Olivier Paspuel
 */
public class Schedule {
    private List<Task> tasks;
    private List<Reminder> reminders;
    private List<Date> dates;

    public Schedule(List<Task> tasks, List<Reminder> reminders, List<Date> dates) {
        this.tasks = tasks;
        this.reminders = reminders;
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Schedule{" + "tasks=" + tasks + ", reminders=" + reminders + ", dates=" + dates + '}';
    }

}
