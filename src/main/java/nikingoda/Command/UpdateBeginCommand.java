package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class UpdateBeginCommand extends UpdateCommand{
    protected final String newBegin;
    public UpdateBeginCommand(int id, String newBegin) {
        super(id);
        this.newBegin = newBegin;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        int id = this.getId() - 1; //change from 1-indexed to 0-indexed
        ui.updateTask(tasks.updateTaskBegin(id, newBegin));
    }
}
