import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WrapLinesTextCommandTest {

    @Test
    void testWrapLines() {
        // Arrange
        WrapLinesTextCommand command = new WrapLinesTextCommand("[", "]");
        String inputText = "Hello\nWorld";

        // Act
        String result = command.execute(inputText);

        // Assert
        String expected = "[Hello]\n[World]";
        assertEquals(expected, result, "Each line should be wrapped in brackets.");
    }
}

class WrapSelectionTextCommandTest {

    @Test
    void testWrapSelection() {
        // Arrange
        WrapSelectionTextCommand command = new WrapSelectionTextCommand("[", "]", "World");
        String inputText = "Hello World!";

        // Act
        String result = command.execute(inputText);

        // Assert
        String expected = "Hello [World]!";
        assertEquals(expected, result, "The selection should be wrapped in brackets.");
    }
}

class ReplaceFirstTextCommandTest {

    @Test
    void testReplaceFirst() {
        // Arrange
        ReplaceFirstTextCommand command = new ReplaceFirstTextCommand("Hello", "Hi");
        String inputText = "Hello World! Hello Again!";

        // Act
        String result = command.execute(inputText);

        // Assert
        String expected = "Hi World! Hello Again!";
        assertEquals(expected, result, "Only the first occurrence of 'Hello' should be replaced with 'Hi'.");
    }
}

class CapitalizeWordsTextCommandTest {

    @Test
    void testCapitalizeWords() {
        // Arrange
        CapitalizeWordsTextCommand command = new CapitalizeWordsTextCommand();
        String inputText = "hello world! this is a test.";

        // Act
        String result = command.execute(inputText);

        // Assert
        String expected = "Hello World! This Is A Test.";
        assertEquals(expected, result, "Each word should be capitalized.");
    }
}

class CapitalizeSelectionTextCommandTest {

    @Test
    void testCapitalizeSelection() {
        // Arrange
        CapitalizeSelectionTextCommand command = new CapitalizeSelectionTextCommand("world");
        String inputText = "hello world! welcome to the world.";

        // Act
        String result = command.execute(inputText);

        // Assert
        String expected = "hello WORLD! welcome to the WORLD.";
        assertEquals(expected, result, "Only the selected word 'world' should be capitalized.");
    }
}

class ScriptTest {

    @Test
    void testScriptExecution() {
        // Arrange
        TextCommand wrapCommand = new WrapLinesTextCommand("[", "]");
        TextCommand capitalizeWordsCommand = new CapitalizeWordsTextCommand();
        Script script = new Script(Arrays.asList(capitalizeWordsCommand, wrapCommand));

        String inputText = "hello world";

        // Act
        String result = script.execute(inputText);

        // Assert
        String expected = "[Hello World]";
        assertEquals(expected, result, "Script should wrap lines and capitalize words in sequence.");
    }
}