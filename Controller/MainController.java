package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Administrator;
import Model.Lecturer;
import Model.Student;

public class MainController {
    private LoginController loginController;
    private RegisterController registerController;
    private ProjectController projectController;
    private JPanel mainView;
    private CardLayout cardLayout;
    // private CommentController commentController;
    
    private MainController(){
        this.cardLayout = new CardLayout();
        this.mainView = new JPanel(cardLayout);
        this.loginController = new LoginController(this);
        this.registerController = new RegisterController();
        this.projectController = new ProjectController(this, new Administrator());

        mainView.add(loginController.getLoginView(), "login", 0);
        mainView.add(registerController.getRegisterView(), "register", 1);
        mainView.add(projectController.getProjectView(), "startProjectView", 2);
    }

    public JPanel getMainView(){
        return mainView;
    }

    public void switchLoginView(){
        cardLayout.show(mainView,"login");
    }

    public void switchRegisterView(){
        cardLayout.show(mainView,"register");
    }

    public void returnAdminView(){
        cardLayout.show(mainView, "admin");
    }

    public void switchProjectView(Administrator adminUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, adminUser);
        mainView.add(projectController.getProjectView(), "admin", 2);
        cardLayout.show(mainView, "admin");
    }

    public void switchProjectView(Lecturer lecturerUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, lecturerUser);
        mainView.add(projectController.getProjectView(), "lecturer", 2);
        cardLayout.show(mainView, "lecturer");
    }

    public void switchProjectView(Student studentUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, studentUser);
        mainView.add(projectController.getProjectView(), "student", 2);
        cardLayout.show(mainView, "student");
    }

    public void init(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        frame.add(mainView);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        // ProjectList projectList = new ProjectList();
        // Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        // Administrator admin = new Administrator("A0001", "Admin", "a01");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // Student student = new Student("S001", "S1", "s01", "Data Science", "P0002");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // lecturer2.addproject("P0004");
        // projectList.addItem(new Project("P0003", "Final Year", "Data", "Build this game for me or else you fail your FYP and spend another 10k on your degree", lecturer2));

        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(1200, 700);
        
        // ProjectController projectController = new ProjectController(this, student);
        // frame.add(projectController.getProjectView());
        // frame.setVisible(true);
        MainController main = new MainController();
        main.init();

    } 
}
