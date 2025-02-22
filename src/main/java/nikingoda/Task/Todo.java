package nikingoda.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "T|" + done + "|" + this.description;
    }
}
