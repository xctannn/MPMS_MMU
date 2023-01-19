package Controller;

import java.util.ArrayList;

import Model.Administrator;
import Model.Project;
import Model.ProjectList;
import View.ProjectView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;

/*
* Iven Low
* Purpose: Controller for obtaining information of filtered projects list to generate text file report
*/

public class ReportController {
    private ProjectList projectList;
    private ProjectView projectView;
    private ArrayList<String> specializationWheelOptions = new ArrayList<String>();
    
    /*
    * Iven Low
    * Constructor to build view for Administrator users
    */
    public ReportController(Administrator user, ProjectView view){
        this.projectView = new ProjectView(user);
        this.projectList = new ProjectList();
    }
    
    /*
    * Iven Low
    * Purpose: Function to call get all projects entered into the system in projectList and generate text file writeToFile() for report
    */
    public void allProjectList(){
        ArrayList<Project> allProjects = projectList.getAllProjects();
        if(allProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\AllProjectsReport.txt";
        writeToFile(fileName, allProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Function to generate another joptionpane and prompt user to choose a specialization to filter projects by specialization
    */
    public void specializationProjectList(){
        projectList.setList();
        if(specializationWheelOptions.isEmpty()){
            projectView.getSpecializationList(specializationWheelOptions);
        }
        String selectedSpecOption = projectView.getSpecializationOptions(specializationWheelOptions);
        actionForSelectedSpecOption(selectedSpecOption);
    }

    /*
    * Iven Low
    * Purpose: Function to generate another joptionpane and prompt user to choose a lecturer to filter projects by lecturer
    */
    public void lecturerProjectList(){
        ArrayList<String> lecturerWheelOptions = projectList.getLecturerOptions();
        String selectedLecOption = projectView.getLecturerOptions(lecturerWheelOptions);
        actionForSelectedLecOption(selectedLecOption);
    }
    
    /*
    * Iven Low
    * Purpose: Function to call get inactive projects list and generate text file report
    */
    public void inactiveProjectList(){
        ArrayList<Project> inactiveProjects = projectList.getInactiveProjectList();
        if(inactiveProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\InactiveProjectsReport.txt";
        writeToFile(fileName, inactiveProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Function to call get active projects list and generate text file report
    */
    public void activeProjectList(){
        ArrayList<Project> activeProjects = projectList.getActiveProjectList();
        if(activeProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\ActiveProjectsReport.txt";
        writeToFile(fileName, activeProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Function to call get filter assigned student projects list to generate text file report
    */
    public void assignedProjectList(){
        ArrayList<Project> assignedProjects = projectList.getAssignedProjectList();
        if(assignedProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\AssignedProjectsReport.txt";
        writeToFile(fileName, assignedProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Function to call get filter active projects list to generate text file report
    */
    public void unassignedProjectList(){
        ArrayList<Project> unAssignedProjects = projectList.getUnassignedProjectList();
        if(unAssignedProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\UnassignedProjectsReport.txt";
        writeToFile(fileName, unAssignedProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Function to call get filter projects with comments to generate text file report
    */
    public void commentProjectList(){
        ArrayList<Project> commentProjects = projectList.getCommentProjectList();
        if(commentProjects.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\CommentsProjectsReport.txt";
        writeToFile(fileName, commentProjects);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Filter out and get projects by specialization and generate text file report
    */
    public void actionForSelectedSpecOption(String specialization){
        ArrayList<Project> filteredSpecialization = projectList.getFilteredSpecialization(specialization);
        if(filteredSpecialization.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\SpecializationReport.txt";
        writeToFile(fileName, filteredSpecialization);
        ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }
    
    /*
    * Iven Low
    * Purpose: Filter out and get projects by lecturer and call generate text file report
    */
    public void actionForSelectedLecOption(String lecturerName){
       ArrayList<Project> filteredLecturer = projectList.getFilteredLecturer(lecturerName);
       if(filteredLecturer.size() == 0) throw new IllegalArgumentException();
       String fileName = "Report\\LecturerReport.txt";
       writeToFile(fileName, filteredLecturer);
       ProjectView.displaySuccessMessage("Projects Generated Successfully!");
    }

    /*
    * Iven Low
    * Purpose: Get the filename and filtered projects details to generate text file report
    */
    public void writeToFile(String fileName, ArrayList<Project> projectData){
        File directory = new File("Report\\");
        if(!directory.exists()){
            directory.mkdir();
        }
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String writeTitle = String.format("%-50s %25s %20s %15s %23s", "Project Name", "Specialization", "Lecturer", "Active", "Assigned Student");
            bufferedWriter.write(writeTitle);
            for(int i = 0 ; i < projectData.size(); i++){
                Project project = projectData.get(i);
                String writeContent = String.format("%-50s %25s %18s %16s %20s", project.getName(), project.getSpecialization(), project.getLecturerId(), project.getIsActive(), project.getStudentAssignedId());
                bufferedWriter.write("\n" + writeContent);
            }
            bufferedWriter.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}