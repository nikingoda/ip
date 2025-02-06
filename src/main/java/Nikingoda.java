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
        while (!isExit) {
            try {
                ui.readCommand(storage, taskList);
            } catch (nikingodaException e) {
                ui.showError(e);
            } finally {
                isExit = ui.isExit();
            }
        }
    }
}
