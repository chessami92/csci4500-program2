package process;

import manager.Resource;
import manager.ResourceManager;
import work.Task;

public class Process {
    /* Which resource the process is waiting on. */
    public Resource requestedResource;
    /* List of tasks to be performed by this process. */
    private Task[] tasks;
    /* Which task is to be executed next. Begins with 0. */
    private int currentTask;

    public Process(Task[] tasks) {
        this.tasks = tasks;
        currentTask = 0;
    }

    /* Executes the next tasks in the process.           */
    public void execute(ResourceManager manager) {
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
}
