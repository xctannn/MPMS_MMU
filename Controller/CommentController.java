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
import Model.User;
import View.CommentView;

/**
 * Kam Kar Hou 
 * Purpose: CommentController handles the method updating and creating commentblocks, revalidating scrollpane from commentView.java
 */
public class CommentController {
    private User user;
    private String currentProjectID;
    private CommentView commentView;
    private CommentList commentList;

    /**
     * Kam Kar Hou
     * Purpose: Creates a new CommentController with the given user and projectID
     * also adds an action listener and also handles the creation of commentBlocks.
     */
    public CommentController( User currentUser ,String currentProjectID){

        this.commentList = new CommentList();
        this.commentView = new CommentView();
        this.currentProjectID = currentProjectID;
        this.user = currentUser;
        commentView.getSubmitButton().addActionListener(new commentSubmitListener());
        
        createCommentPanel();
    }

    /**
     * Kam Kar Hou
     * Purpose: This creates the comment blocks in the scrollable pane in the commentView.java
     * it contains the userID, userName and the commentedString.
     */
    public void createCommentPanel(){
        final Dimension EACH_ROW_DIMENSION = new Dimension(700,100);
        JPanel commentPanel = commentView.getCommentBlock();
        ArrayList<CommentModel> comments = commentList.getComments();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals(currentProjectID)){
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2,1));
                panel.setBorder(BorderFactory.createEtchedBorder());
                panel.setBackground(Color.getHSBColor(188, 87, 68));
                panel.setMinimumSize(EACH_ROW_DIMENSION);
                panel.setPreferredSize(EACH_ROW_DIMENSION);
                panel.setMaximumSize(EACH_ROW_DIMENSION);

                JPanel inPanel = new JPanel();
                inPanel.setMinimumSize(EACH_ROW_DIMENSION);
                inPanel.setPreferredSize(EACH_ROW_DIMENSION);
                inPanel.setMaximumSize(EACH_ROW_DIMENSION);
                inPanel.setLayout(new GridLayout(1,2));
                inPanel.setBorder(BorderFactory.createEtchedBorder());
                CommentModel comment = comments.get(i);
                String userIdentityString = "ID: " + comment.getUserID() + "   " +"Name: " + comment.getUsername();
                JLabel userLabel = new JLabel(userIdentityString);
                JLabel commentString =  new JLabel(comment.getCommentString());

                inPanel.add(userLabel,BorderLayout.NORTH );

                panel.add(inPanel);
                panel.add(commentString);
                commentPanel.add(panel);
                commentView.getcommentsScrollPane().revalidate();;
            }
        }
    }

    /**
     * Kam Kar Hou
     * Purpose: This empties the list of commentBlocks
     * and updates the comments table with the new list of comment blocks
     */
    public void updateCommentPanel(){
        commentView.getCommentBlock().removeAll();

        createCommentPanel();
    }

    /**
     * Kam Kar Hou
     * Purpose: A submit button listener to create a new object to be saved into the comments.json database
     * also empties the Comment Area for new comments to be entered
     */
    class commentSubmitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == commentView.getSubmitButton()) {
                // Get comment from field and add to model
                String newCommentId = "C" + commentList.generateCommentIdNum();
                String newProject = currentProjectID;
                User commentor = user;
                String newCommentString = commentView.getCommentArea().getText();

                CommentModel newComment = new CommentModel(newCommentId, newProject,commentor, newCommentString);
                commentList.addItem(newComment);

                // Clear commentfield
                commentView.getCommentArea().setText("");

                updateCommentPanel();

            }
        }
    }
    
    /**
     * Kam Kar Hou
     */
    public JPanel getCommentView(){
        return commentView;
    }


}

