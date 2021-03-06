package process;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Main runner for the simulation. Creates the ready and blocked queues.
 * Creates the processes with all of their tasks using the standard input.
 * Takes processes from the ready queue and runs them.
 */

import main.Log;
import manager.ResourceManager;
import work.Task;
import work.TaskFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ProcessRunner {
    /* Full listing of all processes. */
    private Process[] processList;
    /* Queue of processes ready to execute. */
    private Queue<Process> readyQueue;
    /* Queue of processes waiting for a resource. */
    private List<Process> blockedQueue;
    /* Controls granting and releasing resources. */
    private ResourceManager manager;

    /* Read from the system input to create a queue of processes */
    /* that each contain a list of tasks. Put all processes into */
    /* the ready queue initially.                                */
    public ProcessRunner(Scanner in, int numProcesses, int numResources) {
        /* How many tasks are in the current process. */
        int numTasks;
        /* The two parameters for the task. */
        int operation, n;
        Task[] processTasks;

        /* Create the object to list all processes. */
        processList = new Process[numProcesses];

        /* Set up the ready queue for execution. */
        readyQueue = new LinkedList<Process>();
        blockedQueue = new LinkedList<Process>();

        manager = new ResourceManager(numResources, readyQueue, blockedQueue);

        /* Create all of the processes according to the input. */
        for (int i = 0; i < numProcesses; ++i) {
            numTasks = in.nextInt();            /* See how many tasks. */
            processTasks = new Task[numTasks];
            /* Populate the list of tasks to be executed for the process. */
            for (int j = 0; j < numTasks; ++j) {
                operation = in.nextInt();
                n = in.nextInt();

                processTasks[j] = TaskFactory.createTask(operation, n);
            }
            /* Create the new process with the appropriate tasks. */
            Process process = new Process(i + 1, processTasks);
            /* Add the process to the list for final reporting. */
            processList[i] = process;
            /* Set up the process to be run. */
            readyQueue.add(process);
        }
    }

    public void runProcesses() {
        int currentStep = -1;

        /* Run until the ready queue is empty. */
        while (readyQueue.peek() != null) {
            Process currentProcess = readyQueue.remove();

            /* Update how many tasks have been executed. */
            currentStep++;

            /* Attempt to execute all of the processes, which may */
            /* throw an error if a deadlock is detected.          */
            try {
                currentProcess.execute(manager);
            } catch (RuntimeException e) {
                Log.printTrace("%d: ", currentStep);

                Log.info("Deadlock detected at time %d involving...\n%s\n\n",
                        currentStep,
                        e.getMessage());
                return;
            }

            Log.printTrace("%d: ", currentStep);

            /* Add the process to the correct queue according to its state. */
            if (currentProcess.isBlocked()) {
                currentStep--;

                blockedQueue.add(currentProcess);
            } else if (currentProcess.hasWork()) {
                readyQueue.add(currentProcess);
            } else {
                /* Trace that this process successfully terminated. */
                Log.printTrace("\t(process %d terminated)\n", currentProcess.getProcessId());
                currentProcess.endTime = currentStep;
            }
        }

        /* Did not receive any exceptions from deadlocks, */
        /* so everything terminated correctly.            */
        Log.info("All processes successfully terminated.\n");
        for (Process process : processList) {
            Log.info("Process %d: run time = %d, ended at %d\n",
                    process.getProcessId(),
                    process.getRunTime(),
                    process.endTime);
        }

        /* Output one final line to separate from next process. */
        Log.info("\n");
    }
}
