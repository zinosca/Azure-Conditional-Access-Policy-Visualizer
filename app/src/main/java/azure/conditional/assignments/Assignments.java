package azure.conditional.assignments;

import azure.conditional.accesscontrols.AccessControl;
import azure.conditional.assignments.conditions.Conditions;
import azure.conditional.assignments.targetresources.TargetResources;
import azure.conditional.assignments.users.UserGroup;

public class Assignments {
    private final UserGroup userGroup;
    private final TargetResources targetResources;
    private final Conditions conditions;

    public Assignments(UserGroup userGroup, TargetResources targetResources, Conditions conditions) {
        this.userGroup = userGroup;
        this.targetResources = targetResources;
        this.conditions = conditions;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public TargetResources getTargetResources() {
        return targetResources;
    }

    public Conditions getConditions() {
        return conditions;
    }
}
