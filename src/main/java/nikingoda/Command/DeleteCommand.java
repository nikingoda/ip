package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class DeleteCommand extends Command {
    private final int id;

    /**
     * Delete command to delete task with id
     * @param id id
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            ui.delete(tasks, id - 1);           //Id must be transform to 0-indexed
            storage.saveTask(tasks);
        } catch (NumberFormatException e) {
            throw new NikingodaException("Task id must be integer");
        } catch (IndexOutOfBoundsException e) {
            throw new NikingodaException("Cannot find task id");
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }
}
