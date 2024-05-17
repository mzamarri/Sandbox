import java.util.ArrayList;

import commands.*;

public class CommandManager {
    private ArrayList<Command> history = new ArrayList<>();
    private ArrayList<Command> redo = new ArrayList<>();

    public void invokeCommand(Command command) {
        if (command.isReversible()) {
            command.execute();
            history.add(command);
        } else {
            command.execute();
        }
    }

    public void undoCommand() {
        if (!history.isEmpty()) {
            Command recentCommand = history.get(history.size() - 1);
            if (recentCommand.isReversible()) {
                System.out.println("Undoing Command...");
                recentCommand.unexecute();
                history.remove(history.size() - 1);
                redo.add(recentCommand);
        
            } else {
                System.out.println("Command cannot be undone.");
            }
        } else {
            System.out.println("No command to undo.");
        }
    }

    public void redoCommand() {
        if (!redo.isEmpty()) {
            Command redoCommand = redo.get(0);
            redoCommand.execute();
            redo.remove(redo.size() - 1);
            history.add(redoCommand);
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}

