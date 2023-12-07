
package ec.edu.espe.dailyDev.model;

/**
 *
 * @author Olivier Paspuel
 */
public class Project {
    Team AssignedTeam;
    String name;
    Task[] tasks;

    public Project(Team AssignedTeam, String name, Task[] tasks) {
        this.AssignedTeam = AssignedTeam;
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" + "AssignedTeam=" + AssignedTeam + ", name=" + name + ", tasks=" + tasks + '}';
    }
    
    
    
    
}
