package process;

import manager.ResourceManager;
import work.Task;
import work.TaskFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ProcessRunner {
    /* Queue of processes ready to execute. */
    private static Queue<Process> readyQueue;
    /* Queue of processes waiting for a resource. */
    private static List<Process> blockedQueue;
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

        /* Set up the ready queue for execution. */
        readyQueue = new LinkedList<Process>();
        blockedQueue = new LinkedList<Process>();

        manager = new ResourceManager(numResources, readyQueue, blockedQueue);

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
        int currentStep = -1;

        while (readyQueue.peek() != null) {
            /* Update how many tasks have been executed. */
            currentStep++;

            currentProcess = readyQueue.remove();
            currentProcess.execute(manager);

            if (currentProcess.isBlocked()) {
                blockedQueue.add(currentProcess);
            } else if (currentProcess.hasWork()) {
                readyQueue.add(currentProcess);
            } else {
                currentProcess.endTime = currentStep;
                System.out.printf("Process finished at time %d after running for %d cycles.\n", currentProcess.endTime, currentProcess.getRunTime());
            }
        }
        System.out.printf("All processes successfully terminated at step %d.\n", currentStep);
    }
}
