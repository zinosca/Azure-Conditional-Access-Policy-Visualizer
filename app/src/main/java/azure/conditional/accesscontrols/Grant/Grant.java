package azure.conditional.accesscontrols.Grant;

import java.util.HashMap;

public class Grant {
    private final boolean grantAccess;

    private final boolean requireAllTheConfigurations;

    private final HashMap <String, Boolean> configurationHashMap;

    public Grant(boolean grantAccess, boolean requireAllTheConfigurations) {
        this.grantAccess = grantAccess;
        this.requireAllTheConfigurations = requireAllTheConfigurations;
        this.configurationHashMap = new HashMap<>();
        configurationHashMap.put("Require multifactor authentication", false);
        configurationHashMap.put("Require device to be marked as compliant", false);
        configurationHashMap.put("Require approved client app", false);
        configurationHashMap.put("Require authentication strength", false);
        configurationHashMap.put("Require Hybrid Azure AD joined device", false);
        configurationHashMap.put("Require app protection policy", false);
        configurationHashMap.put("Require password change", false);

    }

    public boolean getGrantAccess() {
        return this.grantAccess;
    }

    public boolean getrequireAllTheConfigurations() {
        return this.requireAllTheConfigurations;
    }

    public HashMap<String, Boolean> getConfigurationHashMap() {
        return this.configurationHashMap;
    }

    public void setmulifactorauthentication(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require multifactor authentication", value);
        }
    }

    public void setdevicecompliant(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require device to be marked as compliant", value);
        }
    }

    public void setapprovedclientapp(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require approved client app", value);
        }
    }

    public void setauthenticationstrength(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require authentication strength", value);
        }
    }

    public void sethybridazureADjoineddevice(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require Hybrid Azure AD joined device", value);
        }
    }

    public void setappprotectionpolicy(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require app protection policy", value);
        }
    }

    public void setpasswordchange(boolean value) {
        if(grantAccess){
            this.configurationHashMap.put("Require password change", value);
        }
    }

}
