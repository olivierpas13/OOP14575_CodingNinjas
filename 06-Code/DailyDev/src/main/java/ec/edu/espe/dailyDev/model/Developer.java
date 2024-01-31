package ec.edu.espe.dailyDev.model;

import com.google.gson.Gson;
import static ec.edu.espe.dailyDev.model.User.currentUser;
import ec.edu.espe.dailyDev.utils.MongoDBHandler;
import ec.edu.espe.dailyDev.utils.PasswordHandler;
import ec.edu.espe.dailyDev.view.LandingPage;
import java.util.ArrayList;
import java.util.UUID;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author Team Number: 4 - CodingNinjas
 */

public class Developer extends User {

    public static User login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private final static MongoDBHandler mdbHandler = new MongoDBHandler();
    private final String role = "developer";
    @BsonProperty("organizationCode")
    private final String organizationCode;

    public Developer(String username, String password, String organizationCode) {
        super(username, password);
        this.organizationCode = organizationCode;

    }

    @Override
    public String toString() {
        return "Developer{" + "role=" + role + ", organizationCode=" + organizationCode + '}';
    }

    public static ArrayList<Developer> getDevsFromFile() {

        return User.getUsersFromFile("dev");
    }

    public static Developer registerDev(String username, String password, UUID orgCode) throws Exception {

        if (mdbHandler.findOneDoc("username", username, "Devs") != null) {
            System.out.println("Username already exists. Please choose a different one.");
            LandingPage.showLandingPage();
            return null;
        }

        if (mdbHandler.findOneDoc("organizationCode", orgCode, "Organizations") == null) {
            System.out.println("Organization doesn't exist.");
            LandingPage.showLandingPage();
            return null;

        }

        Developer newDeveloper = new Developer(username, password, orgCode.toString());

        Document developerDoc = Document.parse(new Gson().toJson(newDeveloper));
        mdbHandler.createDocument("Devs", developerDoc);
        return newDeveloper;
    }

    public static User loginDev(String username, String password) throws User.InvalidCredentialsException {
        ArrayList<Developer> devs = getDevsFromFile();
        for (Developer dev : devs) {
            if (dev.getUsername().equals(username) && dev.getEncryptedPassword().equals(PasswordHandler.encryptPassword(password))) {
                currentUser = dev;
                return dev;
            }
        }
        throw new User.InvalidCredentialsException("Invalid credentials for username: " + username);
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

}
