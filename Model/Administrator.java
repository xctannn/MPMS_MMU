package Model;

public class Administrator extends User{
    
    public Administrator(){};

    public Administrator(String id, String username, String password){
        super(id, username, password);
    }
    
    public String generateCode(int listSize){
        return String.format("%04d", listSize + 1);
    }
}
