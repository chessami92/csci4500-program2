package work;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TaskFactoryTest {
    @Test
    void createTask() throws Exception {
        assertEquals(TaskFactory.createTask(1, 0).getClass(), RequestResourceTask.class);
        assertEquals(TaskFactory.createTask(2, 0).getClass(), ReleaseResourceTask.class);
        assertEquals(TaskFactory.createTask(3, 0).getClass(), WorkTask.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void createTask_invalid() {
        TaskFactory.createTask(4, 0);
    }
}
