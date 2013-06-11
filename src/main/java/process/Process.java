package process;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Object representing a process in the simulation.
 * Contains a list of tasks to be executed. Also has a reference to a
 * resource that is being requested by this process, which is null when
 * the process is not blocking for any resources.
 */

import main.Log;
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

        Log.trace("process %d: ", processId);

        /* Run the current task and capture the return value. */
        int taskCode = tasks[currentTask].execute(manager, this);

        /* See if task completed successfully. */
        if (taskCode == Task.DONE) {
            /* Execute next task when process is next invoked. */
            currentTask++;
        } else if (taskCode == Task.BLOCKED) {
            /* Failed requests for resources count as zero time. */
            runTime--;
        }
    }

    /* See if this process has any tasks left to execute. */
    public boolean hasWork() {
        return currentTask < tasks.length;
    }

    /* See if this process is blocked waiting for a resource. */
    public boolean isBlocked() {
        return requestedResource != null;
    }

    /* See how many steps this process took to execute. */
    public int getRunTime() {
        return runTime;
    }

    /* Return which process this is. */
    public int getProcessId() {
        return processId;
    }
}
