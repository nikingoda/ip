package nikingoda.Command;

public abstract class UpdateCommand extends Command {
    protected final int id;

    protected UpdateCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
