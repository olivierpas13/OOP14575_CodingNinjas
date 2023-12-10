package ec.edu.espe.dailyDev.model;

import java.util.Date;

/**
 *
 * @author Olivier Paspuel
 */
public class Reminder {
    private String name;
    private String description;
    private Date remindDate;
    private Date deadline;
    private Date creationTime;
 //   private Task task = associatedTask;

    public Reminder(String name, String description, Date remindDate, Date deadline, Date creationTime) {
        this.name = name;
        this.description = description;
        this.remindDate = remindDate;
        this.deadline = deadline;
        this.creationTime = creationTime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the remindDate
     */
    public Date getRemindDate() {
        return remindDate;
    }

    /**
     * @param remindDate the remindDate to set
     */
    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

   
    
}
