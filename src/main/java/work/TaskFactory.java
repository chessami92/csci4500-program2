package work;

public class TaskFactory {
    private static final int REQUEST_RESOURCE = 1;
    private static final int RELEASE_RESOURCE = 2;
    private static final int COMPUTE = 3;

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
