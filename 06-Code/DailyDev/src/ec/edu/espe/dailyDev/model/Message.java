package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
public class Message {

    private UUID id;
    private String title;
    private String description;
    private Date creationDate;
    private Date assignedDate;
    private boolean isApproved;

    public Message(UUID id, String title, String description, Date creationDate, Date assignedDate, boolean isApproved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.assignedDate = assignedDate;
        this.isApproved = isApproved;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * @return the assignedDate
     */
    public Date getAssignedDate() {
        return assignedDate;
    }

    /**
     * @param assignedDate the assignedDate to set
     */
    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    /**
     * @return the isApproved
     */
    public boolean isIsApproved() {
        return isApproved;
    }

    /**
     * @param isApproved the isApproved to set
     */
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}
