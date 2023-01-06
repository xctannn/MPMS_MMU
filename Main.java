import javax.swing.JFrame;
import javax.swing.JFrame;

import Controller.ProjectController;
import Model.Administrator;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.ProjectView;

public class Main {
    public static void main(String[] args){
        // ProjectList projectList = new ProjectList();
        Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        Administrator admin = new Administrator("A0001", "Admin", "a01");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        Student student = new Student("S001", "S1", "s01", "Data Science", "P0002");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // lecturer2.addproject("P0004");
        // projectList.addItem(new Project("P0003", "Final Year", "Data", "Build this game for me or else you fail your FYP and spend another 10k on your degree", lecturer2));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        
        ProjectView projectView = new ProjectView();
        ProjectController projectController = new ProjectController(admin, projectView);
        frame.add(projectView);
        frame.setVisible(true);
    } 
}
