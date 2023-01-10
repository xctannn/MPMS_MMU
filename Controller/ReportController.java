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

            selectionWheelOptions.add("---Select Here---");
            selectionWheelOptions.add("All Project");
            selectionWheelOptions.add("According to Specialization");
            selectionWheelOptions.add("According to Lecturers");
            selectionWheelOptions.add("Inactive Projects");
            selectionWheelOptions.add("Active Projects");
            selectionWheelOptions.add("Projects Assigned to Students");
            selectionWheelOptions.add("Projects Unassigned to Students");
            selectionWheelOptions.add("Projects With Comments");
            try{

                String selectedOptions = reportView.getOptions(selectionWheelOptions);
                if (selectedOptions.equals(selectionWheelOptions.get(1))){
                    //generate a textfile
                    //System.out.println("Generate text file");
                    ArrayList<Project> allProjects = projectList.getAllProjects();
                    String fileName = "Report\\AllProjectsReport.txt";
                    writeToFile(fileName, allProjects);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(2))){
                    //call function to open another joptionpanel and ask for the speciliazation
                    //generate a textfile according to specialization
                    specializationWheelOptions.add("---Select Here---");
                    specializationWheelOptions.add("Data Science");
                    specializationWheelOptions.add("Cyber Security");
                    specializationWheelOptions.add("Software Engineering");
                    specializationWheelOptions.add("Game Development");

                    String selectedSpecOption = reportView.getSpecializationOptions(specializationWheelOptions);
                    actionForSelectedSpecOption(selectedSpecOption);
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(3))){
                    //call function to open another joptionpanel and ask for the lecturer list
                    //generate a textfile
                    //ArrayList<String> lecturerWheelOptions = new ArrayList<String>();
                    ArrayList<Lecturer> lecturers = lecturerList.getLecturers();
                    for(int i=0; i< lecturers.size(); i++){
                        //lecturerWheelOptions.add(lecturers.get(i));
                    }

                }
                else if (selectedOptions.equals(selectionWheelOptions.get(4))){
                    //generate inactive projects textfile
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(5))){
                    //generate active projects textfile
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(6))){
                    //generate projects assigned to students textfile
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(7))){
                    //generate projects unassigned to students textfile
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(8))){
                    //generate prpkects with comments
                }
                
            }catch(IllegalArgumentException exception){
                ReportView.displayErrorMessage("There are no projects available to generate");
            }
        }
        
    }
    
    public void actionForSelectedSpecOption(String specialization){
        ArrayList<Project> filteredSpecialization = projectList.getFilteredSpecialization(specialization);
        String fileName = "Report\\SpecializationReport.txt";
        writeToFile(fileName, filteredSpecialization);
    }

    public void actionForSelectedLecOption(String specialization){
       // ArrayList<Project> filteredSpecialization = projectList.getFilteredLecturer(specialization);

        if(specialization.equals(specializationWheelOptions.get(1))){
            //generate projects textfile for all projects'
        }
    }

    public void writeToFile(String fileName, ArrayList<Project> projectData){
        try(FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            //if (fileName.equals()
            for(int i = 0 ; i < projectData.size(); i++){
            Project project = projectData.get(i);
            bufferedWriter.write("Project Name\n" + project.getName());
            }
            bufferedWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
// private void populateTable() { inin pending
//     //
// }


//if statement here
//write file into txt to generate report
//request generate report here
