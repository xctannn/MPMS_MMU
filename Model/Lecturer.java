package Model;

import java.util.ArrayList;

public class Lecturer extends User{
    private ArrayList<Project> projectList;

    // No-arg constructor for Json Parser
    private Lecturer(){};
    
    public Lecturer(String id, String username, String password, ArrayList<Project> projectList){
        super(id, username, password);
        this.projectList = projectList;
    }

    public ArrayList<Project> getProjectList(){
        return projectList;
    }

    public void setProjectList(ArrayList<Project> projectList){
        this.projectList = projectList;
    }
}
