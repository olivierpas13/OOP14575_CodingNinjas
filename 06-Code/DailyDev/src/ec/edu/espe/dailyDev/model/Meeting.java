package ec.edu.espe.dailyDev.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.UUID;

/**
 *
 * author CodingNinjas
 */
public class Meeting {

    static Scanner consoleScanner = new Scanner(System.in);

    private UUID id;
    private String title;
    private Date startTime;
    private Date endTime;
    private List<User> participants;

    // Nuevo: ArrayList para almacenar los registros de reuniones
    private static List<Meeting> meetingsList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();

    @Override
    public String toString() {
        return "Meeting{" + "id=" + id + ", title='" + title + '\'' + ", startTime=" + startTime + ", endTime=" + endTime + ", participants=" + participants + '}';
    }

    // Constructor privado para crear una reunión con ID aleatorio
    private Meeting(String title, Date startTime, Date endTime, List<User> participants) {
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

// Nuevo: Método para registrar una reunión con ID aleatorio
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
                return; // Salir si el usuario lo elige
            }

            if (endTime.before(startTime) || startTime.before(new Date())) {
                System.out.println("Invalid dates. Make sure the end time is after start time and start time is not in the past.");
            } else if (endTime.equals(startTime)) {
                System.out.println("End time should be after start time. Please enter a valid end time.");
            } else if (!areDatesInSameDay(startTime, endTime)) {
                System.out.println("Start time and end time should be in the same day. Please enter valid dates.");
            }
        } while (endTime.before(startTime) || startTime.before(new Date()) || endTime.equals(startTime) || !areDatesInSameDay(startTime, endTime));

        List<User> participants = getParticipantsList();

        if (participants != null) {
            Meeting newMeeting = new Meeting(title, startTime, endTime, participants);
            meetingsList.add(newMeeting);
            System.out.println("Meeting registered successfully!");
        } else {
            System.out.println("Make sure to have registered participants before creating a meeting.");
        }
    }

    private static boolean shouldExit(Date date) {
        System.out.println("Press Enter to continue or type anything to cancel registration and exit.");
        String userInput = consoleScanner.nextLine().trim();
        return !userInput.isEmpty();
    }

// Nuevo: Método para verificar si dos fechas están en el mismo día
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
    // Método para seleccionar un usuario existente por nombre de usuario o ID

    private static User selectUser() {
        System.out.println("Select a user by name or ID:");
        String userInput = consoleScanner.nextLine();

        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(userInput) || user.getId().toString().equalsIgnoreCase(userInput)) {
                return user;
            }
        }
        return null;
    }

    // Método para obtener la entrada del usuario
    public static String getUserInput(String message) {
        System.out.println("Enter " + message + ": ");
        return consoleScanner.nextLine();
    }

    public static Date getDateTimeInput(String message) {
        System.out.println("Enter " + message + " (format: DD/MM/YYYY HH:mm):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5")); // Configurar la zona horaria de Ecuador

        while (true) {
            try {
                String userInput = consoleScanner.nextLine();
                if (userInput.trim().isEmpty()) {
                    throw new ParseException("Empty input",0);
                }

                Date date = dateFormat.parse(userInput);

                // Verificar el rango de fechas (30 días)
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, 30);

                // Verificar el rango de minutos (60 minutos), meses (12 meses) y horas (24 horas)
                Calendar calMaxMinutes = Calendar.getInstance();
                calMaxMinutes.add(Calendar.MINUTE, 60);

                Calendar calMaxMonths = Calendar.getInstance();
                calMaxMonths.add(Calendar.MONTH, 12);

                Calendar calMaxHours = Calendar.getInstance();
                calMaxHours.add(Calendar.HOUR_OF_DAY, 24);

                if (cal.getTime().after(new Date())
                        && calMaxMinutes.getTime().after(date)
                        && calMaxMonths.getTime().after(date)
                        && calMaxHours.getTime().after(date)) {
                    return date;
                } else {
                    throw new ParseException("Invalid date/time. Maximum allowed range is 30 days, 60 minutes, 12 months, and 24 hours from now.", 0);
                }
            } catch (ParseException e) {
                System.out.println("Invalid date/time format or out of range. Please enter the date/time in the correct format.");
            }
        }
    }

// Método para imprimir todas las reuniones registradas
    public static void printAllMeetings() {
        System.out.println("All Meetings:");
        for (Meeting meeting : meetingsList) {
            System.out.println(meeting);
        }
    }

    public static void printAllUsers() {
        System.out.println("--- All Users ---");
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
