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
        nikingoda.exit();
    }

    public void greet() {
        System.out.println("Hello! I'm nikingoda\n" +
                "What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
