package com.jakhar.chessgame.test;

import com.jakhar.chessgame.AppMain;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RooksAndKnightsTest {
    @Test
    public void testForInputSizeOne() {
        StringBuilder userInputBuilder = new StringBuilder();
        userInputBuilder.append("1\n");
        userInputBuilder.append("W\n");
        userInputBuilder.append("R\n");
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
        expectedOutputBuilder.append("R on e3: a3 b3 c3 d3 e1 e2 e4 e5 e6 e7 e8 f3 g3 h3");

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
        userInputBuilder.append("R\n");
        userInputBuilder.append("f2\n");

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
        expectedOutputBuilder.append("R on f2: a2 b2 c2 d2 e2 f1 f3 f4 f5 g2 h2\n");
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
    public void testForInputWithContinuousGamePlayOption() {
        StringBuilder userInputBuilder = new StringBuilder();
        userInputBuilder.append("2\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("R\n");
        userInputBuilder.append("f2\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("N\n");
        userInputBuilder.append("f6\n");

        // Continue with the next game.
        userInputBuilder.append("Y\n");

        // For 3 pieces this time.
        userInputBuilder.append("3\n");

        userInputBuilder.append("W\n");
        userInputBuilder.append("R\n");
        userInputBuilder.append("e3\n");

        userInputBuilder.append("B\n");
        userInputBuilder.append("R\n");
        userInputBuilder.append("e6\n");

        userInputBuilder.append("B\n");
        userInputBuilder.append("N\n");
        userInputBuilder.append("d6\n");

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
        expectedOutputBuilder.append("R on e3: a3 b3 c3 d3 e1 e2 e4 e5 e6 f3 g3 h3\n");
        expectedOutputBuilder.append("R on e6: e3 e4 e5 e7 e8 f6 g6 h6\n");
        expectedOutputBuilder.append("N on d6: b5 b7 c4 c8 e4 e8 f5 f7");

        StringBuilder actualOutputBuilder = new StringBuilder();
        // actualOutputBuilder.append(outputLines[outputLines.length - 8]).append("\n");
        // actualOutputBuilder.append(outputLines[outputLines.length - 7]).append("\n");
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
