import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Nikingoda {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final String home = System.getProperty("user.dir");


    public static void main(String[] args) {
        Nikingoda nikingoda = new Nikingoda();
        nikingoda.greet();
        nikingoda.loadTasks();
        nikingoda.operate();
    }

    private void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Hello! I'm nikingoda\n" +
                "\t" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void loadTasks() {
        Path path = Paths.get(home, "data");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdir();
        }
        File taskFile = new File(Paths.get(home, "data", "tasks.txt").toString());
        try {
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            } else {
                try (Scanner scanTask = new Scanner(taskFile)) {
                    while (scanTask.hasNextLine()) {
                        String nextLine = scanTask.nextLine();
                        String[] lineSplitted = nextLine.split("\\|");
                        Boolean isDone = lineSplitted[1].equals("1");
                        if (lineSplitted[0].equals("T")) {
                            this.tasks.add(new Todo(lineSplitted[2], isDone));
                        } else if (lineSplitted[0].equals("D")) {
                            this.tasks.add(new Deadline(lineSplitted[2], lineSplitted[3], isDone));
                        } else {
                            this.tasks.add(new Event(lineSplitted[2], lineSplitted[3], lineSplitted[4], isDone));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is problem with Tasks file!");
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot create Tasks file!");
        } catch (Exception e) {
            System.out.println("There is problem with Tasks file!");
        }
    }

    private void addDeadline(String description, String deadline) {
        Task task = new Deadline(description, deadline);
        this.add(task);
    }

    private void addEvent(String description, String begin, String end) {
        Task task = new Event(description, begin, end);
        this.add(task);
    }

    private void addTodo(String description) {
        Task task = new Todo(description);
        this.add(task);
    }

    private void delete(int id) {
        Task task = this.tasks.get(id);
        this.tasks.remove(id);
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" + task + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    private void add(Task task) {
        this.tasks.add(task);
        System.out.println("____________________________________________________________\n" +
                "Got it, I've added this task: \n" + task + "\n" +
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

    private void handleError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    private void save() {
        Path path = Paths.get(home, "data");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            FileWriter writer = new FileWriter(Paths.get(home, "data", "tasks.txt").toString());
            for (Task task : tasks) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            this.handleError(e.getMessage());
        }
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
            this.save();
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
                String description = command.substring(5);
                if (description.isBlank()) {
                    throw new nikingodaException("Description must not be blank!!!");
                }
                this.addTodo(description);
            } catch (nikingodaException e) {
                this.handleError(e.getMessage());
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
                this.addDeadline(description, deadline);
            } catch (nikingodaException e) {
                this.handleError(e.getMessage());
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
                this.addEvent(description, begin, end);
            } catch (nikingodaException e) {
                this.handleError(e.getMessage());
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
                this.handleError(e.getMessage());
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

    private void mark(int id) {
        tasks.get(id).mark();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" +
                "\t" + tasks.get(id));
        System.out.println("____________________________________________________________");
    }

    private void unmark(int id) {
        tasks.get(id).unmark();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "\t" + tasks.get(id));
        System.out.println("____________________________________________________________");
    }

    private void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
