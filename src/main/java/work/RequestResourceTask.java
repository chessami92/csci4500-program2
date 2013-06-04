package work;

import main.Log;
import manager.Resource;
import manager.ResourceManager;
import process.Process;

public class RequestResourceTask extends Task {

    private int resource;

    public RequestResourceTask(int resource) {
        this.resource = resource;
    }

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
