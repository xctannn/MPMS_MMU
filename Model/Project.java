package Model;


public class Project{
    private String id;
    private String name;
    private String specialization;
    private String content;
    private String lecturerId;
    private Student studentAssigned;
    private boolean isAssigned = false;
    private boolean isActive = false;


    //Constructor
    public Project(String id, String name, String specialization, String content, String lecturerId, 
    Student studentAssigned, boolean isAssigned, boolean isActive){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturerId = lecturerId;
        this.studentAssigned = studentAssigned;
        this.isAssigned = isAssigned;
        this.isActive = isActive;
    }

    public Project(){};
    
    public Project(String id, String name, String specialization, String content, String lecturerId){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturerId = lecturerId;
        this.studentAssigned = null;
        this.isAssigned = false;
        this.isActive = true;

    }

    public String generateCode(int listSize){
        return String.format("%04d", listSize + 1);
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

    public Student getStudentAssigned(){
        return studentAssigned;
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
    
    public void setLecturer(String lecturerId){
        this.lecturerId = lecturerId;
    }

    public void setStudent(Student studentAssigned){
        this.studentAssigned = studentAssigned;
    }

    public void setIsAssigned(boolean isAssigned){
        this.isAssigned = isAssigned;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

}