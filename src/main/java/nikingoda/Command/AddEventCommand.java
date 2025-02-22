package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Event;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private final String command;

    /**
     * command to add EventTask
     *
     * @param command description
     */
    public AddEventCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            String subCommand = command.substring(6);
            String[] subCommandSplit = subCommand.split(" /from ");
            String description = subCommandSplit[0].trim();
            String[] timeCommandSplit = subCommandSplit[1].split(" /to ");
            String begin = timeCommandSplit[0].trim();
            String end = timeCommandSplit[1].trim();
            if (description.isBlank()) {
                throw new NikingodaException("Description must not be blank!!!");
            }
            if (begin.isBlank()) {
                throw new NikingodaException("please add begin time");
            }
            if (end.isBlank()) {
                throw new NikingodaException("please add end time");
            }
            ui.add(tasks, new Event(description, begin, end));
            storage.saveTask(tasks);
        } catch (DateTimeParseException e) {
            throw new NikingodaException("Invalid format, begin time or end time should be in form: HHmm dd/mm/yyyy");
        } catch (Exception e) {
            throw new NikingodaException("Invalid format.\nShould be: event <description> /from <begin_time> /to <end_time>");
        }
    }
}
