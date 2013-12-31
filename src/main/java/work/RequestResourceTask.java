package work;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Task that requests exclusive access to a resource.
 */

import main.Log;
import manager.Resource;
import manager.ResourceManager;
import process.Process;

public class RequestResourceTask extends Task {

    private int resource;

    public RequestResourceTask(int resource) {
        this.resource = resource;
    }

    /* Request exclusive access to a resource. */
    @Override
    public int execute(ResourceManager manager, Process runner) {
        Log.trace("(%d,%d)\n", TaskFactory.REQUEST_RESOURCE, resource);
        if (manager.requestResource(runner, resource) == Resource.SUCCESS) {
            return DONE;
        } else {
            return BLOCKED;
        }
    }
}
