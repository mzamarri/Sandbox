import commands.*;
import messages.*;

public class CommandPatternExample {
    public static void main(String[] args) {
        Message message = new Message("");
        CommandManager manager = new CommandManager();
        String text = "ABCDEFG ";
        Command paste = new PasteCommand(message, text);
        manager.invokeCommand(paste);
        Command save = new SaveCommand(message);
        manager.invokeCommand(save);
        Command print = new PrintCommand(message);
        manager.invokeCommand(paste);
        manager.invokeCommand(print);
        Command delete = new DeleteCommand(message, 4);
        manager.invokeCommand(delete);
        manager.invokeCommand(save);
        manager.invokeCommand(print);
        manager.undoCommand();
        manager.invokeCommand(save);
        manager.invokeCommand(print);
    }
}