package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateBeginCommand extends UpdateCommand{
    protected final String newBegin;
    public UpdateBeginCommand(int id, String newBegin) {
        super(id);
        this.newBegin = newBegin;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change from 1-indexed to 0-indexed
            ui.updateTask(tasks.updateTaskBegin(id, newBegin));
        } catch (DateTimeParseException e) {
            throw new NikingodaException("Begin time should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
