package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Deadline;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command{
    private final String command;

    /**
     * command to add DeadlineTask
     * @param command command
     */
    public AddDeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            String subCommand = command.substring(9);
            String[] subCommandSplit = subCommand.split(" /by ");
            String description = subCommandSplit[0].trim();
            String deadline = subCommandSplit[1].trim();
            if (description.isBlank()) {
                throw new NikingodaException("Description must not be blank!!!");
            }
            if (deadline.isBlank()) {
                throw new NikingodaException("please add deadline");
            }
            ui.add(tasks, new Deadline(description, deadline));
            storage.saveTask(tasks);
        } catch (DateTimeParseException e) {
            throw new NikingodaException("Invalid format, deadline should be in form: HHmm dd/mm/yyyy");
        } catch (Exception e) {
            throw new NikingodaException("Invalid format.\nShould be: event <description> /by <deadline>");
        }
    }
}
