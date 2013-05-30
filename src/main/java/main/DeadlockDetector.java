package main;

import process.ProcessRunner;

import java.util.Scanner;

public class DeadlockDetector {
    /* Reading the input from standard input. */
    private static Scanner in;

    public static void main(String[] args) {
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
            System.out.printf("Simulation %d\n", simulationNumber);

            /* Create the processes, resources, and resource manager. */
            ProcessRunner runner = new ProcessRunner(in, numProcesses, numResources);
            runner.runProcesses();
        }
    }
}
