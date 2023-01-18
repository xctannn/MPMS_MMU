package Model;

/*
 * Purpose: Project Model, contains the atrributes of a Project in the System
 */
public class Project{
    private String id;
    private String name;
    private String specialization;
    private String content;
    private String lecturerId;
    private String lecturerName;
    private String studentAssignedId;
    private String studentAssignedName;
    private boolean isAssigned = false;
    private boolean isActive = false;

    // CONSTRUCTORS
    /*
     * Tan Xiao Chin
     * No-Arg Constructor
     * Purpose: For JSON serializer to serialize and deserialize Project object into and from project.json
     */
    public Project(){};

    // CONSTRUCTORS
    /*
     * Tan Xiao Chin
     * Constructor
     * Purpose: To construct a new Project to be added into the system database. 
     * By default, the Project is unassigned to any student, and it is deactivated
     */
    public Project(String id, String name, String specialization, String content, String lecturerId, String lecturerName){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.studentAssignedId = null;
        this.studentAssignedName = null;
        this.isAssigned = false;
        this.isActive = true;
    }

    // GETTERS AND SETTERS
    
    // Tan Xiao Chin    
    public String getId(){
        return id;
    }

    // Tan Xiao Chin    
    public String getName(){
        return name;
    }

    // Tan Xiao Chin    
    public String getSpecialization(){
        return specialization;
    }

    // Tan Xiao Chin    
    public String getLecturerId(){
        return lecturerId;
    }

    // Tan Xiao Chin    
    public String getLecturerName(){
        return lecturerName;
    }

    // Tan Xiao Chin    
    public String getStudentAssignedId(){
        return studentAssignedId;
    }

    // Tan Xiao Chin    
    public String getStudentAssignedName(){
        return studentAssignedName;
    }

    // Tan Xiao Chin    
    public String getContent(){
        return content;
    }

    // Tan Xiao Chin    
    public boolean getIsAssigned(){
        return isAssigned;
    }

    // Tan Xiao Chin    
    public boolean getIsActive(){
        return isActive;
    }

    // Tan Xiao Chin    
    public void setId(String id){
        this.id = id;
    }

    // Tan Xiao Chin    
    public void setName(String name){
        this.name = name;
    }
    
    // Tan Xiao Chin    
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }
      
    // Tan Xiao Chin    
    public void setContent(String content){
        this.content = content;
    }
    
    // Tan Xiao Chin    
    public void setLecturer(String lecturerId, String lecturerName){
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    // Tan Xiao Chin    
    public void setStudent(String studentAssignedId, String studentAssignedName){
        this.studentAssignedId = studentAssignedId;
        this.studentAssignedName = studentAssignedName;
    }

    // Tan Xiao Chin    
    public void setIsAssigned(boolean isAssigned){
        this.isAssigned = isAssigned;
    }

    // Tan Xiao Chin    
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
}