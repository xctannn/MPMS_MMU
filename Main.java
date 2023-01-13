import javax.swing.JFrame;

import Controller.CommentController;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import View.CommentView;

public class Main {
    public static void main(String[] args){
        //For testing comments
        CommentModel commentModel = new CommentModel();
        Project tempProject = new Project(); // change to proper constructor
        CommentView cV = new CommentView();
        Lecturer user = new Lecturer("L0001", "Tan", "l01");

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 700);

        // CommentController commentController = new CommentController(commentModel, user,tempProject);
        // frame2.add(commentController.getCommentView());
        // frame2.setVisible(true);
    }

    
}
