package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.time.format.DateTimeParseException;

public class UpdateDeadlineCommand extends UpdateCommand {
    protected final String newDeadline;
    public UpdateDeadlineCommand(int id, String newDeadline) {
        super(id);
        this.newDeadline = newDeadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            int id = this.getId() - 1; //change from 1-indexed to 0-indexed
            ui.updateTask(tasks.updateTaskDeadline(id, newDeadline));
        } catch (DateTimeParseException e) {
            throw new NikingodaException(" Deadline should be in form: 'HHmm dd/mm/yyyy'");
        }
    }
}
