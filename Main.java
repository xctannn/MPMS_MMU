import javax.swing.JFrame;

import Controller.CommentController;
import Controller.ProjectController;
import Model.Administrator;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import Model.Student;

import View.CommentView;

public class Main {
    public static void main(String[] args){
        //For testing comments

        String currentProjectID = "P0001";
        CommentView cV = new CommentView();
        Lecturer user = new Lecturer("L0001", "Tan", "l01");

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 700);

        CommentController commentController = new CommentController( user,currentProjectID);
        frame2.add(commentController.getCommentView());
        frame2.setVisible(true);
    }

    
}
