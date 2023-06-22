package azure.conditional.assignments.users;

public class User {
    private String id;

    public User(String id) {
        this.id = id;
    }

    public String getUserId() {
        return id;
    }
}