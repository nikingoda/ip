package nikingoda;

import nikingoda.Command.Command;
import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Parser.Parser;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class Nikingoda {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Nikingoda(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.loadTasks());
        } catch (nikingodaException e) {
            this.ui.showError(e);
        }
    }

    public static void main(String[] args) {
        Nikingoda nikingoda = new Nikingoda("data/tasks.txt");
        nikingoda.run();
    }


    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String command = ui.read();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (nikingodaException e) {
                ui.showError(e);
            }
        }
    }
}
