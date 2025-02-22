package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Command to find task whose description contain keyword
     * @param keyword key word
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            ui.find(tasks, keyword);
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }
}
