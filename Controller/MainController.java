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
    private CommentController commentController;
    private JPanel mainView;
    private CardLayout cardLayout;
    
    private MainController(){
        this.cardLayout = new CardLayout();
        this.mainView = new JPanel(cardLayout);
        this.loginController = new LoginController(this);
        this.registerController = new RegisterController(this);
        this.projectController = new ProjectController(this, new Administrator());
        // this.commentController = new CommentController(this);

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

    // pseudo code for how to switch to comment view
    // public void switchCommentView(String projectId, User user){
    //     mainView.remove(3);
    //     this.commentController = new CommentController(this, projectId, user);
    //     mainView.add(projectController.getProjectView(), "student", 2);
    //     cardLayout.show(mainView, "comment");
    // }

    public void init(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        frame.add(mainView);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        
        MainController main = new MainController();
        main.init();

    } 
}
