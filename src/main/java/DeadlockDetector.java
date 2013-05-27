import process.ProcessRunner;

import java.util.Scanner;

public class DeadlockDetector {
    /* Reading the input from standard input. */
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            /* From input, how many of each object required. */
            int numProcesses = in.nextInt();
            int numResources = in.nextInt();

            if (numProcesses == 0 && numResources == 0) {
                break;
            }

            /* Create the processes, resources, and resource manager. */
            ProcessRunner runner = new ProcessRunner(in, numProcesses, numResources);
            runner.runProcesses();
        }
    }
}
