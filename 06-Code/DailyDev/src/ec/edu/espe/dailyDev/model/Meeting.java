
package ec.edu.espe.dailyDev.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author Olivier Paspuel
 */
//Carlos Ñato--
public class Meeting {
    static Scanner consoleScanner = new Scanner(System.in);

    private UUID id;
    private String title;
    private Date startTime;
    private Date endTime;
    private List<User> participants;

    @Override
    public String toString() {
        return "Meeting{" + "id=" + getId() + ", title=" + getTitle()  + ", startTime=" + getStartTime() + ", endTime=" + getEndTime() + ", participants=" + getParticipants()  + '}';
    }
    

    public Meeting(UUID id, String title, String objective, Date startTime, Date endTime, List<User> participants, String sketck) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }



    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


   
    public Date getStartTime() {
        return startTime;
    }

   
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

  
    public Date getEndTime() {
        return endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public List<User> getParticipants() {
        return participants;
    }


    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }




    
    
    public void register(){ 
        System.out.println("---Meeting register...");
        
    String title = getUserInput("title");
    Date startTime = getDateTimeInput("w");
        
        
     
    }
    public String getUserInput(String message){
        System.out.println("Enter "+ message + ": ");
        return consoleScanner.nextLine();
       
    }
    public Date getDateTimeInput(String message){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYY HH:mm");
        Date dateTime = null;
        
        
        
        
        
        return dateTime;
    
   }
}
