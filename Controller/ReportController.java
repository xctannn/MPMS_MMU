package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// import javax.swing.JTable;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import javax.swing.table.DefaultTableModel;
// import javax.swing.table.TableColumnModel;

import Model.Administrator;
import Model.Lecturer;
import Model.LecturerList;
import Model.Project;
import Model.ProjectData;
import Model.ProjectList;
import Model.Report;
import Model.ReportList;
import Model.Student;
import Model.StudentList;
import Model.User;
import View.ProjectView;
import View.ReportView;


import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.BufferedWriter;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
import java.io.File;


public class ReportController {
    private User user;
    private Project projectModel;
    private ProjectList projectList;
    //private ArrayList<Project> filteredProjectList;
    private ProjectView projectView;
    private Report model;
    private ReportView reportView;
    private ReportList reportList;
    private LecturerList lecturerList = new LecturerList();
    private ArrayList<String> selectionWheelOptions = new ArrayList<String>();
    private ArrayList<String> specializationWheelOptions = new ArrayList<String>();
    


    public ReportController(Administrator user, Report report, ReportView view){
        this.user = user;
        this.model = report;
        this.reportView = view;
        // this.reportList = new ReportList();
        this.projectList = new ProjectList();


        view.addGenerateButtonListener(new GenerateButtonListener());
    }

    class GenerateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){ 
            if(selectionWheelOptions.isEmpty()){
                selectionWheelOptions.add("---Select Here---");
                selectionWheelOptions.add("All Project");
                selectionWheelOptions.add("According to Specialization");
                selectionWheelOptions.add("According to Lecturers");
                selectionWheelOptions.add("Inactive Projects");
                selectionWheelOptions.add("Active Projects");
                selectionWheelOptions.add("Projects Assigned to Students");
                selectionWheelOptions.add("Projects Unassigned to Students");
                selectionWheelOptions.add("Projects With Comments");
            }
            try{

                String selectedOptions = reportView.getOptions(selectionWheelOptions);
                if (selectedOptions.equals(selectionWheelOptions.get(1))){
                    //generate all project textfile
                    ArrayList<Project> allProjects = projectList.getProjects();
                    if(allProjects.size() == 0) throw new IllegalArgumentException();
                    String fileName = "Report\\AllProjectsReport.txt";
                    writeToFile(fileName, allProjects);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(2))){
                    //call function to open another joptionpanel and ask for the speciliazation
                    //generate a textfile according to specialization
                    specializationWheelOptions.add("---Select Here---");
                    specializationWheelOptions.add("Software Engineering");
                    specializationWheelOptions.add("Game Development");
                    specializationWheelOptions.add("Data Science");
                    specializationWheelOptions.add("Cybersecurity");
                    specializationWheelOptions.add("Artficial Intelligence");

                    String selectedSpecOption = reportView.getSpecializationOptions(specializationWheelOptions);
                    actionForSelectedSpecOption(selectedSpecOption);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(3))){
                    //call function to open another joptionpanel and ask for the lecturer list
                    //generate a textfile
                    ArrayList<String> lecturerWheelOptions = new ArrayList<String>();
                    ArrayList<Lecturer> lecturers = lecturerList.getLecturers();
                    for(int i=0; i< lecturers.size(); i++){
                        String lecturerName = lecturers.get(i).getUsername();
                        lecturerWheelOptions.add(lecturerName);
                    }
                    String selectedLecOption = reportView.getSpecializationOptions(lecturerWheelOptions);
                    actionForSelectedLecOption(selectedLecOption);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(4))){
                    //generate inactive projects textfile
                    ArrayList<Project> Projects = projectList.getProjects();
                    ArrayList<Project> notActiveProjects = new ArrayList<Project>();
                    for(int i=0; i< Projects.size(); i++){
                        Project project = Projects.get(i);
                        if(project.getIsActive() == false){
                            notActiveProjects.add(project);
                        }
                    }
                    if(notActiveProjects.size() == 0) throw new IllegalArgumentException();
                    String fileName = "Report\\NotActiveProjectsReport.txt";
                    writeToFile(fileName, notActiveProjects);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(5))){
                    //generate active projects textfile
                    ArrayList<Project> Projects = projectList.getProjects();
                    ArrayList<Project> activeProjects = new ArrayList<Project>();
                    for(int i=0; i< Projects.size(); i++){
                        Project project = Projects.get(i);
                        if(project.getIsActive() == true){
                            activeProjects.add(project);
                        }
                    }
                    if(activeProjects.size() == 0) throw new IllegalArgumentException();
                    String fileName = "Report\\ActiveProjectsReport.txt";
                    writeToFile(fileName, activeProjects);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(6))){
                    //generate projects assigned to students textfile
                    ArrayList<Project> Projects = projectList.getProjects();
                    ArrayList<Project> assignedProjects = new ArrayList<Project>();
                    for(int i=0; i< Projects.size(); i++){
                        Project project = Projects.get(i);
                        if(project.getIsAssigned() == true){
                            assignedProjects.add(project);
                        }
                    }
                    if(assignedProjects.size() == 0) throw new IllegalArgumentException();
                    String fileName = "Report\\AssignedProjectsReport.txt";
                    writeToFile(fileName, assignedProjects);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(7))){
                    //generate projects unassigned to students textfile
                    ArrayList<Project> Projects = projectList.getProjects();
                    ArrayList<Project> unAssignedProjects = new ArrayList<Project>();
                    for(int i=0; i< Projects.size(); i++){
                        Project project = Projects.get(i);
                        if(project.getIsAssigned() == false){
                            unAssignedProjects.add(project);
                        }
                    }
                    if(unAssignedProjects.size() == 0) throw new IllegalArgumentException();
                    String fileName = "Report\\UnassignedProjectsReport.txt";
                    writeToFile(fileName, unAssignedProjects);
                
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(8))){
                    //generate prpkects with comments
                    // pending yet
                    // ArrayList<Project> Projects = projectList.getProjects();
                    // ArrayList<CommentModel> Comments = commentList.getComments();                    
                    // ArrayList<Project> commentedProjects = new ArrayList<Project>();
                    // for(int i=0; i< Projects.size(); i++){
                    //     CommentModel project = Comments.get(i);
                    //     if(project.() == false){
                    //         commentedProjects.add(project);
                    //     }
                    // }
                    // String fileName = "Report\\CommentsProjectsReport.txt";
                    // writeToFile(fileName, commentedProjects);
                }
                

            }catch(IllegalArgumentException exception){
                ReportView.displayErrorMessage("No projects found, report will not be generated");
            }
        }
        
    }
    
    public void actionForSelectedSpecOption(String specialization){
        ArrayList<Project> filteredSpecialization = projectList.getFilteredSpecialization(specialization);
        if(filteredSpecialization.size() == 0) throw new IllegalArgumentException();
        String fileName = "Report\\SpecializationReport.txt";
        writeToFile(fileName, filteredSpecialization);
    }

    public void actionForSelectedLecOption(String lecturerName){
       ArrayList<Project> filteredLecturer = projectList.getFilteredLecturer(lecturerName);
       if(filteredLecturer.size() == 0) throw new IllegalArgumentException();
       String fileName = "Report\\LecturerReport.txt";
       writeToFile(fileName, filteredLecturer);
    }

    public void writeToFile(String fileName, ArrayList<Project> projectData){
        try{
            
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //pending to be done
            //formattedString = 
            bufferedWriter.write("Project Name\t\t\t\t\tSpecialization        \tLecturer   \tStatus   \tAssigned Student");
            for(int i = 0 ; i < projectData.size(); i++){
            Project project = projectData.get(i);
            String writeString = String.format("%s %s %s %s %s", project.getName(), project.getSpecialization(), project.getLecturerName(), project.getIsActive(), project.getStudentAssignedId());
            bufferedWriter.write("\n" + writeString);
            }
            bufferedWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}


//if statement here
//write file into txt to generate report
//request generate report here
