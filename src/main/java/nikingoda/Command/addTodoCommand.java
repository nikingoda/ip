package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Todo;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class addTodoCommand extends Command{
    private final String command;

    /**
     * command to add TodoTask
     * @param command command
     */
    public addTodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        try {
            String description = command.substring(5).trim();
            if (description.isBlank()) {
                throw new nikingodaException("Description must not be blank!!!");
            }
            ui.add(tasks, new Todo(description));
            storage.saveTask(tasks);
        } catch (Exception e) {
            throw new nikingodaException("Invalid command. \nShould be: todo <description>");
        }
    }
}
