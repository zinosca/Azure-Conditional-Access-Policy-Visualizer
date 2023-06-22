package azure.conditional.accesscontrols.Session;

import java.util.HashMap;

public class Session {

    private HashMap<String, Boolean> configurationHashMap = new HashMap<>();

    public Session() {
        configurationHashMap.put("Use app enforced restrictions", false);
        configurationHashMap.put("Use Conditional Access App Control", false);
        configurationHashMap.put("Sign-in frequency", false);
        configurationHashMap.put("Persistent browser session", false);
        configurationHashMap.put("Customize continuous access evaluation", false);
        configurationHashMap.put("Disable resilience defaults", false);
        configurationHashMap.put("Require token protection for sign-in sessions (Preview)", false);
    }

    public HashMap<String, Boolean> getConfigurationHashMap() {
        return this.configurationHashMap;
    }

    public void setappenforcedrestrictions(boolean value) {
        this.configurationHashMap.put("Use app enforced restrictions", value);
    }

    public void setconditionalaccessappcontrol(boolean value) {
        this.configurationHashMap.put("Use Conditional Access App Control", value);
    }

    public void setsigninfrequency(boolean value) {
        this.configurationHashMap.put("Sign-in frequency", value);
    }

    public void setpersistentbrowsersession(boolean value) {
        this.configurationHashMap.put("Persistent browser session", value);
    }

    public void setcustomizecontinuousaccessevaluation(boolean value) {
        this.configurationHashMap.put("Customize continuous access evaluation", value);
    }

    public void setdisableresiliencedefaults(boolean value) {
        this.configurationHashMap.put("Disable resilience defaults", value);
    }

    public void setrequiretokenprotectionforsigninsessions(boolean value) {
        this.configurationHashMap.put("Require token protection for sign-in sessions (Preview)", value);
    }
}
