package process;

import manager.Resource;
import manager.ResourceManager;
import work.Task;

public class Process {
    public static final int BLOCKED = 0;
    public static final int COMPLETE = 1;
    public static final int EXECUTING = 2;
    /* List of tasks to be performed by this process. */
    private Task[] tasks;
    /* Which task is to be executed next. Begins with 0. */
    private int currentTask;
    /* Which resource the process is waiting on. */
    private Resource requestedResource;

    public Process(Task[] tasks) {
        this.tasks = tasks;
        currentTask = 0;
    }

    /* Executes the next tasks in the process.           */
    /* Returns BLOCKED when a request was not filled.    */
    /* Returns COMPLETE when completed all tasks.        */
    /* Returns EXECUTING when tasks are still available. */
    public int execute(ResourceManager manager) {
        /* Run the current task and capture the return value. */
        int taskCode = tasks[currentTask].execute(manager);

        /* Interpret the return code. */
        switch (taskCode) {
            /* See if task completed successfully. */
            case Task.DONE:
                currentTask++;
                if (currentTask == tasks.length) {
                    return COMPLETE;
                } else {
                    return EXECUTING;
                }
            /* See if task requires more time (computation task). */
            case Task.NOT_DONE:
                return EXECUTING;
            /* The task must be waiting on a resource, so block the process. */
            default:
                requestedResource = manager.getResourceById(taskCode);
                return BLOCKED;
        }
    }
}
