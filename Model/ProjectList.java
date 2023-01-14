package Model;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Purpose: Handles and manipulates the "projects" field in Project.json, an ArrayList of the projects stored in the system
 */
public class ProjectList implements JsonList<Project>{
    JsonParser<ProjectData> parser = new JsonParser<>("/Database/project.json", ProjectData.class);
    private ProjectData projectData;
    private ArrayList<Project> projects;
    private int projectCount;
    
    /* 
     * Tan Xiao Chin
     * No-Arg Constructor
     */
    public ProjectList(){   
        setList();
        this.projectCount = projectData.getProjectCount();
    }

    /* 
     * Tan Xiao Chin
     * Purpose: filter out projects that aren't created by the lecturerUser,
     *          and returns the remaining projects
     */
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

    /* 
     * Tan Xiao Chin
     * Purpose: filter out projects that aren't activated, and dont have the same specialization as the studentUser,
     *          and returns the remaining projects
     */
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

    /* 
     * Tan Xiao Chin
     * Purpose: generate the number code for a new project
     */
    public String generateCode(){
        return String.format("%04d", projectCount + 1);
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectCountIncrement(){
        projectCount++;
        projectData.setProjectCount(projectCount);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectName(String projectId, String name){
        Project editedProject = getItem(projectId);
        editedProject.setName(name);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectSpecialization(String projectId, String specialization){
        Project editedProject = getItem(projectId);
        editedProject.setSpecialization(specialization);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectContent(String projectId, String content){
        Project editedProject = getItem(projectId);
        editedProject.setContent(content);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectStudentAssigned(String projectId, String studentId, String studentName){
        Project assignedProject = getItem(projectId);
        assignedProject.setStudent(studentId, studentName);
        assignedProject.setIsAssigned(true);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectUnassigned(String projectId){
        Project assignedProject = getItem(projectId);
        assignedProject.setStudent(null, null);
        assignedProject.setIsAssigned(false);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void toggleProject(String projectId){
        Project project = getItem(projectId);
        project.setIsActive(!(project.getIsActive()));
        
        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public void saveProjectDeletion(String projectId){
        Project project = getItem(projectId);
        projects.remove(project);

        save();
    }

    /* 
     * Tan Xiao Chin
     */
    public ArrayList<Project> getProjects(){
        return projectData.getProjects();
    }

    /* 
     * Tan Xiao Chin
     */
    public int getProjectCount(){
        return projectData.getProjectCount();
    } 

    /* 
     * Tan Xiao Chin
     * Purpose: filter out projects that aren't created by the lecturer
     */
    @Override
    public void save(){
        try {
            parser.serializeObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Tan Xiao Chin
     */
    @Override
    public void setList(){
        try {
            this.projectData = parser.deserializeObject();
            this.projects = projectData.getProjects();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Tan Xiao Chin
     */
    @Override
    public void addItem(Project item) {
        setList();

        this.projects.add(item);
         
        save();
    }

    /* 
     * Tan Xiao Chin
     */
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

    /* 
     * Tan Xiao Chin
     */
    @Override
    public int getSize(){
        try {
            parser.deserialize();
            return projects.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
