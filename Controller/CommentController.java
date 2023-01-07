package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JPanel;
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
    private CommentModel model;
    private CommentView commentView;
    private CommentList commentList;

    public CommentController(CommentModel commentModel, CommentView view,User user ,Project currentProject){
        this.model = commentModel;
        this.commentView = view;
        this.commentList = new CommentList();
        this.projectModel = currentProject;
        this.user = user;
        view.getSubmitButton().addActionListener(new commentSubmitListener());
        
        createCommentPanel(currentProject);

    }
    

    public void createCommentPanel(Project currentProject){
        JPanel commentPanel = commentView.getCommentBlock();
        ArrayList<CommentModel> comments = commentList.getComments();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

        
        String currentProjectId = currentProject.getId();
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals(currentProjectId)){
                JPanel frame = new JPanel();
                frame.setLayout(new GridLayout(2,1));
                frame.setBorder(BorderFactory.createEtchedBorder());
                JPanel inframePanel = new JPanel();
                inframePanel.setLayout(new GridLayout(1,2));
                inframePanel.setBorder(BorderFactory.createEtchedBorder());
                CommentModel comment = comments.get(i);
                // String commentID = comment.getCommentID();
                String userIdentityString = "ID: " + comment.getUserID() + "   " +"Name: " + comment.getUsername();
                JLabel userLabel = new JLabel(userIdentityString);
                JLabel commentString =  new JLabel(comment.getCommentString());

                inframePanel.add(userLabel,BorderLayout.NORTH );

                frame.add(inframePanel);
                frame.add(commentString);
                commentPanel.add(frame);
            }
        }
    }
    class commentSubmitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == commentView.getSubmitButton()) {
                // Get comment from field and add to model
                String newCommentId = "C" + commentList.generateCommentIdNum();
                Project newProject = projectModel;
                User commentor = user;
                String newCommentString = commentView.getCommentArea().getText();

                CommentModel newComment = new CommentModel(newCommentId, newProject,commentor, newCommentString);
                commentList.addItem(newComment);

                // Clear comment field and add comment to area
                commentView.getCommentArea().setText("");
                createCommentPanel(newProject);
                // commentView.getCommentBlock();

                commentList.save();

            }
        }
    }
}
