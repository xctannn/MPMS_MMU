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
        CommentModel commentModel = new CommentModel();
        // ProjectList projectList = new ProjectList();
        Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        Administrator admin = new Administrator("A0001", "Admin", "a01");


        JFrame frame = new JFrame();
        JFrame frame2 = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        
        ProjectView projectView = new ProjectView();
        CommentView cV = new CommentView();
        ProjectController projectController = new ProjectController(commentModel, cV);
        ProjectController projectController = new ProjectController(projectModel, projectView);
        frame.add(projectView.wrapper);
        frame.setVisible(true);

        frame2.add(cV.wrapper);
        frame2.setVisible(true);
    } 
}
