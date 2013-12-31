package work;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Convenient method for creating tasks given two integers.
 * The first integer is the action, and the second integer's meaning
 * depends on the first integer.
 */

public class TaskFactory {
    public static final int REQUEST_RESOURCE = 1;
    public static final int RELEASE_RESOURCE = 2;
    public static final int COMPUTE = 3;

    /* Creates a task given two integers. */
    public static Task createTask(int action, int n) {
        switch(action) {
            case REQUEST_RESOURCE:
                return new RequestResourceTask(n);
            case RELEASE_RESOURCE:
                return new ReleaseResourceTask(n);
            case COMPUTE:
                return new WorkTask(n);
            default:
                throw new IllegalArgumentException("Action #" + action + " is invalid.");
        }
    }
}
