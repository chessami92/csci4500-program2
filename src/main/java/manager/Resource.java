package manager;

import process.Process;

public class Resource {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    private Process processAssigned;

    /* Method for requesting this resource.           */
    /* Returns SUCCESS if the resource was available. */
    /* Returns FAIL if the resource was unavailable.  */
    public int requestResource(Process requester) {
        if (processAssigned == null) {
            processAssigned = requester;
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    /* Method for releasing this resource.                 */
    /* Returns SUCCESS if the owner of resource made call. */
    /* Returns FAIL if the improper owner made the call.   */
    public int releaseResource(Process requester) {
        if (processAssigned == requester) {
            processAssigned = null;
            return SUCCESS;
        } else {
            return FAIL;
        }
    }
}
