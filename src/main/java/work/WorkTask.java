package work;

import manager.ResourceManager;
import process.Process;

public class WorkTask extends Task{

    private int duration;

    public WorkTask (int duration) {
        this.duration = duration;
    }

    @Override
    public int execute(ResourceManager manager, Process process) {
        duration--;

        /* Check if the work has been completed and return as appropriate. */
        if(duration == 0) {
            return Task.DONE;
        } else {
            return Task.NOT_DONE;
        }
    }
}
