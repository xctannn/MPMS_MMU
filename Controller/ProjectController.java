package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.xml.stream.events.Comment;

import Model.CommentList;
import Model.Lecturer;
import Model.Project;
import Model.ProjectList;
import Model.Student;
import Model.User;
import Model.CommentModel;
import View.CommentView;
import View.ProjectView;

public class ProjectController {
    private User user;
    private Project projectModel;
    private ProjectList projectList;
    private ProjectView projectView;
    private CommentModel commentModel;
    private CommentView commentView;
    private CommentList commentList;

    public ProjectController(Project model, ProjectView view){
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList();
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new tableSelectionListener());
        populateTable();
    }
     //Comment Panel
    public ProjectController(CommentModel model, CommentView view){
        this.commentModel = model;
        this.commentView = view;
        this.commentList = new CommentList();
        populateCommentTable();
    }

    public ProjectController(Lecturer user, Project model, ProjectView view){
        this.user = user;
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new tableSelectionListener());
        populateTable();
    }

    public ProjectController(Student user, Project model, ProjectView view){
        this.user = user;
        this.projectModel = model;
        this.projectView = view;
        this.projectList = new ProjectList(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new tableSelectionListener());
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


    public void populateCommentTable(){
        ArrayList<CommentModel> comments = commentList.getComments();
        DefaultTableModel commentTableModel = new DefaultTableModel(commentView.getColumnNames(),0);

        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals("P0001")){
                CommentModel comment = comments.get(i);
                String commentID = comment.getCommentID();
                String userID = comment.getUserID();
                String username = comment.getUsername();
                String commentString = comment.getCommentString();
                //"ID", "User","Comment",
                Object[] row = {commentID, userID,username, commentString};
                commentTableModel.addRow(row);
                
            }

        }
        JTable viewTable = commentView.getCommentTable();
        viewTable.setModel(commentTableModel);

        TableColumnModel columnModel = viewTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(250);   
    }

    class AssignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = projectView.getStudentID();
            System.out.println(id);
        }
    }

    class tableSelectionListener implements ListSelectionListener{

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
