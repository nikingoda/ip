public class Task {
    protected final int typeID;
    protected final static String[] types = new String[] {"T", "D", "E"};
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String deadline;

    protected String begin;
    protected String end;

    public void mark() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void unmark() {
        this.isDone = false;
    }

    public Task(String description, int typeID) {
        this.description = description;
        this.isDone = false;
        this.type = types[typeID];
        this.typeID = typeID;
    }

    public Task(String description, int typeID, String deadline) {
        this.description = description;
        this.isDone = false;
        this.type = types[typeID];
        this.typeID = typeID;
        this.deadline = deadline;
    }

    public Task(String description, int typeID, String begin, String end) {
        this.description = description;
        this.isDone = false;
        this.type = types[typeID];
        this.typeID = typeID;
        this.begin = begin;
        this.end = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        if(typeID == 0) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
        } else if (typeID == 1) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                    + " (by: " + this.deadline + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                    + " (from: " + this.begin + " to: " + this.end + ")";
        }
    }
}
