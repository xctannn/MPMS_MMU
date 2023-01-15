package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Administrator;
import Model.Lecturer;
import Model.Student;
import Model.User;

/*
 * Purpose: Redirect user to different pages of the system
 */
public class MainController {
    private LoginController loginController;
    private RegisterController registerController;
    private ProjectController projectController;
    private CommentController commentController;
    private JPanel mainView;
    private CardLayout cardLayout;
    
    /*
     * Yaw Boon Zhe
     * No-arg Constructor
     */
    private MainController(){
        this.cardLayout = new CardLayout();
        this.mainView = new JPanel(cardLayout);
        this.loginController = new LoginController(this);
        this.registerController = new RegisterController(this);
        this.projectController = new ProjectController(this, new Administrator());
        this.commentController = new CommentController(this, new Administrator(), "");

        mainView.add(loginController.getLoginView(), "login", 0);
        mainView.add(registerController.getRegisterView(), "register", 1);
        mainView.add(projectController.getProjectView(), "startProjectView", 2);
        mainView.add(commentController.getCommentView(), "startCommentView", 3);
    }

    // Yaw Boon Zhe
    public JPanel getMainView(){
        return mainView;
    }

    // Yaw Boon Zhe
    public void switchLoginView(){
        cardLayout.show(mainView,"login");
    }

    // Yaw Boon Zhe
    public void switchRegisterView(){
        cardLayout.show(mainView,"register");
    }

    // Yaw Boon Zhe
    public void returnProjectView(){
        cardLayout.show(mainView, "admin");
    }

    /*
     * Yaw Boon Zhe
     * Purpose: redirect Administrator user to project view
     */
    public void switchProjectView(Administrator adminUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, adminUser);
        mainView.add(projectController.getProjectView(), "admin", 2);
        cardLayout.show(mainView, "admin");
    }

    /*
     * Yaw Boon Zhe
     * Purpose: redirect Lecturer user to project view
     */
    public void switchProjectView(Lecturer lecturerUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, lecturerUser);
        mainView.add(projectController.getProjectView(), "lecturer", 2);
        cardLayout.show(mainView, "lecturer");
    }

    /*
     * Yaw Boon Zhe
     * Purpose: redirect Student user to project view
     */
    public void switchProjectView(Student studentUser){
        mainView.remove(2);
        this.projectController = new ProjectController(this, studentUser);
        mainView.add(projectController.getProjectView(), "student", 2);
        cardLayout.show(mainView, "student");
    }

    /*
     * Yaw Boon Zhe
     * Purpose: redirect user to comment view
     */
    public void switchCommentView(String projectId, User user){
        mainView.remove(3);
        this.commentController = new CommentController(this, user, projectId);
        mainView.add(commentController.getCommentView(), "comment", 3);
        cardLayout.show(mainView, "comment");
    }

    // Yaw Boon Zhe
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