abstract class ReplaceTextCommand implements TextCommand {
    protected String target;
    protected String replacement;

    public ReplaceTextCommand(String target, String replacement) {
        this.target = target;
        this.replacement = replacement;
    }

    public String getTarget() {
        return target;
    }

    public String getReplacement() {
        return replacement;
    }

    public String execute(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Target string cannot be null or empty");
        }
        return text.replace(target, replacement);
    }
}

class ReplaceFirstTextCommand extends ReplaceTextCommand {

    public ReplaceFirstTextCommand(String target, String replacement) {
        super(target, replacement);
    }

    @Override
    public String execute(String text) {
        return text.replaceFirst(target, replacement);
    }
}
