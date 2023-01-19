package src.Model;

/*
 * Abstract class of a User model, to be inherited to more specific user types
 */
public abstract class User {
    private String id;
    private String username;
    private String password;

    // CONSTRUCTORS
    /*
     * Tan Xiao Chin
     * No-Arg Constructor
     */
    public User(){}

    /*
     * Tan Xiao Chin
     * Constructor
     */
    public User(String id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // GETTERS AND SETTERS
    // Tan Xiao Chin
    public String getId(){
        return id;
    }

    // Tan Xiao Chin
    public String getUsername(){
        return username;
    }

    // Tan Xiao Chin
    public String getPassword(){
        return password;
    }

    // Tan Xiao Chin
    public void setId(String id){
        this.id = id;
    }

    // Tan Xiao Chin
    public void setUsername(String username){
        this.username = username;
    }

    // Tan Xiao Chin
    public void setPassword(String password){
        this.password = password;
    }
}
