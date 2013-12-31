package work;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Interface for a task - the basic unit of work in the simulation.
 */

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
