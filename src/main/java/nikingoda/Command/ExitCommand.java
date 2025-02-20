package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class ExitCommand extends Command{
    /**
     * Command to exit chatbot
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException {
        storage.saveTask(tasks);
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
