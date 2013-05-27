package manager;

public class ResourceManager {
    private Resource[] resources;

    public ResourceManager(int numResources) {
        /* Create array of resources available to the processes. */
        resources = new Resource[numResources];
    }

    public Resource getResourceById(int id) {
        return resources[id];
    }
}
