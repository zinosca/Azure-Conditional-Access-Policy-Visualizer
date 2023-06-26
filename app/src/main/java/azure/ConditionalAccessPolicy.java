package azure;

import azure.conditional.accesscontrols.AccessControl;
import azure.conditional.assignments.Assignments;

public class ConditionalAccessPolicy {

    private final String policyName;
    private final String id;
    private final String policyState;
    private final Assignments assignments;
    private final AccessControl accessControl;

    public ConditionalAccessPolicy(String policyName, String id, String policyState, Assignments assignments, AccessControl accessControl) {
        this.policyName = policyName;
        this.id = id;
        this.policyState = policyState;
        this.assignments = assignments;
        this.accessControl = accessControl;
    }

    public String getPolicyName() {
        return policyName;
    }

    public String getPolicyState() {
        return policyState;
    }

    public String getId() {
        return id;
    }

    public Assignments getAssignments() {
        return assignments;
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

}
