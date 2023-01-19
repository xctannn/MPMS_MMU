package src.Model;

/*
 * Chua Hui Yi
 * Purpose: Student model that inherits from User class and contains the attributes of a Student
 */
public class Student extends User{
    private String specialization;
    private String assignedProjectID;

    /*
     * Chua Hui Yi
     * No-Arg Constructor
     * Purpose: For JSON serializer to serialize and deserialize Student object into and from student.json
     */
    public Student(){}
    
    /*
     * Chua Hui Yi
     * Purpose: To construct a new Student
     * Use the super keyword to call the constructor of the parent class (User)
     */
    public Student(String id, String username, String password, String specialization, String assignedProjectID){
        super(id, username, password);
        this.specialization = specialization;
        this.assignedProjectID = assignedProjectID;
    }

    // Chua Hui Yi
    public void removeAssignedProject(){
        setAssignedProjectID(null);
    }
    
    // Chua Hui Yi
    public String getSpecialization(){
        return specialization;
    }

    // Chua Hui Yi
    public String getAssignedProjectID(){
        return assignedProjectID;
    }

    // Chua Hui Yi
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    // Chua Hui Yi
    public void setAssignedProjectID(String assignedProjectID){
        this.assignedProjectID = assignedProjectID;
    }
}


