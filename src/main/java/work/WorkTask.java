package work;

import manager.ResourceManager;

public class WorkTask extends Task{

    private int duration;

    public WorkTask (int duration) {
        this.duration = duration;
    }

    @Override
    public int execute(ResourceManager manager) {
        return 0;
    }
}
