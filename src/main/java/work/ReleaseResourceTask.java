package work;

import manager.ResourceManager;
import process.Process;

public class ReleaseResourceTask extends Task {

    private int resource;

    public ReleaseResourceTask(int resource) {
        this.resource = resource;
    }

    @Override
    public int execute(ResourceManager manager, Process runner) {
        return 0;
    }
}
