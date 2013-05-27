package work;

import manager.ResourceManager;

public class ReleaseResourceTask extends Task {

    private int resource;

    public ReleaseResourceTask(int resource) {
        this.resource = resource;
    }

    @Override
    public int execute(ResourceManager manager) {
        return 0;
    }
}
