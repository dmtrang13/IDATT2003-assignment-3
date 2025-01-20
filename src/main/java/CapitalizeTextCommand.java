import java.util.Arrays;
import java.util.stream.Collectors;

abstract class CapitalizeTextCommand implements TextCommand {
    public String execute(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.toUpperCase();
    }
}

class CapitalizeWordsTextCommand extends CapitalizeTextCommand {
    public String execute(String text) {
        return Arrays.stream(text.split("\\s+"))
                        .map(word -> {
                            if (word.isEmpty()) return word;
                            return String.format("%s %s",
                                    word.substring(0, 1).toUpperCase(),
                                    word.substring(1).toLowerCase()
                            );
                        })
                        .collect(Collectors.joining(" "));
    }
}

class CapitalizeSelectionTextCommand extends CapitalizeTextCommand {
    private String selection;

    public CapitalizeSelectionTextCommand(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public String execute(String text) {
        if (selection == null || selection.isEmpty()) {
            return text;
        }
        String capitalizedSelection = selection.toUpperCase();
        return text.replace(selection, capitalizedSelection);
    }
}
