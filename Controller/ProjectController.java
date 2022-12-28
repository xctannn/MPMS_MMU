package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Model.Project;
import Model.ProjectList;
import View.ProjectView;

public class ProjectController {
    private Project projectModel;
    private ProjectList projectList;
    private ProjectView projectView;

    public ProjectController(Project model, ProjectView view){
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList();
        projectView.addAssignButtonListener(new AssignButtonListener());
        populateTable();
    }
    public void populateModelView(){
        projectView.setProjectNameLabel(projectModel.getName());
        projectView.setProjectLecturerLabel(projectModel.getLecturer().getUsername());
        projectView.setProjectSpecializationLabel(projectModel.getSpecialization());
        projectView.setProjectContentArea(projectModel.getContent());
        projectView.setProjectStudentLabel(projectModel.getStudentAssigned().getUsername());
        
    }

    // public void populateTable(){
    //     // TableColumnModel columnModel = new TableColumnModel();
    // }

    public void populateTable(){
        ArrayList<Project> projects = projectList.getProjects();
        DefaultTableModel projectTableModel = new DefaultTableModel(projectView.getColumnNames(), 0);
        
        for (int i = 0; i < projects.size(); i++){
            Project project = projects.get(i);
            String projectID = project.getId();
            String projectName = project.getName();
            String projectLecturerName = project.getLecturer().getUsername();
            Object[] row = {projectID, projectName, projectLecturerName};

            projectTableModel.addRow(row);
        }
        JTable viewTable = projectView.getProjectTable();
        viewTable.setModel(projectTableModel);

        TableColumnModel columnModel = viewTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);

        // projectView.getTableView().add(viewTable);
    }

    class ReturnButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Return to main page
        }
    }

    class AssignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = projectView.getStudentID();
            System.out.println(id);
        }
    }

    
    
}
