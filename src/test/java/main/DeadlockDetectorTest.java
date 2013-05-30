package main;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.assertEquals;

public class DeadlockDetectorTest {

    @DataProvider(name = "testCases")
    public Object[][] createTestCases() {
        return new Object[][]{
                {null, "1 1 2 1 1 2 1", "Simulation 1\n" +
                        "All processes successfully terminated.\n" +
                        "Process 1: run time = 2, ended at 1\n"},
                {null, "2 2 2 1 1 1 2 2 1 2 1 1", "Simulation 1\n" +
                        "Deadlock detected at time 2 involving...\n" +
                        "\tprocesses: 1 2\n\tresources: 2 1"}};
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
