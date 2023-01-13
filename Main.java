import javax.swing.JFrame;

import Controller.CommentController;
import Controller.ProjectController;
import Model.CommentModel;
import Model.Administrator;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.CommentView;
import View.ProjectView;

public class Main {
    public static void main(String[] args){

        //For testing comments
        CommentModel commentModel = new CommentModel();
        Project tempProject = new Project("P0001");
        CommentView cV = new CommentView();
        Lecturer user = new Lecturer("L0001", "Tan", "l01");

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 600);

        CommentController commentController = new CommentController(commentModel, user,tempProject);
        frame2.add(commentController.getCommentView());
        frame2.setVisible(true);
    }

    
}
