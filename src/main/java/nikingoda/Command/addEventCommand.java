package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Event;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class addEventCommand extends Command{
    private final String command;

    /**
     * command to add EventTask
     * @param command description
     */
    public addEventCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        try {
            String subCommand = command.substring(6);
            String[] subCommandSplit = subCommand.split(" /from ");
            String description = subCommandSplit[0].trim();
            String[] timeCommandSplit = subCommandSplit[1].split(" /to ");
            String begin = timeCommandSplit[0].trim();
            String end = timeCommandSplit[1].trim();
            if (description.isBlank()) {
                throw new nikingodaException("Description must not be blank!!!");
            }
            if (begin.isBlank()) {
                throw new nikingodaException("please add begin time");
            }
            if (end.isBlank()) {
                throw new nikingodaException("please add end time");
            }
            ui.add(tasks, new Event(description, begin, end));
        } catch (IndexOutOfBoundsException e) {
            throw new nikingodaException("Invalid format.\nShould be: event <description> /from <begin_time> /to <end_time>");
        } catch (Exception e) {
            throw new nikingodaException(e.getMessage());
        }
    }
}
