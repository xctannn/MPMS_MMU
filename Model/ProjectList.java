package Model;

import java.io.IOException;
import java.util.ArrayList;


public class ProjectList implements JsonList<Project>{
    JsonParser<ProjectData> parser = new JsonParser<>("/Database/project.json", ProjectData.class);
    private ProjectData projectData;
    private ArrayList<Project> projects;
    private int projectCount;
    private String specialization;
    private String id;

    //projectList will contain every project in the database， can be used for adminUser
    public ProjectList(){   
        setList();
        this.projectCount = projectData.getProjectCount();
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

    //Contains filtered specialization to generate the report
    public ArrayList<Project> getFilteredSpecialization(String specialization){
        ArrayList<Project> filteredProjects = new ArrayList<>(projects);
        
        for (int i = 0; i < filteredProjects.size(); i++){
            Project project = filteredProjects.get(i);
            String tempSpecialization = project.getSpecialization();
            if (!(tempSpecialization.equals(specialization))){
                filteredProjects.remove(i);
                i--;
            }
        }
        return filteredProjects;
    }

        //Contains filtered lecturer to generate the report
        public ArrayList<Project> getFilteredLecturer(String lecturerName){
            ArrayList<Project> filteredLecturers = new ArrayList<>(projects);
            
            for (int i = 0; i < filteredLecturers.size(); i++){
                Project lecturer = filteredLecturers.get(i);
                String tempLecturers = lecturer.getLecturerName();
                if (!(tempLecturers.equals(lecturerName))){
                    filteredLecturers.remove(i);
                    i--;
                }
            }
            return filteredLecturers;
        }



    public String generateCode(){
        return String.format("%04d", projectCount + 1);
    }

    public void saveProjectCountIncrement(){
        projectCount++;
        projectData.setProjectCount(projectCount);

        save();
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
        return projectData.getProjects();
    }

    public int getProjectCount(){
        return projectData.getProjectCount();
    } 

    @Override
    public void save(){
        try {
            parser.serializeObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(){
        try {
            this.projectData = parser.deserializeObject();
            this.projects = projectData.getProjects();
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
            parser.deserialize();
            return projects.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 

}
