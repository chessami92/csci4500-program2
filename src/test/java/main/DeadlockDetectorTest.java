package main;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeadlockDetectorTest {

    @DataProvider(name = "testCases")
    public Object[][] createTestCases() {
        List<String> inputs = new ArrayList<String>();
        List<String> outputs = new ArrayList<String>();

        inputs.add("1 2 5 1 1 1 2 3 10 2 1 2 2");
        outputs.add("All processes successfully terminated.\n" +
                "Process 1: run time = 14, ended at 13");
        inputs.add("3 3 5 1 1 1 2 3 2 2 1 2 2 5 1 1 1 2 3 2 2 1 2 2 4 1 3 3 5 2 3 3 2");
        outputs.add("All processes successfully terminated.\n" +
                "Process 1: run time = 6, ended at 12\n" +
                "Process 2: run time = 7, ended at 21\n" +
                "Process 3: run time = 9, ended at 19");
        inputs.add("2 2 2 1 1 1 2 2 1 2 1 1");
        outputs.add("Deadlock detected at time 2 involving...\n"
                + "\tprocesses: 1 2\n\tresources: 2 1");

        StringBuilder inputSb = new StringBuilder();
        StringBuilder outputSb = new StringBuilder();

        for (String input : inputs) {
            inputSb.append(input).append("\n");
        }
        int i = 1;
        for (String output : outputs) {
            outputSb.append("Simulation ").append(i++).append("\n")
                    .append(output).append("\n\n");
        }

        return new Object[][]{{null, inputSb.toString(), outputSb.toString()}};
    }

    @Test(dataProvider = "testCases")
    public void basicTest(String argument, String input, String expectedOutput) throws Exception {
        input += "\n0 0";
        InputStream systemInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(systemInput);
        OutputStream systemOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutput));

        DeadlockDetector.main(new String[]{argument});
        assertEquals(systemOutput.toString(), expectedOutput);
    }
}
