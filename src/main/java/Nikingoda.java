import java.util.Scanner;

public class Nikingoda {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Nikingoda nikingoda = new Nikingoda();
        nikingoda.greet();
        nikingoda.echo();
        nikingoda.exit();
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

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
