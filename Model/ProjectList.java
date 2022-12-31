package Model;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectList implements JsonList<Project>{
    JsonParser<Project> parser = new JsonParser<>("/Database/project.json", Project.class);
    private ArrayList<Project> projects;
    
    //projectList will contain every project in the database， can be used for adminUser
    public ProjectList(){   
        setList();
    }

    //projectList will contain all the projects of the lecturerUser
    public ProjectList(Lecturer lecturerUser){
        setList();

        for (int i = 0; i < projects.size(); i++){
            Project project = projects.get(i);
            String projectLecturerID = project.getLecturer().getID();
            if (!(projectLecturerID.equals(lecturerUser.getID()))){
                projects.remove(i);
                i--;
            }
        }  
    }

    //projectList will contain all the projects with the same specialization to the user
    public ProjectList(Student studentUser){
        setList();

        for (int i = 0; i < projects.size(); i++){
            Project project = projects.get(i);
            String specialization = studentUser.getSpecialization();
            if ((!(project.getSpecialization().equals(specialization))) || !project.getIsActive()){
                projects.remove(i);
                i--;
            }
        }
    }

    

    public void saveProjectContent(String projectID, String content){
        Project editedProject = getItem(projectID);
        editedProject.setContent(content);

        save();
    }

    public void toggleProject(String projectID){
        Project project = getItem(projectID);
        project.setIsActive(!(project.getIsActive()));
        
        save();
    }

    public ArrayList<Project> getProjects(){
        return projects;
    }

    @Override
    public void save(){
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(){
        try {
            this.projects = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Project item) {
        this.projects.add(item);
         
        save();
    }

    @Override
    public Project getItem(String id) {
        for (int i = 0; i < projects.size(); i++){
            Project tempProject = projects.get(i);
            if (tempProject.getId() == id){
                return tempProject;
            }
        }
        return null;
    }
}
