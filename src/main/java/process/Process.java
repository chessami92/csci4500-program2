package process;

import manager.Resource;
import manager.ResourceManager;
import work.Task;

public class Process {
    /* Which resource the process is waiting on. */
    public Resource requestedResource;
    /* When this process finished running in time. */
    public int endTime;
    /* List of tasks to be performed by this process. */
    private Task[] tasks;
    /* Which task is to be executed next. Begins with 0. */
    private int currentTask;
    /* How long this process has taken to run. */
    private int runTime;
    /* The ID for this process. */
    private int processId;

    public Process(int processId, Task[] tasks) {
        this.tasks = tasks;
        currentTask = 0;
        runTime = 0;
        /* Signify it hasn't ended yet. */
        endTime = -1;

        this.processId = processId;
    }

    /* Executes the next tasks in the process. */
    public void execute(ResourceManager manager) {
        /* Update the total running time. */
        runTime++;

        /* Run the current task and capture the return value. */
        int taskCode = tasks[currentTask].execute(manager, this);

        /* See if task completed successfully. */
        if (taskCode == Task.DONE) {
            currentTask++;
        }
    }

    public boolean hasWork() {
        return currentTask < tasks.length;
    }

    public boolean isBlocked() {
        return requestedResource != null;
    }

    public int getRunTime() {
        return runTime;
    }

    public int getProcessId() {
        return processId;
    }
}
