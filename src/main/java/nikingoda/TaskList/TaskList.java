package nikingoda.TaskList;

import nikingoda.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> taskContainsKeyword(String keyword) {
        keyword = keyword.trim();
        String[] keyWords = keyword.split(" ");
        ArrayList<Task> tmpTasks = new ArrayList<>();
        for(Task task : tasks) {
            String[] descriptionSplit = task.getDescription().split(" ");
            for(String word : descriptionSplit) {
                for(String kword : keyWords) {
                    if(word.equals(kword) && !tmpTasks.contains(task)) {
                        tmpTasks.add(task);
                        break;
                    }
                }
            }
        }
        return tmpTasks;
    }

    public int getSize() {
        return this.tasks.size();
    }
    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int id) {
        Task task = this.tasks.get(id);
        this.tasks.remove(id);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task mark(int id) {
        Task task = tasks.get(id);
        task.mark();
        return task;
    }

    public Task unmark(int id) {
        Task task = tasks.get(id);
        task.unmark();
        return task;
    }

    public void listTasks() {
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }
}
