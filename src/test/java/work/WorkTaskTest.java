package work;

import manager.ResourceManager;
import org.testng.annotations.Test;
import process.Process;

import java.util.LinkedList;

import static org.testng.Assert.assertEquals;

public class WorkTaskTest {

    private static final int COMPUTATIONS = 5;

    @Test
    public void execute() {
        Task workTask = new WorkTask(COMPUTATIONS);
        LinkedList<Process> processes = new LinkedList<Process>();
        ResourceManager manager = new ResourceManager(1, processes, processes);
        Task[] tasks = {workTask};
        Process process = new Process(1, tasks);

        for (int i = 0; i < COMPUTATIONS - 1; ++i) {
            assertEquals(workTask.execute(manager, process), Task.NOT_DONE, "Should not be done executing yet.");
        }
        assertEquals(workTask.execute(manager, process), Task.DONE, "Should now be done executing.");
    }
}
