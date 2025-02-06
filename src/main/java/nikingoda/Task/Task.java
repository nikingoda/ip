package nikingoda.Task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return switch (this) {
            case Todo todo -> "T|" + done + "|" + this.description;
            case Deadline deadline -> "D|" + done + "|" + this.description + "|" + deadline.deadline;
            case Event event -> "E|" + done + "|" + this.description + "|" + event.begin + "|" + event.end;
            default -> "";
        };
    }


    public void mark() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void unmark() {
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
