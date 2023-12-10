package ec.edu.espe.dailyDev.model;

/**
 * Admin class, extends User
 * @author Olivier Paspuel
 */
public class Admin extends User {
    
    private String role = "admin";

    public Admin(String username, String password) {
        super(username, password);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin{");
        sb.append("role=").append(role);
        sb.append('}');
        return sb.toString();
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
}
