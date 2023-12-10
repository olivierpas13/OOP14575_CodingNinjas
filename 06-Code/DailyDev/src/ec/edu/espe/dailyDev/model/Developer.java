
package ec.edu.espe.dailyDev.model;

/**
 *
 * @author Olivier Paspuel
 */
public class Developer extends User {
    String role = "dev";
    Team team;

    public Developer(Team team, String username, String password) {
        super(username, password);
        this.team = team;
    }
    
     /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }
    
}
