package ec.edu.espe.dailyDev.model;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.bson.Document;

/**
 *
 * @author Team Number: 4 - CodingNinjas
 */
public class Meeting {

    private static final Scanner consoleScanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final Gson gson = new Gson();
    private UUID id;
    private String title;
    private Date startTime;
    private Date endTime;
    private List<User> participants;
    private static final List<User> userList = new ArrayList<>();

    @Override
    public String toString() {
        return "Meeting{" + "id=" + id + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

    private Meeting(String title, Date startTime, Date endTime) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;

    }

    public UUID getId() {
        return id;
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

    private static List<User> getUserList() {
        return userList;
    }

    public Document toDocument() {

        Document newMeetingDocument = new Document()
                .append("id", getId())
                .append("title", getTitle() != null ? getTitle() : "");
        if (getStartTime() != null) {
            newMeetingDocument.append("startTime", getStartTime());
        }
        if (getEndTime() != null) {
            newMeetingDocument.append("endTime", getEndTime());
        }
        return newMeetingDocument;
    }

    public static Meeting fromDocument(Document document) {
        UUID id = (UUID) document.get("Id");
        String title = document.getString("Title");
        Date startTime = document.getDate("StartTime");
        Date endTime = document.getDate("EndTime");
        Meeting meeting = new Meeting(title, startTime, endTime);

        return meeting;
    }
}
