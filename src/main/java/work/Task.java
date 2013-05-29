package work;

import manager.ResourceManager;
import process.Process;

public abstract class Task {
    public static final int DONE = 0;
    public static final int NOT_DONE = -1;

    /* Returns DONE when the task has completed.              */
    /* Returns NOT_DONE when the task still has work.         */
    /* Returns a positive number when waiting for a resource. */
    public abstract int execute(ResourceManager manager, Process runner);
}
