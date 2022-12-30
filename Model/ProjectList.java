package Model;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectList implements JsonList<Project>{
    JsonParser<Project> parser = new JsonParser<>("/Database/project.json", Project.class);
    private ArrayList<Project> projects;
    public Object getProjects;
    
    //projectList will contain every project in the database， can be used for adminUser
    public ProjectList(){   
        try {
            this.projects = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //projectList will contain all the projects of the lecturerUser
    public ProjectList(Lecturer lecturerUser){
        try {
            this.projects = parser.deserialize();
            for (int i = 0; i < projects.size(); i++){
                Project project = projects.get(i);
                String projectLecturerID = project.getLecturer().getID();
                if (!(projectLecturerID.equals(lecturerUser.getID()))){
                    projects.remove(i);
                    i--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    //projectList will contain all the projects with the same specialization to the user
    public ProjectList(Student studentUser){
        try {
            this.projects = parser.deserialize();
            for (int i = 0; i < projects.size(); i++){
                Project project = projects.get(i);
                String specialization = studentUser.getSpecialization();
                if ((!(project.getSpecialization().equals(specialization))) || !project.getIsActive()){
                    projects.remove(i);
                    i--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    public ArrayList<Project> getProjects(){
        return projects;
    }

    public void saveProjectContent(String projectID, String content){
        Project editedProject = getItem(projectID);
        editedProject.setContent(content);

        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Project item) {
        this.projects.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
