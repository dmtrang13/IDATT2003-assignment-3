import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalClient {
    private static String mainText = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TextCommand> commands = new ArrayList<>();

        printWelcomeMenu();

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                TextCommand.displayCommands(commands);
                continue;
            }

            if (mainText.isEmpty() && !input.startsWith("set-text")) {
                System.out.println("Warning: Main text is not set. Use 'set-text <your-main-text>' to set it.");
            }

            handleCommand(input, commands);
        }

        if (mainText.isEmpty()) {
            System.out.println("Enter the text to process:");
            mainText = scanner.nextLine();
        }

        Script script = new Script(commands);
        String result = script.execute(mainText);

        System.out.println("Result:");
        System.out.println(result);

        scanner.close();
    }

    private static void printWelcomeMenu() {
        System.out.println("Welcome to the Text Manipulation Tool!");
        System.out.println("Available commands:");
        System.out.println("1. Wrap lines (wrap-lines <opening> <end>)");
        System.out.println("2. Replace word (replace-word <target> <replacement>)");
        System.out.println("3. Capitalize words (capitalize-words)");
        System.out.println("4. Capitalize selection (capitalize-selection <selection>)");
        System.out.println("5. Set main text to manipulate (set-text <your-main-text>)");
        System.out.println("Type 'done' to finish adding commands.");
        System.out.println("Enter 'list' to review added commands.");
    }

    private static void handleCommand(String input, List<TextCommand> commands) {
        String[] parts = input.split(" ", 2);
        if (parts.length == 0) {
            System.out.println("Invalid command. Please try again.");
            return;
        }

        String command = parts[0];
        String params = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "wrap-lines": {
                handleWrapLinesCommand(params, commands);
                break;
            }
            case "replace-word": {
                handleReplaceWordCommand(params, commands);
                break;
            }
            case "capitalize-words": {
                commands.add(new CapitalizeWordsTextCommand());
                break;
            }
            case "capitalize-selection": {
                handleCapitalizeSelectionCommand(params, commands);
                break;
            }
            case "set-text": {
                handleSetTextCommand(params);
                break;
            }
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void handleWrapLinesCommand(String params, List<TextCommand> commands) {
        String[] tokens = params.split(" ");
        if (tokens.length < 2) {
            System.out.println("Usage: wrap-lines <opening> <end>");
        } else {
            commands.add(new WrapLinesTextCommand(tokens[0], tokens[1]));
        }
    }

    private static void handleReplaceWordCommand(String params, List<TextCommand> commands) {
        String[] tokens = params.split(" ");
        if (tokens.length < 2) {
            System.out.println("Usage: replace-word <target> <replacement>");
        } else {
            commands.add(new ReplaceFirstTextCommand(tokens[0], tokens[1]));
        }
    }

    private static void handleCapitalizeSelectionCommand(String params, List<TextCommand> commands) {
        if (params.isEmpty()) {
            System.out.println("Usage: capitalize-selection <selection>");
        } else {
            commands.add(new CapitalizeSelectionTextCommand(params));
        }
    }

    private static void handleSetTextCommand(String params) {
        if (params.isEmpty()) {
            System.out.println("Usage: set-text <your-main-text>");
        } else {
            mainText = params;
            System.out.println("Main text set to: " + mainText);
        }
    }
}
