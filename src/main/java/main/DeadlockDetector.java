package main;

import process.ProcessRunner;

import java.util.Scanner;

public class DeadlockDetector {
    /* Reading the input from standard input. */
    private static Scanner in;

    public static void main(String[] args) {

        if (args.length != 0) {
            Log.logLevel = Log.TRACE;
        } else {
            Log.logLevel = Log.INFO;
        }

        int simulationNumber = 0;

        in = new Scanner(System.in);

        while (true) {
            /* From input, how many of each object required. */
            int numProcesses = in.nextInt();
            int numResources = in.nextInt();

            if (numProcesses == 0 && numResources == 0) {
                break;
            }

            simulationNumber++;
            Log.info("Simulation %d\n", simulationNumber);

            /* Create the processes, resources, and resource manager. */
            ProcessRunner runner = new ProcessRunner(in, numProcesses, numResources);
            runner.runProcesses();
        }
    }
}
