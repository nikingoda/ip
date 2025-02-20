package nikingoda.Parser;

import nikingoda.Command.Command;
import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Task.Deadline;
import nikingoda.Task.Event;
import nikingoda.Task.Task;
import nikingoda.Task.Todo;

public class Parser {
    public static Task parseTask(String line) {
        String[] lineSplitted = line.split("\\|");
        boolean isDone = lineSplitted[1].equals("1");
        if (lineSplitted[0].equals("T")) {
            return new Todo(lineSplitted[2], isDone);
        } else if (lineSplitted[0].equals("D")) {
            return new Deadline(lineSplitted[2], lineSplitted[3], isDone);
        } else {
            return new Event(lineSplitted[2], lineSplitted[3], lineSplitted[4], isDone);
        }
    }

    public static Command parse(String command) throws nikingodaException {
        return Command.findCommand(command);
    }
}
