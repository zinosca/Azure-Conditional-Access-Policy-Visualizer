package azure.conditional.assignments.conditions;

public class ClientApps {
    private boolean configure;
    private boolean browser;
    private boolean mobileAppsAndDesktopClients;
    private boolean legacyAuthenticationClients;
    private boolean exchangeActiveSyncClients;
    private boolean otherClients;

    public ClientApps(boolean configure) {
        this.configure = configure;
    }

    public boolean configuration(boolean browser, boolean mobileAppsAndDesktopClients, boolean legacyAuthenticationClients, boolean exchangeActiveSyncClients, boolean otherClients) {
        if (configure) {
            this.browser = browser;
            this.mobileAppsAndDesktopClients = mobileAppsAndDesktopClients;
            this.legacyAuthenticationClients = legacyAuthenticationClients;
            this.exchangeActiveSyncClients = exchangeActiveSyncClients;
            this.otherClients = otherClients;
            return true;
        } else {
            return false;
        }
    }

    public boolean isConfigure() {
        return configure;
    }

    public boolean isBrowser() {
        if (configure) {
            return browser;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isMobileAppsAndDesktopClients() {
        if (configure) {
            return mobileAppsAndDesktopClients;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isLegacyAuthenticationClients() {
        if (configure) {
            return legacyAuthenticationClients;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isOtherClients() {
        if (configure) {
            return otherClients;
        } else {
            throw new RuntimeException("configure is false");
        }
    }

    public boolean isExchangeActiveSyncClients() {
        if (configure) {
            return exchangeActiveSyncClients;
        } else {
            throw new RuntimeException("configure is false");
        }
    }
}
