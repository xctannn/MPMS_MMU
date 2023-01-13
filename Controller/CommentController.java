package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JPanel;

import Model.CommentList;
import Model.CommentModel;
import Model.Project;
import Model.User;
import View.CommentView;

public class CommentController {
    private User user;
    private Project projectModel;
    private CommentModel model;
    private CommentView commentView;
    private CommentList commentList;




    public CommentController(CommentModel commentModel, User user ,Project currentProject){
        this.model = commentModel;
        this.commentList = new CommentList();
        this.commentView = new CommentView();
        this.projectModel = currentProject;
        this.user = user;
        commentView.getSubmitButton().addActionListener(new commentSubmitListener());
        
        createCommentPanel();

    }
    
    public JPanel getCommentView(){
        return commentView;
    }

    public void updateCommentPanel(){
        commentView.getCommentBlock().removeAll();

        createCommentPanel();
    }

    public void createCommentPanel(){
        final Dimension EACH_ROW_DIMENSION = new Dimension(700,100);
        JPanel commentPanel = commentView.getCommentBlock();
        ArrayList<CommentModel> comments = commentList.getComments();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        
        String currentProjectId = projectModel.getId();
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals(currentProjectId)){
                JPanel frame = new JPanel();
                frame.setLayout(new GridLayout(2,1));
                frame.setBorder(BorderFactory.createEtchedBorder());
                frame.setBackground(Color.getHSBColor(188, 87, 68));
                frame.setMinimumSize(EACH_ROW_DIMENSION);
                frame.setPreferredSize(EACH_ROW_DIMENSION);
                frame.setMaximumSize(EACH_ROW_DIMENSION);

                JPanel inframePanel = new JPanel();
                inframePanel.setMinimumSize(EACH_ROW_DIMENSION);
                inframePanel.setPreferredSize(EACH_ROW_DIMENSION);
                inframePanel.setMaximumSize(EACH_ROW_DIMENSION);
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
                
                updateCommentPanel();

            }
        }
    }
    


}

