package manager;

import process.Process;

import java.util.List;
import java.util.Queue;

public class ResourceManager {
    private Resource[] resources;
    private Queue<Process> readyQueue;
    private List<Process> blockedQueue;

    /* Create a resouce manager with the given number of resources. */
    public ResourceManager(int numResources, Queue<Process> readyQueue,
                           List<Process> blockedQueue) {
        /* Create array of resources available to the processes. */
        resources = new Resource[numResources];
        for (int i = 0; i < numResources; ++i) {
            resources[i] = new Resource();
        }

        this.readyQueue = readyQueue;
        this.blockedQueue = blockedQueue;
    }

    public Resource getResourceById(int id) {
        return resources[id - 1];
    }

    public int requestResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        if (resource.requestResource(requester) == Resource.SUCCESS) {
            return Resource.SUCCESS;
        } else {
            blockedQueue.add(requester);

            return Resource.FAIL;
        }
    }

    public int releaseResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        if (resource.releaseResource(requester) == Resource.SUCCESS) {
            for (Process process : blockedQueue) {
                if (process.requestedResource == resource) {
                    blockedQueue.remove(process);
                    readyQueue.add(process);
                    break;
                }
            }

            return Resource.SUCCESS;
        } else {
            return Resource.FAIL;
        }
    }
}
