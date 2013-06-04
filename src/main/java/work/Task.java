package work;

import manager.ResourceManager;
import process.Process;

public abstract class Task {
    public static final int DONE = 0;
    public static final int NOT_DONE = 1;
    public static final int BLOCKED = 2;

    /* Performs the work for a given task.            */
    /* Returns DONE when the task has completed.      */
    /* Returns NOT_DONE when the task still has work. */
    public abstract int execute(ResourceManager manager, Process runner);
}
