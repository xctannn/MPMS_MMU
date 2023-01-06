package Model;

import java.util.ArrayList;

public class Student extends User{
    private String specialization;
    private String assignedProjectID;
    private ArrayList<String> favourites;

    // no-arg constructor for jsonParser
    public Student(){}
    
    public Student(String id, String username, String password, String specialization, String assignedProjectID){
        super(id, username, password);
        this.specialization = specialization;
        this.assignedProjectID = assignedProjectID;
        this.favourites = new ArrayList<>();
    }

    public String generateCode(int listSize){
        return String.format("%04d", listSize + 1);
    }

    public String getSpecialization(){
        return specialization;
    }

    public String getAssignedProjectID(){
        return assignedProjectID;
    }
    
    public ArrayList<String> getFavourites(){
        return favourites;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public void setAssignedProjectID(String assignedProjectID){
        this.assignedProjectID = assignedProjectID;
    }
}


