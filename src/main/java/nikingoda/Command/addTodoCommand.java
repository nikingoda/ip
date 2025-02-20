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
     * @param command description
     */
    public addTodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        try {
            String description = command.substring(5);
            if (description.isBlank()) {
                throw new nikingodaException("Description must not be blank!!!");
            }
            ui.add(tasks, new Todo(description));
        } catch (Exception e) {
            throw new nikingodaException(e.getMessage());
        }
    }
}
