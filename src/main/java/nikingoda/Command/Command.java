package nikingoda.Command;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public abstract class Command {
    public static Command findCommand(String command) throws nikingodaException {
        command = command.trim();
        command = command.toLowerCase();
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        }
        String[] commandSplit = command.split(" ");
        if (commandSplit.length == 0) {
            throw new nikingodaException("Please enter message");
        }
        String firstWord = commandSplit[0];
        switch (firstWord) {
        case "mark" -> {
            try {
                int id = Integer.parseInt(command.substring(5));
                return new MarkCommand(id);
            } catch (NumberFormatException e) {
                throw new nikingodaException(" Id must be in form of number");
            }
        }
        case "unmark" -> {
            try {
                int id = Integer.parseInt(command.substring(7));
                return new UnmarkCommand(id);
            } catch (NumberFormatException e) {
                throw new nikingodaException(" Id must be in form of number");
            }
        }
        case "todo" -> {
            return new addTodoCommand(command);
        }
        case "event" -> {
            return new addEventCommand(command);
        }
        case "deadline" -> {
            return new addDeadlineCommand(command);
        }
        case "delete" -> {
            try {
                int id = Integer.parseInt(commandSplit[1]);
                return new DeleteCommand(id);
            } catch (NumberFormatException e) {
                throw new nikingodaException("nikingoda.Task.Task id must be integer");
            }
        }
        case "find" -> {
            try {
                StringBuilder keyWord = new StringBuilder(commandSplit[1]);
                for (int i = 2; i < commandSplit.length; i++) {
                    keyWord.append(" ").append(commandSplit[i]);
                }
                return new FindCommand(keyWord.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new nikingodaException("Please add keyword");
            } catch (Exception e) {
                throw new nikingodaException(e.getMessage());
            }
        }
        default -> throw new nikingodaException("I don't understand you");
        }
    }

    /**
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws nikingodaException handle Error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws nikingodaException;

    public boolean isExit() {
        return false;
    }
}
