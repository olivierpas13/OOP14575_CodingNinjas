package ec.edu.espe.dailyDev.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.dailyDev.utils.FileHandler;
import java.util.ArrayList;

/**
 *
 * @author CodingNinjas 
 */
public class Team {
    User[] users;
    String name;
    
        public static ArrayList<Team> getFromFile() {
        return FileHandler.readFile("artists.json", new TypeToken<ArrayList<Team>>() {
        }.getType());
    }

    
}
