public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String toFile() {
        int done = this.isDone ? 1 : 0;
        if (this instanceof Todo) {
            return "T|" + done + "|" + this.description;
        } else if (this instanceof Deadline) {
            return "D|" + done + "|" + this.description + "|" + ((Deadline) this).deadline;
        } else if (this instanceof Event) {
            return "E|" + done + "|" + this.description + "|" + ((Event) this).begin + "|" + ((Event) this).end;
        } else {
            return "";
        }
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
