import java.util.List;

public interface TextCommand {
    String execute(String text);

    static void displayCommands(List<TextCommand> commands) {
        System.out.println("Commands added:");
        for (TextCommand command : commands) {
            if (command instanceof WrapTextCommand) {
                WrapTextCommand wrapCommand = (WrapTextCommand) command;
                System.out.println(" - WrapTextCommand: opening='" + wrapCommand.getOpening() +
                        "', end='" + wrapCommand.getEnd() + "'");
            } else if (command instanceof ReplaceTextCommand) {
                ReplaceTextCommand replaceCommand = (ReplaceTextCommand) command;
                System.out.println(" - ReplaceTextCommand: target='" + replaceCommand.getTarget() +
                        "', replacement='" + replaceCommand.getReplacement() + "'");
            } else if (command instanceof CapitalizeSelectionTextCommand) {
                CapitalizeSelectionTextCommand capitalizeCommand = (CapitalizeSelectionTextCommand) command;
                System.out.println(" - CapitalizeSelectionTextCommand: selection='" + capitalizeCommand.getSelection() + "'");
            } else if (command instanceof CapitalizeWordsTextCommand) {
                System.out.println(" - CapitalizeWordsTextCommand");
            }
        }
    }
}
