package work;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Task that releases exclusive access to a previously allocated resource.
 */

import main.Log;
import manager.Resource;
import manager.ResourceManager;
import process.Process;

public class ReleaseResourceTask extends Task {

    private int resource;

    public ReleaseResourceTask(int resource) {
        this.resource = resource;
    }

    /* Release a resource. */
    @Override
    public int execute(ResourceManager manager, Process runner) {
        Log.trace("(%d,%d)\n", TaskFactory.RELEASE_RESOURCE, resource);

        if(manager.releaseResource(runner, resource) == Resource.SUCCESS) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }
}
