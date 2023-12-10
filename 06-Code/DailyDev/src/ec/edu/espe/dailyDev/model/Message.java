
package ec.edu.espe.dailyDev.model;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
public class Message {
    UUID id;
    String title;
    String description;
    Date creationDate;
    Date assignedDate;
    boolean isApproved;
}
