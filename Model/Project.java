package Model;


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


    //Constructor
    public Project(String id, String name, String specialization, String content, String lecturerId, String lecturerName,
    String studentAssignedId, boolean isAssigned, boolean isActive){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.studentAssignedId = studentAssignedId;
        this.isAssigned = isAssigned;
        this.isActive = isActive;
    }

    public Project(){};
    
    public Project(String id, String name, String specialization, String content, String lecturerId, String lecturerName){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.studentAssignedId = null;
        this.isAssigned = false;
        this.isActive = true;
    }

    // GETTERS AND SETTERS
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSpecialization(){
        return specialization;
    }

    public String getLecturerId(){
        return lecturerId;
    }

    public String getLecturerName(){
        return lecturerName;
    }

    public String getStudentAssignedId(){
        return studentAssignedId;
    }

    public String getStudentAssignedName(){
        return studentAssignedName;
    }

    public String getContent(){
        return content;
    }

    public boolean getIsAssigned(){
        return isAssigned;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public void setLecturer(String lecturerId, String lecturerName){
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public void setStudent(String studentAssignedId, String studentAssignedName){
        this.studentAssignedId = studentAssignedId;
        this.studentAssignedName = studentAssignedName;
    }

    public void setIsAssigned(boolean isAssigned){
        this.isAssigned = isAssigned;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

}