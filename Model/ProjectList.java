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

    public void saveProjectName(String projectId, String name){
        Project editedProject = getItem(projectId);
        editedProject.setName(name);

        save();
    }

    public void saveProjectSpecialization(String projectId, String specialization){
        Project editedProject = getItem(projectId);
        editedProject.setSpecialization(specialization);

        save();
    }

    public void saveProjectContent(String projectId, String content){
        Project editedProject = getItem(projectId);
        editedProject.setContent(content);

        save();
    }

    public void saveProjectStudentAssigned(String projectId, String studentId, String studentName){
        Project assignedProject = getItem(projectId);
        assignedProject.setStudent(studentId, studentName);
        assignedProject.setIsAssigned(true);

        save();
    }

    public void saveProjectUnassigned(String projectId){
        Project assignedProject = getItem(projectId);
        assignedProject.setStudent(null, null);
        assignedProject.setIsAssigned(false);

        save();
    }

    public void toggleProject(String projectId){
        Project project = getItem(projectId);
        project.setIsActive(!(project.getIsActive()));
        
        save();
    }

    public void saveProjectDeletion(String projectId){
        Project project = getItem(projectId);
        projects.remove(project);

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
