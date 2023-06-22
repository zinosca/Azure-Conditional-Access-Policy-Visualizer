package azure.conditional.assignments.conditions;

import java.util.ArrayList;

public class FilterForDevices {
    private boolean configure;
    private ArrayList<FilteredDevicesRule> filteredDevicesRules;
    private boolean includeFilter;

    public FilterForDevices(boolean configure, boolean includeFilter) {
        this.configure = configure;
        this.includeFilter = includeFilter;
        filteredDevicesRules = new ArrayList<FilteredDevicesRule>();
    }

    public boolean getConfigure(){
        return configure;
    }

    public boolean setFilteredDevicesRules(FilteredDevicesRule filteredDevicesRules){
        if(!configure) {
            this.filteredDevicesRules.add(filteredDevicesRules);
            return true;
        }else{
            throw new RuntimeException("configure is false");
        }
    }

    public ArrayList<FilteredDevicesRule> getFilteredDevicesRules(){
        return filteredDevicesRules;
    }




}
