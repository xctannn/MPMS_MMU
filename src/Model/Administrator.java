package src.Model;

/*
 * Chua Hui Yi
 * Purpose: Administrator model that inherits from User class and contains the attributes of an Administrator
 */
public class Administrator extends User{
    
    /*
     * Chua Hui Yi
     * No-Arg Constructor
     * Purpose: For JSON serializer to serialize and deserialize Administrator object into and from administrator.json
     */
    public Administrator(){};

    /*
     * Chua Hui Yi
     * Purpose: To construct a new Administrator
     * Use the super keyword to call the constructor of the parent class (User)
     */
    public Administrator(String id, String username, String password){
        super(id, username, password);
    }
}
