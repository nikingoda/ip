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

    /**
     * constructor for event
     * @param description description
     * @param begin begin_time
     * @param end end_time
     */
    public Event(String description, String begin, String end) {
        super(description);
        this.begin = LocalDateTime.parse(begin, inputForm);
        this.end = LocalDateTime.parse(end, inputForm);
    }

    /**
     * constructor for event
     * @param description description
     * @param begin begin_time
     * @param end end_time
     * @param done status whether it is done
     */
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

    /**
     * function to update begin_time
     * @param newBegin new begin_time
     */
    public void updateBegin(String newBegin) {
        this.begin = LocalDateTime.parse(newBegin, inputForm);
    }

    /**
     * function to update end_time
     * @param newEnd new end_time
     */
    public void updateEnd(String newEnd) {
        this.end = LocalDateTime.parse(newEnd, inputForm);
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
