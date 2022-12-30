package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.View;

import Model.Lecturer;
import Model.Project;
import Model.ProjectList;
import Model.Student;
import Model.User;
import View.ProjectView;

public class ProjectController {
    private User user;
    private Project projectModel;
    private ProjectList projectList;
    private ProjectView projectView;

    public ProjectController(Project model, ProjectView view){
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList();
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        populateTable();
    }

    public ProjectController(Lecturer user, Project model, ProjectView view){
        this.user = user;
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.setAssignButtonVisible();
        projectView.addTableSelectionListener(new TableSelectionListener());
        populateTable();
    }

    public ProjectController(Student user, Project model, ProjectView view){
        this.user = user;
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        populateTable();
    }


    public void populateModelView(){
        String projectName = projectModel.getName();
        String projectLecturer = projectModel.getLecturer().getUsername();
        String projectSpecialization = projectModel.getSpecialization();
        String projectContent = projectModel.getContent();
        String projectStudent = "";
        if (projectModel.getIsAssigned()){
            projectStudent = projectModel.getStudentAssigned().getUsername();
        }

        projectView.setProjectNameLabel(projectName);
        projectView.setProjectLecturerLabel(projectLecturer);
        projectView.setProjectSpecializationLabel(projectSpecialization);
        projectView.setProjectContentArea(projectContent);
        projectView.setProjectStudentLabel(projectStudent);
        
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

    class AssignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = projectView.getStudentID();
            System.out.println(id);
        }
    }

    class EditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectView.enableContentEditMode();
        }
    }

    class SaveEditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String newContent = projectView.getProjectContent();
            String projectID = projectModel.getId();
            projectList.saveProjectContent(projectID, newContent);
            projectView.disableContentEditMode();
        }
    }

    class TableSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            JTable table = projectView.getProjectTable();
            int selectedRow = table.getSelectedRow();
            String selectedRowID = (String) table.getValueAt(selectedRow, 0);
            projectModel = projectList.getItem(selectedRowID);
            populateModelView();
        }
    }
}
