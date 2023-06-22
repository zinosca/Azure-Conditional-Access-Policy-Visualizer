package azure.conditional.assignments.conditions;

public class UserRisk {

    private boolean high;
    private boolean medium;
    private boolean low;
    private final boolean configure;

    public UserRisk(boolean configure) {
        this.configure = configure;
    }


    public boolean configuration(boolean high, boolean medium, boolean low) {
        if (configure) {
            this.high = high;
            this.medium = medium;
            this.low = low;
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
}
