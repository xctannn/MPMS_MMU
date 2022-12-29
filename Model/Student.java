package Model;

import java.util.ArrayList;

public class Student extends User{
    private String specialization;
    private String assignedProjectID;
    private ArrayList<Project> favourites;

    // no-arg constructor for jsonParser
    private Student(){}
    
    public Student(String id, String username, String password, String specialization, String assignedProjectID){
        super(id, username, password);
        this.specialization = specialization;
        this.assignedProjectID = assignedProjectID;
        this.favourites = new ArrayList<>();
    }
    
    public String getSpecialization(){
        return specialization;
    }

    public String getAssignedProjectID(){
        return assignedProjectID;
    }
    
    public ArrayList<Project> getFavourites(){
        return favourites;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public void setAssignedProjectID(String AssignedProjectID){
        this.assignedProjectID = assignedProjectID;
    }
}


