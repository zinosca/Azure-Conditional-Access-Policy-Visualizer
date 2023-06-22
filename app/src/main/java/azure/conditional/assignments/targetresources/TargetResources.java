package azure.conditional.assignments.targetresources;

import java.util.ArrayList;

public class TargetResources {
    private ArrayList<Resource> resourceArrayListInclude;
    private ArrayList<Resource> resourceArrayListExclude;
    private UserActions userActions;

    public void addResourceInclude(Resource resource) {
        if(resourceArrayListInclude==null){
           resourceArrayListInclude = new ArrayList<Resource>();
        }
        this.resourceArrayListInclude.add(resource);
    }
    public void addResourceExclude(Resource resource) {
        if(resourceArrayListExclude==null){
           resourceArrayListExclude = new ArrayList<Resource>();
        }
        this.resourceArrayListExclude.add(resource);
    }

    public void setUserActions(UserActions userActions) {
        this.userActions = userActions;
    }

    public UserActions getUserActions() {
        return userActions;
    }

    public ArrayList<Resource> getResourceArrayListInclude() {
        return this.resourceArrayListInclude;
    }

    public ArrayList<Resource> getResourceArrayListExclude() {
        return resourceArrayListExclude;
    }
}
