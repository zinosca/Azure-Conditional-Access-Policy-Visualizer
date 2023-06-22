package azure.conditional.assignments.conditions;

import java.util.ArrayList;

public class Locations {

    private final ArrayList<String> locationsList;
    private final boolean configure;

    public Locations(boolean configure) {
        this.configure = configure;
        locationsList = new ArrayList<String>();
    }

    public boolean getConfigure() {
        return configure;
    }

    public void setLocation(String location) {
        locationsList.add(location);
    }

    public ArrayList<String> getLocations() {
        return locationsList;
    }
}
