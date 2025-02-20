package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class UnmarkCommand extends Command{
    private final int id;

    /**
     * command to unmark task with id
     * @param id id
     */
    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        try {
            ui.unmark(tasks, id - 1);             //Id must be transform to 0-indexed
            storage.saveTask(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new nikingodaException(" There's no task with your id");
        }
    }
}
