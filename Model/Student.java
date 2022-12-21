package Model;

public class Student extends User{
    private String specialization;
    private Project projectAssigned;

    public Student(String id, String username, String password, String specialization, Project projectAssigned){
        super(id, username, password);
        this.specialization = specialization;
        this.projectAssigned =projectAssigned;
    }
    
    public String getSpecialization(){
        return specialization;
    }

    public Project getProjectAssigned(){
        return projectAssigned;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public void setProjectAssigned(Project projectAssigned){
        this.projectAssigned = projectAssigned;
    }
}


