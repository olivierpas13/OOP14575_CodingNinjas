
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
        return "Task{" + "id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate + ", creationDate=" + creationDate + '}';
    }

     
}
