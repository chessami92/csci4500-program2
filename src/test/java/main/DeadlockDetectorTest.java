package main;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.assertEquals;

public class DeadlockDetectorTest {

    @DataProvider(name = "testCases")
    public Object[][] createTestCases() {
        return new Object[][]{
                {null, "1 1 2 1 1 2 1 0 0", "Simulation 1\n" +
                        "All processes successfully terminated.\n" +
                        "Process 1: run time = 2, ended at 1\n"}};
    }

    @Test(dataProvider = "testCases")
    public void basicTest(String argument, String input, String expectedOutput) throws Exception {
        InputStream systemInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(systemInput);
        OutputStream systemOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutput));

        DeadlockDetector.main(new String[]{argument});
        assertEquals(systemOutput.toString(), expectedOutput);
    }
}
