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


import java.io.FileReader;
import java.io.BufferedReader;
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


    public ReportController(Administrator user, Report report, ReportView view){
        this.user = user;
        this.model = report;
        this.reportView = view;
        this.reportList = new ReportList();


        view.addGenerateButtonListener(new GenerateButtonListener());
    //duno need add button or what or not yet
    // populateTable();
    }

    class GenerateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){ 
            ArrayList<String> selectionWheelOptions = new ArrayList<String>();

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
                if (selectedOptions.equals("All Project")){
                    //generate a textfile
                    System.out.println("Generate text file");
                }
                else if (selectedOptions.equals("According to Specialization")){
                    //call function to open another joptionpanel and ask for the speciliazation
                    //generate a textfile according to specialization
                    //projectView.addFilterProjectsButtonListener(new GenerateButtonListener());
                // public void actionPerformed(ActionEvent e){
                    //GenerateButtonListener1();
                    ArrayList<String> specializationWheelOptions = new ArrayList<String>();
                    specializationWheelOptions.add("---Select Here---");
                    specializationWheelOptions.add("All Project");
                    specializationWheelOptions.add("2");
                    specializationWheelOptions.add("3");
                    specializationWheelOptions.add("4");

                    String selectedSpecOption = reportView.getSpecializationOptions(specializationWheelOptions);
                    actionForSelectedSpecOption(selectedSpecOption);
                }
                else if (selectedOptions.equals("According to Lecturers")){
                    //call function to open another joptionpanel and ask for the lecturer list
                    //generate a textfile
                }
                else if (selectedOptions.equals("Inactive Projects")){
                    //generate inactive projects textfile
                }
                else if (selectedOptions.equals("Active Projects")){
                    //generate active projects textfile
                }
                else if (selectedOptions.equals("Projects Assigned to Students")){
                    //generate projects assigned to students textfile
                }
                else if (selectedOptions.equals("Projects Unassigned to Students")){
                    //generate projects unassigned to students textfile
                }
                else if (selectedOptions.equals("Projects With Comment   ")){
                    //generate prpkects with comments
                }
                
            }catch(IllegalArgumentException exception){
                ReportView.displayErrorMessage("There are no projects available to generate");
            }
        }
        
    }
    
    public void actionForSelectedSpecOption(String specialization){
        if(specialization.equals("All Project")){
            //generate projects textfile for all projects'
            System.out.println("Generating for all specializations");
        }
    }


    // public void GenerateButtonListener1() {
    //         class GenerateButtonListener1 implements ActionListener{

    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             // TODO Auto-generated method stub
    //             ArrayList<String> specializationWheelOptions = new ArrayList<String>();
    //             specializationWheelOptions.add("---Select Here---");
    //             specializationWheelOptions.add("All Project");
    //             specializationWheelOptions.add("2");
    //             specializationWheelOptions.add("3");
    //             specializationWheelOptions.add("4");
    //             try {
    //                 String selectedOptions = reportView.getSpecializationOptions(specializationWheelOptions);
    //             } catch (IllegalArgumentException exception) {
    //                 // TODO: handle exception
    //             }
    //         }

    //     }
    // }
}   

// private void populateTable() { inin pending
//     //
// }


//if statement here
//write file into txt to generate report
//request generate report here
