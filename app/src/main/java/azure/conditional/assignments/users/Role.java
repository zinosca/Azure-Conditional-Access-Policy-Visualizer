package azure.conditional.assignments.users;

public class Role {
    private String id;

    public Role(String id){
        this.id = id;
    }

    public String getRoleId(){
        return this.id;
    }
}
