package Model;

public class Project{
    private String id;
    private String name;
    private String specialization;
    private String content;
    private Lecturer lecturer;
    private Student studentAssigned;
    private boolean isAssigned;
    private boolean isActive;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSpecialization(){
        return specialization;
    }

    public Lecturer getLecturer(){
        return lecturer;
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
        this.specialization = id;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public void setLecturer(Lecturer lecturer){
        this.lecturer = lecturer;
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