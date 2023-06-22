package azure.conditional.assignments.conditions;

public class Conditions {
    private UserRisk userRisk;
    private SignInRisk signInRisk;
    private DevicePlatforms devicePlatformsInclution;
    private DevicePlatforms devicePlatformsExclution;
    private Locations locationsInclution;
    private Locations locationsExclution;
    private ClientApps clientApps;
    private FilterForDevices filterForDevices;

    public Conditions(UserRisk userRisk, SignInRisk signInRisk, DevicePlatforms devicePlatformsInclution, DevicePlatforms devicePlatformsExclution, Locations locationsInclution, Locations locationsExclution, ClientApps clientApps, FilterForDevices filterForDevices) {
        this.userRisk = userRisk;
        this.signInRisk = signInRisk;
        this.devicePlatformsInclution = devicePlatformsInclution;
        this.devicePlatformsExclution = devicePlatformsExclution;
        this.locationsInclution = locationsInclution;
        this.locationsExclution = locationsExclution;
        this.clientApps = clientApps;
        this.filterForDevices = filterForDevices;
    }

    public UserRisk getUserRisk() {
        return userRisk;
    }

    public SignInRisk getSignInRisk() {
        return signInRisk;
    }

    public DevicePlatforms getDevicePlatformsInclution() {
        return devicePlatformsInclution;
    }

    public DevicePlatforms getDevicePlatformsExclution() {
        return devicePlatformsExclution;
    }

    public Locations getLocationsInclution() {
        return locationsInclution;
    }

    public Locations getLocationsExclution() {
        return locationsExclution;
    }

    public ClientApps getClientApps() {
        return clientApps;
    }

    public FilterForDevices getFilterForDevices() {
        return filterForDevices;
    }

}
