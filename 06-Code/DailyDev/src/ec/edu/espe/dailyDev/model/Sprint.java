package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author CodingNinjas 
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
        return "Sprint{" + "id=" + getId() + ", name=" + getName() + ", startDate=" + getStartDate() + ", endDate=" + getEndDate() + ", tasks=" + getTasks() + '}';
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
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
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
}
