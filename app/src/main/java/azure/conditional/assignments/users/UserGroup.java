package azure.conditional.assignments.users;

import java.util.ArrayList;

public class UserGroup {
    private ArrayList<User> includeUsers;
    private ArrayList<User> excludeUsers;
    private ArrayList<Group> includeGroups;
    private ArrayList<Group> excludeGroups;
    private ArrayList<Role> includeRoles;
    private ArrayList<Role> excludeRoles;
    private ArrayList<GuestsOrExternalUsers> includeGuestsOrExternalUsers;
    private ArrayList<GuestsOrExternalUsers> excludeGuestsOrExternalUsers;

    public UserGroup(){
        this.includeUsers = new ArrayList<User>();
        this.excludeUsers = new ArrayList<User>();
        this.includeGroups = new ArrayList<Group>();
        this.excludeGroups = new ArrayList<Group>();
        this.includeRoles = new ArrayList<Role>();
        this.excludeRoles = new ArrayList<Role>();
    }

    public void addIncludeUser(User user){
        this.includeUsers.add(user);
    }

    public void addExcludeUser(User user){
        this.excludeUsers.add(user);
    }

    public void addIncludeGroup(Group group){
        this.includeGroups.add(group);
    }

    public void addExcludeGroup(Group group){
        this.excludeGroups.add(group);
    }

    public void addIncludeRole(Role role){
        this.includeRoles.add(role);
    }

    public void addExcludeRole(Role role){
        this.excludeRoles.add(role);
    }

    public void addIncludeGuestsOrExternalUsers(GuestsOrExternalUsers guestsOrExternalUsers){
        if(!(guestsOrExternalUsers.getKind().equals("null"))){
            this.includeGuestsOrExternalUsers.add(guestsOrExternalUsers);
        }
    }

    public void addExcludeGuestsOrExternalUsers(GuestsOrExternalUsers guestsOrExternalUsers){
        if(!(guestsOrExternalUsers.getKind().equals("null"))){
            this.excludeGuestsOrExternalUsers.add(guestsOrExternalUsers);
        }
    }

    public ArrayList<User> getIncludeUsers(){
        return this.includeUsers;
    }

    public ArrayList<User> getExcludeUsers(){
        return this.excludeUsers;
    }

    public ArrayList<Group> getIncludeGroups(){
        return this.includeGroups;
    }

    public ArrayList<Group> getExcludeGroups(){
        return this.excludeGroups;
    }

    public ArrayList<Role> getIncludeRoles(){
        return this.includeRoles;
    }

    public ArrayList<Role> getExcludeRoles(){
        return this.excludeRoles;
    }

    public ArrayList<GuestsOrExternalUsers> getIncludeGuestsOrExternalUsers(){
        return this.includeGuestsOrExternalUsers;
    }

    public ArrayList<GuestsOrExternalUsers> getExcludeGuestsOrExternalUsers(){
        return this.excludeGuestsOrExternalUsers;
    }
}
