package main;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Main entry point for the program. Performs input operations
 * to find out how many processes and resources are involved in the
 * simulation, then creates a ProcessRunner.
 * Continues until the number of processes and resources inputted
 * are both 0's.
 */

import process.ProcessRunner;

import java.util.Scanner;

public class DeadlockDetector {
    /* Reading the input from standard input. */
    private static Scanner in;

    public static void main(String[] args) {

        /* See if the user requested verbose or normal mode. */
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

            /* If both zero, user wants to exit program. */
            if (numProcesses == 0 && numResources == 0) {
                break;
            }

            /* Print which simulation this is on. */
            simulationNumber++;
            Log.info("Simulation %d\n", simulationNumber);

            /* Create the processes, resources, and resource manager. */
            ProcessRunner runner = new ProcessRunner(in, numProcesses, numResources);
            /* Run the simulation. */
            runner.runProcesses();
        }
    }
}
