import java.util.Arrays;

abstract class WrapTextCommand implements TextCommand {
    protected String opening;
    protected String end;

    public WrapTextCommand(String opening, String end) {
        this.opening = opening;
        this.end = end;
    }

    public String getOpening() {
        return opening;
    }

    public String getEnd() {
        return end;
    }

    public abstract String execute(String text);
}

class WrapLinesTextCommand extends WrapTextCommand {

    public WrapLinesTextCommand(String opening, String end) {
        super(opening, end);
    }

    @Override
    public String execute(String text) {
        String[] lines = text.split("\n");
        String[] wrappedLines = Arrays.stream(lines)
                                        .map(line -> String.format("%s%s%s", opening, line, end))
                                        .toArray(String[]::new);
        return String.join("\n", wrappedLines);
    }
}

class WrapSelectionTextCommand extends WrapTextCommand {
    private final String selection;

    public WrapSelectionTextCommand(String opening, String end, String selection) {
        super(opening, end);
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    @Override
    public String execute(String text) {
        if (selection == null || selection.isEmpty() || text == null || text.isEmpty()) {
            return text;
        }
        return text.replace(selection, opening + selection + end);
    }
}