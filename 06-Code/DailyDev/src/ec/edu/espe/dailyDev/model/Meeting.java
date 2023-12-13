package ec.edu.espe.dailyDev.model;

import ec.edu.espe.dailyDev.utils.MenuUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.util.UUID;

public class Meeting {

    private static final Scanner consoleScanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private UUID id;
    private String title;
    private Date startTime;
    private Date endTime;
    private List<User> participants;

    private static final List<Meeting> meetingsList = new ArrayList<>();
    private static final List<User> userList = new ArrayList<>();

    @Override
    public String toString() {
        return "Meeting{" + "id=" + id + ", title='" + title + '\'' + ", startTime=" + startTime +
                ", endTime=" + endTime + ", participants=" + participants + '}';
    }

    private Meeting(String title, Date startTime, Date endTime, List<User> participants) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }
    
    public static void create() {
        System.out.println("Creating a new meeting...");
  
        System.out.println("Meeting created!");
    }

    public static void show() {
        System.out.println("Showing meetings...");
        System.out.println("No meetings found.");  
        MenuUtils.backToMainMenu();
    }

    public static void update() {
        System.out.println("Updating a meeting...");
 
        System.out.println("Meeting updated!");
        MenuUtils.backToMainMenu();
    }

    public static void complete() {
        System.out.println("Completing a meeting...");
 
        System.out.println("Meeting completed!");
        MenuUtils.backToMainMenu();
    }

    public static void delete() {
        System.out.println("Deleting a meeting...");

        System.out.println("Meeting deleted!");
        MenuUtils.backToMainMenu();
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

    private static List<Meeting> getMeetingsList() {
        return meetingsList;
    }

    private static List<User> getUserList() {
        return userList;
    }

    public static void register() {
        System.out.println("---Meeting register...");
        String title = getUserInput("title");
        Date startTime;
        Date endTime;

        do {
            startTime = getDateTimeInput("start time");
            if (shouldExit(startTime)) {
                System.out.println("Registration canceled. Exiting...");
                return;
            }

            endTime = getDateTimeInput("end Time");
            if (shouldExit(endTime)) {
                System.out.println("Registration canceled. Exiting...");
                return;
            }

            if (!isValidMeetingTime(startTime, endTime)) {
                System.out.println("Invalid dates. Make sure the end time is after start time and start time is not in the past.");
            }
        } while (!isValidMeetingTime(startTime, endTime));

        List<User> participants = getParticipantsList();

        if (participants != null) {
            Meeting newMeeting = createMeeting(title, startTime, endTime, participants);
            System.out.println("Meeting registered successfully! ID: " + newMeeting.getId());
        } else {
            System.out.println("Make sure to have registered participants before creating a meeting.");
        }
    }

    private static boolean shouldExit(Date date) {
        System.out.println("Press Enter to continue or type anything to cancel registration and exit.");
        String userInput = consoleScanner.nextLine().trim();
        return !userInput.isEmpty();
    }

    private static boolean isValidMeetingTime(Date startTime, Date endTime) {
        return !endTime.before(startTime) && startTime.after(new Date());
    }

    private static Meeting createMeeting(String title, Date startTime, Date endTime, List<User> participants) {
        Meeting newMeeting = new Meeting(title, startTime, endTime, participants);
        getMeetingsList().add(newMeeting);
        return newMeeting;
    }

    private static boolean areDatesInSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date1).equals(sdf.format(date2));
    }

    private static List<User> getParticipantsList() {
        List<User> participants = new ArrayList<>();

        int numberOfParticipants = Integer.parseInt(getUserInput("number of participants"));
        for (int i = 0; i < numberOfParticipants; i++) {
            User selectedUser = selectUser();
            if (selectedUser != null) {
                participants.add(selectedUser);
            } else {
                System.out.println("User not found. Please select an existing user.");
                return null;
            }
        }

        return participants;
    }

    private static User selectUser() {
        System.out.println("Select a user by name or ID:");
        String userInput = consoleScanner.nextLine();

        for (User user : getUserList()) {
            if (user.getUsername().equalsIgnoreCase(userInput) || user.getId().toString().equalsIgnoreCase(userInput)) {
                return user;
            }
        }
        return null;
    }

    public static String getUserInput(String message) {
        System.out.println("Enter " + message + ": ");
        return consoleScanner.nextLine();
    }

    public static Date getDateTimeInput(String message) {
        System.out.println("Enter " + message + " (format: DD/MM/YYYY HH:mm):");

        while (true) {
            try {
                String userInput = consoleScanner.nextLine();
                if (userInput.trim().isEmpty()) {
                    throw new ParseException("Empty input", 0);
                }

                Date date = DATE_FORMATTER.parse(userInput);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, 30);

                if (isValidDateTimeRange(date, cal.getTime())) {
                    return date;
                } else {
                    throw new ParseException("Invalid date/time. Maximum allowed range is 30 days from now.", 0);
                }
            } catch (ParseException e) {
                System.out.println("Invalid date/time format or out of range. Please enter the date/time in the correct format.");
            }
        }
    }

    private static boolean isValidDateTimeRange(Date date, Date maxDate) {
        return date.after(new Date()) && date.before(maxDate);
    }

}
