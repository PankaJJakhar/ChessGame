package com.jakhar.chessgame.test;

import com.jakhar.chessgame.AppMain;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BishopAndKnightsTest {

    @Test
    public void testForInputSizeOne() {
        StringBuilder userInputBuilder = new StringBuilder();

        userInputBuilder.append("1\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("B\n");
        userInputBuilder.append("e3\n");

        userInputBuilder.append("N");

        String userInput = userInputBuilder.toString();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        AppMain.main(null); // Call the main program.

        String[] outputLines = outputStream.toString().split(System.lineSeparator());

        StringBuilder expectedOutputBuilder = new StringBuilder();
        expectedOutputBuilder.append("Valid moves\n");
        expectedOutputBuilder.append("B on e3: a7 b6 c1 c5 d2 d4 f2 f4 g1 g5 h6");

        StringBuilder actualOutputBuilder = new StringBuilder();
        actualOutputBuilder.append(outputLines[outputLines.length - 4]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 3]);

        String expectedOutput = expectedOutputBuilder.toString();
        String actualOutput = actualOutputBuilder.toString();

        // Verify that result is correct as expected.
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testForInputSizeTwo() {
        StringBuilder userInputBuilder = new StringBuilder();

        userInputBuilder.append("2\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("B\n");
        userInputBuilder.append("g5\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("N\n");
        userInputBuilder.append("f6\n");

        userInputBuilder.append("N");

        String userInput = userInputBuilder.toString();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        AppMain.main(null); // Call the main program.

        String[] outputLines = outputStream.toString().split(System.lineSeparator());

        StringBuilder expectedOutputBuilder = new StringBuilder();
        expectedOutputBuilder.append("Valid moves\n");
        expectedOutputBuilder.append("B on g5: c1 d2 e3 f4 h4 h6\n");
        expectedOutputBuilder.append("N on f6: d5 d7 e4 e8 g4 g8 h5 h7");

        StringBuilder actualOutputBuilder = new StringBuilder();
        actualOutputBuilder.append(outputLines[outputLines.length - 5]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 4]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 3]);

        String expectedOutput = expectedOutputBuilder.toString();
        String actualOutput = actualOutputBuilder.toString();

        // Verify that result is correct as expected.
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testForInputSizeThree() {
        StringBuilder userInputBuilder = new StringBuilder();

        userInputBuilder.append("3\n");

        userInputBuilder.append("B\n");
        userInputBuilder.append("B\n");
        userInputBuilder.append("d1\n");

        userInputBuilder.append("B\n");
        userInputBuilder.append("N\n");
        userInputBuilder.append("e2\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("N\n");
        userInputBuilder.append("b3\n");

        userInputBuilder.append("N");

        String userInput = userInputBuilder.toString();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        AppMain.main(null); // Call the main program.

        String[] outputLines = outputStream.toString().split(System.lineSeparator());

        StringBuilder expectedOutputBuilder = new StringBuilder();
        expectedOutputBuilder.append("Valid moves\n");
        expectedOutputBuilder.append("B on d1: b3 c2\n");
        expectedOutputBuilder.append("N on e2: c1 c3 d4 f4 g1 g3\n");
        expectedOutputBuilder.append("N on b3: a1 a5 c1 c5 d2 d4");

        StringBuilder actualOutputBuilder = new StringBuilder();
        actualOutputBuilder.append(outputLines[outputLines.length - 6]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 5]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 4]).append("\n");
        actualOutputBuilder.append(outputLines[outputLines.length - 3]);

        String expectedOutput = expectedOutputBuilder.toString();
        String actualOutput = actualOutputBuilder.toString();

        // Verify that result is correct as expected.
        Assert.assertEquals(expectedOutput, actualOutput);
    }
}