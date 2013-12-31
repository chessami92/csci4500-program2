package work;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Task that simulations computation for many executions.
 */

import main.Log;
import manager.ResourceManager;
import process.Process;

public class WorkTask extends Task{

    private int duration;

    public WorkTask (int duration) {
        this.duration = duration;
    }

    /* Execute one more iteration. Return DONE when finished. */
    @Override
    public int execute(ResourceManager manager, Process process) {
        Log.trace("(%d,%d)\n", TaskFactory.COMPUTE, duration);

        duration--;

        /* Check if the work has been completed and return as appropriate. */
        if(duration == 0) {
            return Task.DONE;
        } else {
            return Task.NOT_DONE;
        }
    }
}
