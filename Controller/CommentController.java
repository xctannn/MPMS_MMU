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

import Model.Administrator;
import Model.CommentList;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import Model.ProjectList;
import Model.Student;
import Model.User;
import View.CommentView;
import View.ProjectView;

public class CommentController {
    private User user;
    private Project projectModel;
    private ProjectList projectList;
    private ProjectView projectView;
    private CommentModel model;
    private CommentView commentView;
    private CommentList commentList;

    //Comment Panel

     public CommentController(CommentModel commentModel, CommentView view,Administrator user ,Project currentProject){
        this.model = commentModel;
        this.commentView = view;
        this.commentList = new CommentList();
        this.projectModel = currentProject;
        this.user = user;
        view.getSubmitButton().addActionListener(new commentSubmitListener());
        
        
        populateCommentTable(currentProject);
    }
    public CommentController(CommentModel commentModel, CommentView view,Lecturer user ,Project currentProject){
        this.model = commentModel;
        this.commentView = view;
        this.commentList = new CommentList();
        this.projectModel = currentProject;
        this.user = user;
        view.getSubmitButton().addActionListener(new commentSubmitListener());
        
        
        populateCommentTable(currentProject);
    }
    public CommentController(CommentModel commentModel, CommentView view,Student user ,Project currentProject){
        this.model = commentModel;
        this.commentView = view;
        this.commentList = new CommentList();
        this.projectModel = currentProject;
        this.user = user;
        view.getSubmitButton().addActionListener(new commentSubmitListener());
        
        
        populateCommentTable(currentProject);
    }
    

    public void populateCommentTable(Project currentProject){
        ArrayList<CommentModel> comments = commentList.getComments();
        DefaultTableModel commentTableModel = new DefaultTableModel(commentView.getColumnNames(),0);
        String currentProjectId = currentProject.getId();
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals(currentProjectId)){
                CommentModel comment = comments.get(i);
                // String commentID = comment.getCommentID();
                String userID = comment.getUserID();
                String username = comment.getUsername();
                String commentString = comment.getCommentString();
                // "UserID", "Username","Comment"
                Object[] row = { userID,username, commentString};
                
                commentTableModel.addRow(row);
            }

        }
        JTable viewTable = commentView.getCommentTable();
        viewTable.setModel(commentTableModel);

        TableColumnModel columnModel = viewTable.getColumnModel();
        //columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);   
        columnModel.getColumn(2).setPreferredWidth(250);  
    }
    public void commentBlock(Project currentProject){
        ArrayList<CommentModel> comments = commentList.getComments();
        DefaultTableModel commentTableModel = new DefaultTableModel(commentView.getColumnNames(),0);
        String currentProjectId = currentProject.getId();
        
        
    }
    class commentSubmitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == commentView.getSubmitButton()) {
                // Get comment from field and add to model
                String newCommentId = "C" + commentList.generateCommentIdNum();
                Project newProject = projectModel;
                User commentor = user;
                String newCommentString = commentView.getCommentField().getText();

                CommentModel newComment = new CommentModel(newCommentId, newProject,commentor, newCommentString);
                commentList.addItem(newComment);

                // Clear comment field and add comment to area
                commentView.getCommentField().setText("");
                commentView.getCommentArea().append(newComment + "\n");

                commentList.save();

            }
        }
    }
}
