package Model;


public class Project{
    private String id;
    private String name;
    private String specialization;
    private String content;
    private Lecturer lecturer;
    private Student studentAssigned;
    private boolean isAssigned = false;
    private boolean isActive = false;


    //Constructor
    public Project(String id, String name, String specialization, String content, Lecturer lecturer, 
    Student studentAssigned, boolean isAssigned, boolean isActive){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturer = lecturer;
        this.studentAssigned = studentAssigned;
        this.isAssigned = isAssigned;
        this.isActive = isActive;
    }

    public Project(){};
    
    public Project(String id, String name, String specialization, String content, Lecturer lecturer){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.content = content;
        this.lecturer = lecturer;
        this.studentAssigned = null;
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
        this.specialization = specialization;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public void setLecturer(Lecturer lecturer){
        this.lecturer = lecturer;
    }

    // public void setLecturer(String lecturer){
    //     this.lecturer = lecturer;
    // }

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