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

    public ProjectList(Lecturer lecturerUser){
        setList();
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

    //projectList will contain all the projects of the lecturerUser
    public ArrayList<Project> getFilteredProjects(Lecturer lecturerUser){
        ArrayList<Project> filteredProjects = new ArrayList<>(projects);

        for (int i = 0; i < filteredProjects.size(); i++){
            Project project = filteredProjects.get(i);
            String projectLecturerID = project.getLecturerId();
            if (!(projectLecturerID.equals(lecturerUser.getId()))){
                filteredProjects.remove(i);
                i--;
            }
        }  
        return filteredProjects;
    }

    //projectList will contain all the projects with the same specialization to the user
    public ArrayList<Project> getFilteredProjects(Student studentUser){
        ArrayList<Project> filteredProjects = new ArrayList<>(projects);

        for (int i = 0; i < filteredProjects.size(); i++){
            Project project = filteredProjects.get(i);
            String specialization = studentUser.getSpecialization();
            if ((!(project.getSpecialization().equals(specialization))) || !project.getIsActive()){
                filteredProjects.remove(i);
                i--;
            }
        }
        return filteredProjects;
    }

    public void saveProjectName(String projectID, String name){
        Project editedProject = getItem(projectID);
        editedProject.setName(name);

        save();
    }

    public void saveProjectSpecialization(String projectID, String specialization){
        Project editedProject = getItem(projectID);
        editedProject.setSpecialization(specialization);

        save();
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
        setList();

        this.projects.add(item);
         
        save();
    }

    @Override
    public Project getItem(String id) {
        for (int i = 0; i < projects.size(); i++){
            Project tempProject = projects.get(i);
            if (tempProject.getId().equals(id)){
                return tempProject;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        ArrayList<Project> tempList;
        try {
            tempList = parser.deserialize();
            return tempList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
