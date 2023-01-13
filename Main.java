import javax.swing.JFrame;

import Controller.CommentController;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import View.CommentView;

public class Main {
    public static void main(String[] args){

        // For testing Project 
        // Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        // Administrator admin = new Administrator("A0001", "Admin", "a01");
        // JFrame frame = new JFrame();     
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(1200, 700);
        // ProjectView projectView = new ProjectView();
        // ProjectController projectController2 = new ProjectController(lecturer2, projectView);
        // frame.add(projectView.wrapper);
        // frame.setVisible(true);
        
        //For testing comments
        CommentModel commentModel = new CommentModel();
        Project tempProject = new Project(); // change to proper constructor
        CommentView cV = new CommentView();
        Lecturer user = new Lecturer("L0001", "Tan", "l01");

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 700);

        CommentController commentController = new CommentController(commentModel, user,tempProject);
        frame2.add(commentController.getCommentView());
        frame2.setVisible(true);
    }

    
}
