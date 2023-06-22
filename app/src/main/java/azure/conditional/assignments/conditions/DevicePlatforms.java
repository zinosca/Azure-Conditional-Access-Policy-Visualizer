package azure.conditional.assignments.conditions;

public class DevicePlatforms {
    private boolean configure;
    private boolean android;
    private boolean ios;
    private boolean windowsphone;
    private boolean macos;
    private boolean linux;

    public DevicePlatforms(boolean configure) {
        this.configure = configure;
    }

    public boolean configuration(boolean android, boolean ios, boolean windowsphone, boolean macos, boolean linux) {
        if (configure) {
            this.android = android;
            this.ios = ios;
            this.windowsphone = windowsphone;
            this.macos = macos;
            this.linux = linux;
            return true;
        } else {
            return false;
        }
    }

    public boolean isConfigure() {
        return configure;
    }

    public boolean isAndroid() {
        if (configure) {
            return android;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isIos() {
        if (configure) {
            return ios;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isWindowsphone() {
        if (configure) {
            return windowsphone;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isMacos() {
        if (configure) {
            return macos;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isLinux() {
        if (configure) {
            return linux;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

}
