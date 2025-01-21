import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface TextCommand {
    String execute(String text);

    class TerminalClient {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<TextCommand> commands = new ArrayList<>();

            System.out.println("Welcome to the Text Manipulation Tool!");
            System.out.println("Available commands:");
            System.out.println("1. Wrap lines (wrap-lines <opening> <end>)");
            System.out.println("2. Replace first (replace-first <target> <replacement>)");
            System.out.println("3. Capitalize words (capitalize-words)");
            System.out.println("4. Capitalize selection (capitalize-selection <selection>)");
            System.out.println("Type 'done' to finish adding commands.");
            System.out.println("Enter your commands:");

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("done")) {
                    break;
                }

                String[] parts = input.split(" ", 2);
                if (parts.length == 0) {
                    System.out.println("Invalid command. Please try again.");
                    continue;
                }

                String command = parts[0];
                String params = parts.length > 1 ? parts[1] : "";

                switch (command) {
                    case "wrap-lines": {
                        String[] tokens = params.split(" ");
                        if (tokens.length < 2) {
                            System.out.println("Usage: wrap-lines <opening> <end>");
                        } else {
                            commands.add(new WrapLinesTextCommand(tokens[0], tokens[1]));
                        }
                        break;
                    }
                    case "replace-first": {
                        String[] tokens = params.split(" ");
                        if (tokens.length < 2) {
                            System.out.println("Usage: replace-first <target> <replacement>");
                        } else {
                            commands.add(new ReplaceFirstTextCommand(tokens[0], tokens[1]));
                        }
                        break;
                    }
                    case "capitalize-words": {
                        commands.add(new CapitalizeWordsTextCommand());
                        break;
                    }
                    case "capitalize-selection": {
                        if (params.isEmpty()) {
                            System.out.println("Usage: capitalize-selection <selection>");
                        } else {
                            commands.add(new CapitalizeSelectionTextCommand(params));
                        }
                        break;
                    }
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }

            System.out.println("Enter the text to process:");
            String text = scanner.nextLine();

            Script script = new Script(commands);
            String result = script.execute(text);

            System.out.println("Result:");
            System.out.println(result);

            scanner.close();
        }
    }
}
