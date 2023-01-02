import javax.swing.JFrame;

import Controller.ProjectController;
import Model.CommentModel;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.CommentView;
import View.ProjectView;

public class Main {
    public static void main(String[] args){
        Project projectModel = new Project();
        CommentModel commentModel = new CommentModel();
        // ProjectList projectList = new ProjectList();
        // Lecturer lecturer2 = new Lecturer("L007", "Mickey Dees", "Disney123");
        // Student student = new Student("S001", "Goofy Arse", "Huekhuek", "Data Science", "P0001");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // lecturer2.addproject("P0004");
        // projectList.addItem(new Project("P0003", "Final Year", "Data", "Build this game for me or else you fail your FYP and spend another 10k on your degree", lecturer2));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        
        //ProjectView projectView = new ProjectView();
        CommentView cV = new CommentView();
        ProjectController projectController = new ProjectController(commentModel, cV);
        frame.add(cV.wrapper);
        frame.setVisible(true);
    } 
}
