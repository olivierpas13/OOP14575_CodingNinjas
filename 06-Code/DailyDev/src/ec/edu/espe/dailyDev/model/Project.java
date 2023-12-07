
package ec.edu.espe.dailyDev.model;

/**
 *
 * @author Olivier Paspuel
 */
public class Project {
    private Team AssignedTeam;
    private String name;
    private Task[] tasks;

    public Project(Team AssignedTeam, String name, Task[] tasks) {
        this.AssignedTeam = AssignedTeam;
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" + "AssignedTeam=" + getAssignedTeam() + ", name=" + getName() + ", tasks=" + getTasks() + '}';
    }

    /**
     * @return the AssignedTeam
     */
    public Team getAssignedTeam() {
        return AssignedTeam;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tasks
     */
    public Task[] getTasks() {
        return tasks;
    }

    public void setAssignedTeam(Team AssignedTeam) {
        this.AssignedTeam = AssignedTeam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }
    
    
    
    
}
