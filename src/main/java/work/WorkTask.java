package work;

import manager.ResourceManager;

public class WorkTask extends Task{

    private int duration;

    public WorkTask (int duration) {
        this.duration = duration;
    }

    @Override
    public void execute(ResourceManager manager) {
    }
}
