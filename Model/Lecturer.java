package Model;

import java.util.ArrayList;

public class Lecturer extends User{
    private ArrayList<String> projectIDList;

    // No-arg constructor for Json Parser
    private Lecturer(){};
    
    public Lecturer(String id, String username, String password){
        super(id, username, password);
        this.projectIDList = new ArrayList<>();
    }

    public ArrayList<String> getprojectIDList(){
        return projectIDList;
    }

    public void addProject(String projectId){
        projectIDList.add(projectId);
    }

    public void removeProject(String projectId){
        projectIDList.remove(projectId);
    }
}
