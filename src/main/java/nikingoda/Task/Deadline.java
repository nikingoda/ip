package nikingoda.Task;

public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, Boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (by: " + this.deadline + ")";
    }

}
