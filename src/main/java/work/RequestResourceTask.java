package work;

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
        if (manager.requestResource(runner, resource) == Resource.SUCCESS) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }
}
