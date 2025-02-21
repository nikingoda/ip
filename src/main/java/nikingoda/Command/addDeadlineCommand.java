package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Deadline;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class addDeadlineCommand extends Command{
    private final String command;

    /**
     * command to add DeadlineTask
     * @param command command
     */
    public addDeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        try {
            String subCommand = command.substring(9);
            String[] subCommandSplit = subCommand.split(" /by ");
            String description = subCommandSplit[0].trim();
            String deadline = subCommandSplit[1].trim();
            if (description.isBlank()) {
                throw new nikingodaException("Description must not be blank!!!");
            }
            if (deadline.isBlank()) {
                throw new nikingodaException("please add deadline");
            }
            ui.add(tasks, new Deadline(description, deadline));
            storage.saveTask(tasks);
        } catch (DateTimeParseException e) {
            throw new nikingodaException("Invalid format, deadline should be in form: HHmm dd/mm/yyyy");
        } catch (Exception e) {
            throw new nikingodaException("Invalid format.\nShould be: event <description> /by <deadline>");
        }
    }
}
