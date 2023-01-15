import javax.swing.JFrame;

import Controller.ProjectController;
import Controller.ReportController;
import Model.Administrator;
import Model.Lecturer;
import Model.Project;
//import Model.Report;
import Model.Student;
import View.ProjectView;
//import View.ReportView;
import Controller.CommentController;
<<<<<<< HEAD
import Controller.MainController;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import View.ProjectView;
//import View.CommentView;

public class Main {
    public static void main(String[] args){
=======
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

>>>>>>> main
    
        // Administrator admin = new Administrator("A0001", "Admin", "a01");
        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(1200, 700);
        
        // ProjectView projectView = new ProjectView();
        // //ProjectController projectController = new ProjectController(admin, projectView);
        // ProjectController projectController = new ProjectController(admin, projectView);
        // frame.add(projectView);
        // frame.setVisible(true);

        
        // Administrator admin = new Administrator("A0001", "Admin", "a01");
        // JFrame frame2 = new JFrame();
        // frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame2.setSize(1200, 700);
        // Report report = new Report("123");
        // ReportView reportView = new ReportView();
        // ReportController reportController = new ReportController(admin,report,reportView);
        // frame2.add(reportView.generateWrapperPanel);

        // frame2.setVisible(true);
        
        //Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        Administrator admin = new Administrator("A0001", "Admin", "a01");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        
        ProjectView projectView = new ProjectView(admin);
        //MainController mainController;
        ProjectController projectController = new ProjectController(admin, projectView);
        frame.add(projectController.getProjectView());
        frame.setVisible(true);
    }
} 

