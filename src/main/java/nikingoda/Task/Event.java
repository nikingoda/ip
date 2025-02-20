package nikingoda.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime begin;
    protected LocalDateTime end;

    /**
     * Custom DateTimeFormatter for input and output
     */
    private final DateTimeFormatter inputForm = DateTimeFormatter.ofPattern("HHmm d/M/yyyy");
    private final DateTimeFormatter outputForm = DateTimeFormatter.ofPattern("h:mm a, MMM d yyyy");
    public Event(String description, String begin, String end) {
        super(description);
        this.begin = LocalDateTime.parse(begin, inputForm);
        this.end = LocalDateTime.parse(end, inputForm);
    }
    public Event(String description, String begin, String end, Boolean done) {
        super(description, done);
        this.begin = LocalDateTime.parse(begin, inputForm);
        this.end = LocalDateTime.parse(end, inputForm);
    }

    /**
     * return begin date in input and output form
     */
    private String inputBegin() {
        return this.begin.format(inputForm);
    }
    private String outputBegin() {
        return this.begin.format(outputForm);
    }

    /**
     * return end date in input and output form
     */
    private String intputEnd() {
        return this.end.format(inputForm);
    }

    private String outputEnd() {
        return this.end.format(outputForm);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + this.outputBegin() + " to: " + this.outputEnd() + ")";
    }

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "E|" + done + "|" + this.description + "|" + this.inputBegin() + "|" + this.intputEnd();
    }
}
