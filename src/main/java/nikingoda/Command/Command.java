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
                throw new nikingodaException("Task id must be integer");
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
        case "update" -> {
            try {
                String[] updateCommandSplit = command.split(" /description ");
                if(updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newDescription = updateCommandSplit[1];
                        return new UpdateDescriptionCommand(id, newDescription);
                    } catch (Exception e) {
                        throw new nikingodaException("To update description, use: update <task_id> /description <new_description>");
                    }
                }
                updateCommandSplit = command.split(" /by ");
                if(updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newDeadline = updateCommandSplit[1];
                        return new UpdateDeadlineCommand(id, newDeadline);
                    } catch (Exception e) {
                        throw new nikingodaException("To update deadline, use: update <task_id> /by <new_deadline>");
                    }
                }
                updateCommandSplit = command.split(" /begin ");
                if(updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newBegin = updateCommandSplit[1];
                        return new UpdateBeginCommand(id, newBegin);
                    } catch (Exception e) {
                        throw new nikingodaException("To update begin_time, use: update <task_id> /begin <new_begin_time>");
                    }
                }
                updateCommandSplit = command.split(" /end ");
                if(updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newEnd = updateCommandSplit[1];
                        return new UpdateEndCommand(id, newEnd);
                    } catch (Exception e) {
                        throw new nikingodaException("To update end_time, use: update <task_id> /end <new_end_time>");
                    }
                }
            } catch (Exception e) {
                throw new nikingodaException("""
                        To update, use:\s
                        'update <task_id> /by <new_deadline>' to update deadline
                        'update <task_id> /description <new_description>' to update description
                        'update <task_id> /begin <new_begin_time>' to update begin_time
                        'update <task_id> /end <new_end_time>' to update end_time""");
            }
        }
        default -> throw new nikingodaException("I don't understand you");
        }
        return null;
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
