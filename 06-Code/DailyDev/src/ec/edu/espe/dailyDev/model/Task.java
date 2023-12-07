
package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
public class Task {
    private UUID id;
    private String name;
    private String description;
    private Date dueDate;
    private Date creationDate;

    public Task(UUID id, String name, String description, Date dueDate, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", dueDate=" + getDueDate() + ", creationDate=" + getCreationDate() + '}';
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
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    
     
}
