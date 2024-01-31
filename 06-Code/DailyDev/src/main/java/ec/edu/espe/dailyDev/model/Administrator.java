package ec.edu.espe.dailyDev.model;

import com.google.gson.Gson;
import static ec.edu.espe.dailyDev.model.User.currentUser;
import ec.edu.espe.dailyDev.utils.PasswordHandler;
import static ec.edu.espe.dailyDev.utils.ValidationHandler.isUsernameUnique;
import ec.edu.espe.dailyDev.view.LandingPage;
import java.util.ArrayList;
import java.util.UUID;
import ec.edu.espe.dailyDev.utils.MongoDBHandler;
import org.bson.Document;

/**
 *
 * @author Team Number: 4 - CodingNinjas
 */
public class Administrator extends User {

    private final String role = "admin";
    private final UUID organizationCode;
    private static String nameCollection;
    
    public Administrator(String username, String password, UUID orgCode) {
        super(username, password);
        this.organizationCode = orgCode;
    }

    @Override
    public String toString() {
        return "Admin{" + "role=" + role + ", organizationCode=" + organizationCode + '}';
    }

    public static ArrayList<Administrator> getAdminsFromFile() {

        return User.getUsersFromFile("admin");
    }

    public static Administrator registerAdmin(String username, String password) {
        String collectionName = "Admin";
        ArrayList<Administrator> admins = getAdminsFromFile();

        try {
            if (!isUsernameUnique(username, "admin")) {
                System.out.println("Username already exists. Please choose a different one.");
                LandingPage.showLandingPage();
                return null;
            }

            // TODO validate organizationName is unique;
            Organization org = Organization.createOrganization();

            Administrator newAdmin = new Administrator(username, password, org.getOrganizationCode());

            admins.add(newAdmin);

            MongoDBHandler mdbHandler = new MongoDBHandler();
            
            Document adminDocument = new Document("username", newAdmin.getUsername())
                    .append("password", newAdmin.getEncryptedPassword()) 
                    .append("organizationCode", newAdmin.getOrganizationCode().toString());
            
                    mdbHandler.createDocument(collectionName,Document.parse(new Gson().toJson(newAdmin)));

            return newAdmin;
        } catch (Exception e) {
            System.err.println("Error registering administrator: " + e.getMessage());
            return null;
        }
    }

    public static User loginAdmin(String username, String password) throws InvalidCredentialsException {
        ArrayList<Administrator> admins = getAdminsFromFile();
        for (Administrator admin : admins) {
            if (admin.getUsername().equals(username) && admin.getEncryptedPassword().equals(PasswordHandler.encryptPassword(password))) {
                currentUser = admin;
                return admin;
            }
        }
        throw new InvalidCredentialsException("Invalid credentials for username: " + username);
    }

    public UUID getOrganizationCode() {
        return organizationCode;
    }

}
