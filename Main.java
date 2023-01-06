import javax.swing.JFrame;
import javax.swing.JFrame;

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
        Project tempProject = new Project("P0001");
        CommentView cV = new CommentView();

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(1200, 700);

        ProjectController projectController = new ProjectController(commentModel, cV,tempProject);
        frame2.add(cV.wrapper);
        frame2.setVisible(true);
    } 
}
