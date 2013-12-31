package manager;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Object representing a resource in the simulation.
 * Contains a reference to a process that owns this resource, which is
 * null when the resource is available.
 */

import main.Log;
import process.Process;

public class Resource {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    /* Which process currently holds this resource. */
    protected Process processAssigned;
    /* The ID for this resource. */
    private int resourceId;

    public Resource(int resourceId) {
        this.resourceId = resourceId;
    }

    /* Method for requesting this resource.           */
    /* Returns SUCCESS if the resource was available. */
    /* Returns FAIL if the resource was unavailable.  */
    public int requestResource(Process requester) {
        if (processAssigned == null) {
            Log.trace("\t(resource %d allocated)\n", resourceId);
            processAssigned = requester;
            return SUCCESS;
        } else {
            Log.trace("\t(resource %d unavailable)\n", resourceId);
            requester.requestedResource = this;
            return FAIL;
        }
    }

    /* Method for releasing this resource.                 */
    /* Returns SUCCESS if the owner of resource made call. */
    /* Returns FAIL if the improper owner made the call.   */
    public int releaseResource(Process requester) {
        if (processAssigned == requester) {
            Log.trace("\t(resource %d released)\n", resourceId);
            processAssigned = null;
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    /* Return which resource this is. */
    public int getResourceId() {
        return resourceId;
    }
}
