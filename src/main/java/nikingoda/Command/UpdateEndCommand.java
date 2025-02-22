package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateEndCommand extends UpdateCommand{
    protected final String newEnd;

    public UpdateEndCommand(int id, String newEnd) {
        super(id);
        this.newEnd = newEnd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change form 1-indexed to 0-indexed
            ui.updateTask(tasks.updateTaskEnd(id, newEnd));
        } catch (DateTimeParseException e) {
            throw new NikingodaException(" End time should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
