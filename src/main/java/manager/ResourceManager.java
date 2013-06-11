package manager;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Coordinator of resource requests. Attempts to allocate a resource
 * given a resource id (1-based counting).
 * Whenever a process releases a resource, the blocked queue is checked
 * for any process blocking for that resource. If a process is found, it
 * is moved to the ready queue.
 * Whenever a process blocks for a resource, the simulation is checked
 * for any deadlock situations.
 */

import main.Log;
import process.Process;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ResourceManager {
    /* Full listing of all resources. */
    private Resource[] resources;
    /* Queue of processes ready to execute. */
    private Queue<Process> readyQueue;
    /* Queue of processes waiting for a resource. */
    private List<Process> blockedQueue;

    /* Create a resource manager with the given number of resources. */
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

    /* Look into resource array and get a particular resource. */
    /* Converts from the 1-based counting to 0-based.          */
    public Resource getResourceById(int id) {
        return resources[id - 1];
    }

    /* Attempt to obtain exclusive use of a resource. */
    /* Check for deadlock if it blocks.               */
    public int requestResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        if (resource.requestResource(requester) == Resource.SUCCESS) {
            return Resource.SUCCESS;
        } else {
            checkForDeadlock(requester);
            return Resource.FAIL;
        }
    }

    /* Release exclusive use of a resource by a process.      */
    /* If a process is waiting for this resource, it is moved */
    /* to the ready queue to be executed.                     */
    public int releaseResource(Process requester, int id) {
        Resource resource = getResourceById(id);

        /* Release the resource and check that it was released properly. */
        if (resource.releaseResource(requester) == Resource.SUCCESS) {
            /* Check if any other processes were waiting on this resource. */
            for (Process process : blockedQueue) {
                if (process.requestedResource == resource) {
                    Log.trace("\t(process %d unblocked)\n", process.getProcessId());
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

    /* See if there is a deadlock by looking at the "resource graph." */
    /* This is actually just following references from process to     */
    /* resource until null is found, or the original process.         */
    private void checkForDeadlock(Process requester) {
        /* A list of processes involved in a deadlock. */
        List<Process> involvedProcesses = new LinkedList<Process>();
        /* A list of resources involved in a deadlock. */
        List<Resource> involvedResources = new LinkedList<Resource>();
        involvedProcesses.add(requester);

        while (true) {
            /* Look at the resource requested by the process. */
            involvedResources.add(0, involvedProcesses.get(0).requestedResource);
            if (involvedResources.get(0) == null) {
                break;  /* If no resource, there is no deadlock. */
            }

            /* Look at the process holding the resource. */
            involvedProcesses.add(0, involvedResources.get(0).processAssigned);
            if (involvedProcesses.get(0) == null) {
                break;  /* If no process, there is no deadlock. */
            }

            /* If we looped back to the original process, there is a deadlock! */
            if (requester == involvedProcesses.get(0)) {
                /* Remove duplicate reference to original process. */
                involvedProcesses.remove(0);
                throw new RuntimeException(createDeadlockMessage(
                        involvedProcesses,
                        involvedResources));
            }
        }
    }

    /* Format the message that will be thrown in an exception back up */
    /* to the process runner. The process runner then prints it.      */
    private String createDeadlockMessage(List<Process> involvedProcesses,
                                         List<Resource> involvedResources) {
        StringBuilder sb = new StringBuilder();

        /* List off all of the PIDs involved in the deadlock. */
        sb.append("    processes:");
        for (Process process : involvedProcesses) {
            sb.append(" ").append(process.getProcessId());
        }

        /* List off all of the resource IDs involved in the deadlock. */
        sb.append("\n    resources:");
        for (Resource resource : involvedResources) {
            sb.append(" ").append(resource.getResourceId());
        }

        return sb.toString();
    }
}
