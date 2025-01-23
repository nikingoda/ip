import java.util.ArrayList;
import java.util.Scanner;

public class Nikingoda {
    private final ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Nikingoda nikingoda = new Nikingoda();
        nikingoda.greet();
        nikingoda.operate();
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Hello! I'm nikingoda\n" +
                "\t" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void echo() {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("bye")) {
            this.exit();
            return;
        }
        System.out.println("____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________");
        this.echo();
    }

    private void add(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        System.out.println("____________________________________________________________\n" +
                "\t" + "added: " + description + "\n" +
                "____________________________________________________________");
    }

    private void list() {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void operate() {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("bye")) {
            this.exit();
        } else if (command.equals("list")) {
            this.list();
            this.operate();
        } else if (command.length() > 5 && command.substring(0, 5).equals("mark ")) {
            try {
                int id = Integer.parseInt(command.substring(5));
                this.mark(id - 1);
            } catch (Exception e) {
                this.add(command);
            } finally {
                operate();
            }

        } else if (command.length() > 7 && command.substring(0, 7).equals("unmark ")) {
            try {
                int id = Integer.parseInt(command.substring(7));
                this.unmark(id - 1);
            } catch (Exception e) {
                this.add(command);
            } finally {
                operate();
            }
        } else {
            this.add(command);
            operate();
        }
    }

    public void mark(int id) {
        tasks.get(id).mark();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" +
                "\t" + tasks.get(id));
        System.out.println("____________________________________________________________");
    }

    public void unmark(int id) {
        tasks.get(id).unmark();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "\t" + tasks.get(id));
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
