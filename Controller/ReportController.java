package Controller;

import java.util.ArrayList;

import Model.Administrator;
import Model.CommentList;
import Model.CommentModel;
import Model.Lecturer;
import Model.LecturerList;
import Model.Project;
import Model.ProjectList;
import View.ProjectView;


import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/*
* Iven Low
* Purpose: Controller for obtaining information of filtered projects list to generate text file report
*/

public class ReportController {
    //private Administrator user;
    private ProjectList projectList;
    private ProjectView projectView;
    private CommentList commentList = new CommentList();;
    private LecturerList lecturerList = new LecturerList();
    //private ArrayList<String> selectionWheelOptions = new ArrayList<String>();
    private ArrayList<String> specializationWheelOptions = new ArrayList<String>();
    
/*
* Iven Low
* Purpose: Controller for obtaining information of filtered projects list to generate text file report
*/
    public ReportController(Administrator user, ProjectView view){
        //this.user = user;
        this.projectView = new ProjectView(user);
        this.projectList = new ProjectList();
    }
    

    public void allProjectList(){
        ArrayList<Project> allProjects = projectList.getProjects();
        if(allProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\AllProjectsReport.txt";
        writeToFile(fileName, allProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void specializationProjectList(){
        //call function to open another joptionpanel and ask for the speciliazation
        //generate a textfile according to specialization
        if(specializationWheelOptions.isEmpty()){
            projectView.getSpecializationList(specializationWheelOptions);
        }
        String selectedSpecOption = projectView.getSpecializationOptions(specializationWheelOptions);
        actionForSelectedSpecOption(selectedSpecOption);
    }

    public void lecturerProjectList(){
        //call function to open another joptionpanel and ask for the lecturer list
        //generate a textfile
        ArrayList<String> lecturerWheelOptions = new ArrayList<String>();
        ArrayList<Lecturer> lecturers = lecturerList.getLecturers();
        for(int i=0; i< lecturers.size(); i++){
            String lecturerName = lecturers.get(i).getUsername();
            lecturerWheelOptions.add(lecturerName);
        }
        String selectedLecOption = projectView.getLecturerOptions(lecturerWheelOptions);
        actionForSelectedLecOption(selectedLecOption);
    }

    public void inactiveProjectList(){
        //generate inactive projects textfile
        ArrayList<Project> projects = projectList.getProjects();
        ArrayList<Project> notActiveProjects = new ArrayList<Project>();
        for(int i=0; i< projects.size(); i++){
            Project project = projects.get(i);
            if(project.getIsActive() == false){
                notActiveProjects.add(project);
            }
        }
        if(notActiveProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\InactiveProjectsReport.txt";
        writeToFile(fileName, notActiveProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void activeProjectList(){
        //generate active projects textfile
        ArrayList<Project> projects = projectList.getProjects();
        ArrayList<Project> activeProjects = new ArrayList<Project>();
        for(int i=0; i< projects.size(); i++){
            Project project = projects.get(i);
            if(project.getIsActive() == true){
                activeProjects.add(project);
            }
        }
        if(activeProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\ActiveProjectsReport.txt";
        writeToFile(fileName, activeProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void assignedProjectList(){
        //generate projects assigned to students textfile
        ArrayList<Project> projects = projectList.getProjects();
        ArrayList<Project> assignedProjects = new ArrayList<Project>();
        for(int i=0; i< projects.size(); i++){
            Project project = projects.get(i);
            if(project.getIsAssigned() == true){
                assignedProjects.add(project);
            }
        }
        if(assignedProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\AssignedProjectsReport.txt";
        writeToFile(fileName, assignedProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void unassignedProjectList(){
        //generate projects unassigned to students textfile
        ArrayList<Project> projects = projectList.getProjects();
        ArrayList<Project> unAssignedProjects = new ArrayList<Project>();
        for(int i=0; i< projects.size(); i++){
            Project project = projects.get(i);
            if(project.getIsAssigned() == false){
                unAssignedProjects.add(project);
            }
        }
        if(unAssignedProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\UnassignedProjectsReport.txt";
        writeToFile(fileName, unAssignedProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void commentProjectList(){
        //generate prpkects with comments
        ArrayList<Project> projects = projectList.getProjects();
        ArrayList<CommentModel> comments = commentList.getComments();      
        ArrayList<Project> listOfProjectsWithComments = new ArrayList<Project>();                                  
        for(int i=0; i< projects.size(); i++){
            Project project = projects.get(i);
            for(int j=0; j < comments.size(); j++){
                CommentModel comment = comments.get(j);
                if(comment.getProjectID().equals(project.getId())){
                    listOfProjectsWithComments.add(project);
                }
            }
        }
        String fileName = "Report\\CommentsProjectsReport.txt";
        writeToFile(fileName, listOfProjectsWithComments);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void actionForSelectedSpecOption(String specialization){
        ArrayList<Project> filteredSpecialization = projectList.getFilteredSpecialization(specialization);
        if(filteredSpecialization.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\SpecializationReport.txt";
        writeToFile(fileName, filteredSpecialization);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void actionForSelectedLecOption(String lecturerName){
       ArrayList<Project> filteredLecturer = projectList.getFilteredLecturer(lecturerName);
       if(filteredLecturer.size() == 0) throw new IllegalArgumentException();
       String fileName = "Report\\LecturerReport.txt";
       writeToFile(fileName, filteredLecturer);
       ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    public void writeToFile(String fileName, ArrayList<Project> projectData){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String writeTitle = String.format("%-50s %20s %20s %17s %23s", "Project Name", "Specialization", "Lecturer", "Active", "Assigned Student");
            bufferedWriter.write(writeTitle);
            for(int i = 0 ; i < projectData.size(); i++){
            Project project = projectData.get(i);
            String writeContent = String.format("%-50s %20s %18s %18s %18s", project.getName(), project.getSpecialization(), project.getLecturerId(), project.getIsActive(), project.getStudentAssignedId());
            bufferedWriter.write("\n" + writeContent);
            }
            bufferedWriter.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}