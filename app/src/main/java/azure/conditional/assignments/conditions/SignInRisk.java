package azure.conditional.assignments.conditions;

public class SignInRisk {
    private boolean high;
    private boolean medium;
    private boolean low;
    private boolean noRisk;
    private final boolean configure;

    public SignInRisk(boolean configure) {
        this.configure = configure;
    }

    public boolean configuration(boolean high, boolean medium, boolean low, boolean noRisk) {
        if (configure) {
            this.high = high;
            this.medium = medium;
            this.low = low;
            this.noRisk = noRisk;
            return true;
        } else {
            return false;
        }
    }

    public boolean isConfigure() {
        return configure;
    }

    public boolean isHigh() {
        if (configure) {
            return high;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isMedium() {
        if (configure) {
            return medium;
        }else{
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isLow() {
        if (configure) {
            return low;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isNoRisk() {
        if (configure) {
            return noRisk;
        } else {
            throw new RuntimeException("configure is false");
        }
    }
}
