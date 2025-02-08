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

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "D|" + done + "|" + this.description + "|" + this.deadline;
    }

}
