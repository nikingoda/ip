package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class UpdateEndCommand extends UpdateCommand{
    protected final String newEnd;

    public UpdateEndCommand(int id, String newEnd) {
        super(id);
        this.newEnd = newEnd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        int id = this.getId() - 1; //change form 1-indexed to 0-indexed
        ui.updateTask(tasks.updateTaskEnd(id, newEnd));
    }
}
