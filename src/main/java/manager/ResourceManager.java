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
            resources[i] = new Resource(i + 1);
        }

        this.readyQueue = readyQueue;
        this.blockedQueue = blockedQueue;
    }

    public Resource getResourceById(int id) {
        return resources[id - 1];
    }

    public int requestResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        return resource.requestResource(requester);
    }

    public int releaseResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        /* Release the resource and check that it was released properly. */
        if (resource.releaseResource(requester) == Resource.SUCCESS) {
            /* Check if any other processes were waiting on this resource. */
            for (Process process : blockedQueue) {
                if (process.requestedResource == resource) {
                    /* Move the process from blocked to ready queue. */
                    blockedQueue.remove(process);
                    readyQueue.add(process);
                    /* Signify that the process is no longer waiting. */
                    process.requestedResource = null;
                    break;
                }
            }

            return Resource.SUCCESS;
        } else {
            return Resource.FAIL;
        }
    }
}
