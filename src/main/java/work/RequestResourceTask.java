package work;

import manager.ResourceManager;

public class RequestResourceTask extends Task{

    private int resource;

    public RequestResourceTask(int resource) {
        this.resource = resource;
    }

    @Override
    public int execute(ResourceManager manager) {
        return 0;
    }
}
