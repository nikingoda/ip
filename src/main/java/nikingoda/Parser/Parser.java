package nikingoda.Parser;

import nikingoda.NikingodaException.nikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Deadline;
import nikingoda.Task.Event;
import nikingoda.Task.Task;
import nikingoda.Task.Todo;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class Parser {
    public static Task parseTask(String line) {
        String[] lineSplitted = line.split("\\|");
        boolean isDone = lineSplitted[1].equals("1");
        if (lineSplitted[0].equals("T")) {
            return new Todo(lineSplitted[2], isDone);
        } else if (lineSplitted[0].equals("D")) {
            return new Deadline(lineSplitted[2], lineSplitted[3], isDone);
        } else {
            return new Event(lineSplitted[2], lineSplitted[3], lineSplitted[4], isDone);
        }
    }

    public static void parseCommand(String command, Storage storage, TaskList tasks, Ui ui) throws nikingodaException {
        command = command.trim();
        command = command.toLowerCase();
        if (command.equals("bye")) {
            ui.exit();
            storage.saveTask(tasks);
            return;
        } else if (command.equals("list")) {
            ui.list(tasks);
            return;
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
                    ui.mark(tasks, id - 1);             //Id must be transform to 0-indexed
                } catch (NumberFormatException e) {
                    throw new nikingodaException(" Id must be in form of number");
                } catch (IndexOutOfBoundsException e) {
                    throw new nikingodaException(" There's no task with your id");
                }
            }
            case "unmark" -> {
                try {
                    int id = Integer.parseInt(command.substring(7));
                    ui.unmark(tasks, id - 1);             //Id must be transform to 0-indexed
                } catch (NumberFormatException e) {
                    throw new nikingodaException(" Id must be in form of number");
                } catch (IndexOutOfBoundsException e) {
                    throw new nikingodaException(" There's no task with your id");
                }
            }
            case "todo" -> {
                try {
                    String description = command.substring(5);
                    if (description.isBlank()) {
                        throw new nikingodaException("Description must not be blank!!!");
                    }
                    ui.add(tasks, new Todo(description));
                } catch (Exception e) {
                    throw new nikingodaException(e.getMessage());
                }
            }
            case "event" -> {
                try {
                    String subCommand = command.substring(6);
                    String[] subCommandSplit = subCommand.split(" /from ");
                    String description = subCommandSplit[0].trim();
                    String[] timeCommandSplit = subCommandSplit[1].split(" /to ");
                    String begin = timeCommandSplit[0].trim();
                    String end = timeCommandSplit[1].trim();
                    if (description.isBlank()) {
                        throw new nikingodaException("Description must not be blank!!!");
                    }
                    if (begin.isBlank()) {
                        throw new nikingodaException("please add begin time");
                    }
                    if (end.isBlank()) {
                        throw new nikingodaException("please add end time");
                    }
                    ui.add(tasks, new Event(description, begin, end));
                } catch (IndexOutOfBoundsException e) {
                    throw new nikingodaException("Invalid format.\nShould be: event <description> /from <begin_time> /to <end_time>");
                } catch (Exception e) {
                    throw new nikingodaException(e.getMessage());
                }
            }
            case "deadline" -> {
                try {
                    String subCommand = command.substring(9);
                    String[] subCommandSplit = subCommand.split(" /by ");
                    String description = subCommandSplit[0].trim();
                    String deadline = subCommandSplit[1].trim();
                    if (description.isBlank()) {
                        throw new nikingodaException("Description must not be blank!!!");
                    }
                    if (deadline.isBlank()) {
                        throw new nikingodaException("please add deadline");
                    }
                    ui.add(tasks, new Deadline(description, deadline));
                } catch (IndexOutOfBoundsException e) {
                    throw new nikingodaException("Invalid format.\nShould be: event <description> /by <deadline>");
                } catch (Exception e) {
                    throw new nikingodaException(e.getMessage());
                }
            }
            case "delete" -> {
                try {
                    int id = Integer.parseInt(commandSplit[1]);
                    ui.delete(tasks, id);
                } catch (NumberFormatException e) {
                    throw new nikingodaException("nikingoda.Task.Task id must be integer");
                } catch (IndexOutOfBoundsException e) {
                    throw new nikingodaException("Please indicate task id");
                } catch (Exception e) {
                    throw new nikingodaException(e.getMessage());
                }
            } case "find" -> {
                try {
                    StringBuilder keyWord = new StringBuilder(commandSplit[1]);
                    for(int i = 2; i < commandSplit.length; i++) {
                        keyWord.append(" ").append(commandSplit[i]);
                    }
                    ui.find(tasks, keyWord.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw  new nikingodaException("Please add keyword");
                } catch (Exception e) {
                    throw new nikingodaException(e.getMessage());
                }
            }
            default -> throw new nikingodaException("I don't understand you");
        }
    }
}
