package nikingoda.Task;

public class Event extends Task {
    protected String begin;
    protected String end;
    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }
    public Event(String description, String begin, String end, Boolean done) {
        super(description, done);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + this.begin + " to: " + this.end + ")";
    }
}
