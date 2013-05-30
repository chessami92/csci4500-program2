package manager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import process.Process;
import work.RequestResourceTask;
import work.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

public class ResourceManagerTest {

    private ResourceManager manager;
    private Process requester;
    private Queue<Process> running;
    private List<Process> blocked;

    @BeforeMethod
    public void setup() {
        running = new LinkedList<Process>();
        blocked = new LinkedList<Process>();

        manager = new ResourceManager(3, running, blocked);

        Task task = new RequestResourceTask(1);
        Task[] tasks = {task};
        requester = new Process(1, tasks);

        running.add(requester);
    }

    @Test
    public void requestReleaseRequest() {
        assertEquals(manager.requestResource(requester, 1), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
        assertEquals(manager.releaseResource(requester, 1), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
        assertEquals(manager.requestResource(requester, 1), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void requestRequestRelease() {
        assertEquals(manager.requestResource(requester, 1), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
        requester.execute(manager);
    }

    @Test
    public void requestAll() {
        assertEquals(manager.requestResource(requester, 1), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
        assertEquals(manager.requestResource(requester, 2), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
        assertEquals(manager.requestResource(requester, 3), Resource.SUCCESS);
        assertEquals(running.peek(), requester);
    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void requestInvalid() {
        assertEquals(manager.requestResource(requester, 4), Resource.SUCCESS);
    }
}
