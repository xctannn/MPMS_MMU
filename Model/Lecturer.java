package Model;

import java.util.ArrayList;

public class Lecturer extends User{
    private ArrayList<String> projectIDList;

    // No-arg constructor for Json Parser
    private Lecturer(){};
    
    public Lecturer(String id, String username, String password, ArrayList<String> projectList){
        super(id, username, password);
        this.projectIDList = projectList;
    }

    public ArrayList<String> getprojectIDList(){
        return projectIDList;
    }

    public void addproject(String projectID){
        projectIDList.add(projectID);
    }
}
