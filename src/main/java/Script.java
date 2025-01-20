import java.util.List;

public class Script {
    private final List<TextCommand> textCommands;

    public Script(List<TextCommand> textCommands) {
        this.textCommands = textCommands;
    }

    public String execute(String text) {
        String result = text;
        for (TextCommand textCommand : textCommands) {
            result = textCommand.execute(result);
        }
        return result;
    }
}
