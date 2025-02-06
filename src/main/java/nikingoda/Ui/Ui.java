package nikingoda.Ui;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Parser.Parser;
import nikingoda.Storage.Storage;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private boolean isExit = false;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public boolean isExit() {
        return isExit;
    }

    public void readCommand(Storage storage, TaskList taskList) throws nikingodaException {
        String command = this.sc.nextLine();
        Parser.parseCommand(command, storage, taskList, this);
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Hello! I'm nikingoda\n" +
                "\t" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        this.isExit = true;
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void list(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        taskList.listTasks();
        System.out.println("____________________________________________________________");
    }

    public void mark(TaskList taskList, int id) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" +
                "\t" + taskList.mark(id));
        System.out.println("____________________________________________________________");
    }

    public void unmark(TaskList taskList, int id) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "\t" + taskList.unmark(id));
        System.out.println("____________________________________________________________");
    }

    public void add(TaskList taskList, Task task) {
        taskList.add(task);
        System.out.println("____________________________________________________________\n" +
                "Got it, I've added this task: \n" + task + "\n" +
                "Now you have " + taskList.getSize() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    public void delete(TaskList taskList, int id) {
        Task task = taskList.delete(id);
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" + task + "\n" +
                "Now you have " + taskList.getSize() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    public void showError(nikingodaException e) {
        System.out.println(e.getMessage());
    }
}
