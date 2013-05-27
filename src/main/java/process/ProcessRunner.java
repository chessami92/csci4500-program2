package process;

import manager.ResourceManager;
import work.Task;
import work.TaskFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProcessRunner {
    /* Queue of processes ready to execute. */
    private static Queue<Process> readyQueue;
    /* Queue of processes waiting for a resource. */
    private static Queue<Process> blockedQueue;
    /* Controls granting and releasing resources. */
    private static ResourceManager manager;

    /* Read from the system input to create a queue of processes */
    /* that each contain a list of tasks. Put all processes into */
    /* the ready queue initially.                                */
    public ProcessRunner(Scanner in, int numProcesses, int numResources) {
        /* How many tasks are in the current process. */
        int numTasks;
        /* The two parameters for the task. */
        int operation, n;
        Task[] processTasks;

        /* Set up the ready queue and blocked queues for execution. */
        readyQueue = new LinkedList<Process>();
        blockedQueue = new LinkedList<Process>();

        manager = new ResourceManager(numResources);

        for (int i = 0; i < numProcesses; ++i) {
            numTasks = in.nextInt();            /* See how many tasks. */
            processTasks = new Task[numTasks];
            /* Populate the list of tasks to be executed for the process. */
            for (int j = 0; j < numTasks; ++j) {
                operation = in.nextInt();
                n = in.nextInt();

                processTasks[j] = TaskFactory.createTask(operation, n);
            }
            /* Create the process and add it to the ready queue. */
            readyQueue.add(new Process(processTasks));
        }
    }

    public void runProcesses() {
        Process currentProcess;

        while (readyQueue.peek() != null) {
            currentProcess = readyQueue.remove();
            switch (currentProcess.execute(manager)) {
                case Process.BLOCKED:
                    blockedQueue.add(currentProcess);
                    break;
                case Process.COMPLETE:
                    System.out.println();
                    break;
                case Process.EXECUTING:
                    readyQueue.add(currentProcess);
                    break;
                default:
                    System.err.println("Unknown response from executing on a process.");
            }
        }
    }
}
