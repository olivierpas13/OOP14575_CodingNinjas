public class Stats {
    private int totalTasks;
    private int completedTasks;
    private int overdueTasks;
    private int highPriorityTasks;
    private double completionRate;

    public Stats(int totalTasks, int completedTasks, int overdueTasks, int highPriorityTasks) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.overdueTasks = overdueTasks;
        this.highPriorityTasks = highPriorityTasks;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getOverdueTasks() {
        return overdueTasks;
    }

    public int getHighPriorityTasks() {
        return highPriorityTasks;
    }
}
