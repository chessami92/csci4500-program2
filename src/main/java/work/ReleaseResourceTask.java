package work;

import manager.Resource;
import manager.ResourceManager;
import process.Process;

public class ReleaseResourceTask extends Task {

    private int resource;

    public ReleaseResourceTask(int resource) {
        this.resource = resource;
    }

    @Override
    public int execute(ResourceManager manager, Process runner) {
        if(manager.releaseResource(runner, resource) == Resource.SUCCESS) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }
}
