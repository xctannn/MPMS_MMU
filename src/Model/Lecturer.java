package src.Model;

import java.util.ArrayList;

/*
 * Chua Hui Yi
 * Purpose: Lecturer model that inherits from User class and contains the attributes of a Lecturer
 */
public class Lecturer extends User{
    private ArrayList<String> projectIDList;

    /*
     * Chua Hui Yi
     * No-Arg Constructor
     * Purpose: For JSON serializer to serialize and deserialize Lecturer object into and from lecturer.json
     */
    public Lecturer(){};

    /*
     * Chua Hui Yi
     * Purpose: To construct a new Lecturer
     * Use the super keyword to call the constructor of the parent class (User)
     */
    public Lecturer(String id, String username, String password){
        super(id, username, password);
        this.projectIDList = new ArrayList<>();
    }

    // Chua Hui Yi
    public ArrayList<String> getprojectIDList(){
        return projectIDList;
    }

    // Chua Hui Yi
    public void addProject(String projectId){
        projectIDList.add(projectId);
    }

    // Chua Hui Yi
    public void removeProject(String projectId){
        projectIDList.remove(projectId);
    }
}
