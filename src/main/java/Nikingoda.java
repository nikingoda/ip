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

    private void add(String description, int typeID, String deadline, String begin, String end) {
        Task task;
        if (typeID == 0) {
            task = new Task(description, typeID);
        } else if (typeID == 1) {
            task = new Task(description, typeID, deadline);
        } else {
            task = new Task(description, typeID, begin, end);
        }
        this.tasks.add(task);
        System.out.println("____________________________________________________________\n" +
                "Got it, I've added this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    private void delete(int id) {
        Task task = this.tasks.get(id);
        this.tasks.remove(id);
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    private void list() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
        System.out.println("____________________________________________________________");
    }

    private void handleSyntaxError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    private void invalid() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid syntax! Please try again.");
        System.out.println("____________________________________________________________");
    }

    private void operate() {
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
                this.invalid();
            } finally {
                operate();
            }

        } else if (command.length() > 7 && command.substring(0, 7).equals("unmark ")) {
            try {
                int id = Integer.parseInt(command.substring(7));
                this.unmark(id - 1);
            } catch (Exception e) {
                this.invalid();
            } finally {
                operate();
            }
        } else if (command.length() > 5 && command.substring(0, 5).equals("todo ")) {
            try {
                int typeID = 0;
                String description = command.substring(5);
                if (description.isBlank()) {
                    throw new nikingodaException("Description must not be blank!!!");
                }
                this.add(description, typeID, "", "", ""); //typeID = 0 for
            } catch (nikingodaException e) {
                this.handleSyntaxError(e.getMessage());
            } catch (Exception e) {
                this.invalid();
            } finally {
                operate();
            }
        } else if (command.length() > 9 && command.substring(0, 9).equals("deadline ")) {
            try {
                int symbolId = command.indexOf(" /by ", 9);
                String description = command.substring(9, symbolId);
                if (description.isBlank()) {
                    throw new nikingodaException("Description must not be blank!!!");
                }
                String deadline = command.substring(symbolId + 5);
                if (deadline.isBlank()) {
                    throw new nikingodaException("Please indicate due date of deadline!!!");
                }
                int typeID = 1;
                this.add(description, typeID, deadline, "", "");
            } catch (nikingodaException e) {
                this.handleSyntaxError(e.getMessage());
            } catch (Exception e) {
                this.invalid();
            } finally {
                this.operate();
            }
        } else if (command.length() > 6 && command.substring(0, 6).equals("event ")) {
            try {
                int beginId = command.indexOf(" /from ", 6);
                int endId = command.indexOf(" /to ", beginId + 7);
                String description = command.substring(6, beginId);
                if (description.isBlank()) {
                    throw new nikingodaException("Description must not be blank!!!");
                }
                String begin = command.substring(beginId + 7, endId);
                if (begin.isBlank()) {
                    throw new nikingodaException("Please indicate begin time!!!");
                }
                String end = command.substring(endId + 5);
                if (end.isBlank()) {
                    throw new nikingodaException("Please indicate end time!!!");
                }
                int typeID = 2;
                this.add(description, typeID, "", begin, end);
            } catch (nikingodaException e) {
                this.handleSyntaxError(e.getMessage());
            } catch (Exception e) {
                this.invalid();
            } finally {
                this.operate();
            }
        } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
            try {
                if (command.length() == 6) {
                    throw new nikingodaException("Please indicate deleted task ID");
                }
                if (!command.substring(0, 7).equals("delete ")) {
                    throw new Exception();
                }
                int id = Integer.parseInt(command.substring(7));
                this.delete(id - 1);
            } catch (nikingodaException e) {
                this.handleSyntaxError(e.getMessage());
            } catch (Exception e) {
                this.invalid();
            } finally {
                this.operate();
            }
        } else {
            this.invalid();
            this.operate();
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
