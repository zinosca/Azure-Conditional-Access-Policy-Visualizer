package azure.conditional.assignments.targetresources;

public class UserActions {
    private boolean registerSecurityInformation;
    private boolean registerOrJoinDevices;

    public UserActions(boolean registerSecurityInformation, boolean registerOrJoinDevices) {
        this.registerSecurityInformation = registerSecurityInformation;
        this.registerOrJoinDevices = registerOrJoinDevices;
    }

    public boolean getRegisterSecurityInformation() {
        return this.registerSecurityInformation;
    }

    public boolean getRegisterOrJoinDevices() {
        return this.registerOrJoinDevices;
    }

}
