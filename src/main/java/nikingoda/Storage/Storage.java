package nikingoda.Storage;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Parser.Parser;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws nikingodaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File taskFile = new File(filePath);
        if (!taskFile.exists()) {
            return tasks;
        }
        try (Scanner scanTask = new Scanner(taskFile)) {
            while (scanTask.hasNextLine()) {
                String nextLine = scanTask.nextLine();
                tasks.add(Parser.parseTask(nextLine));
            }
        } catch (Exception e) {
            throw new nikingodaException(e.getMessage());
        }
        return tasks;
    }

    public void saveTask(TaskList tasks) throws nikingodaException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            throw new nikingodaException("Cannot save task");
        }
    }
}