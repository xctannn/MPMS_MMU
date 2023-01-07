package Controller;

import javax.xml.stream.events.Comment;

import Model.CommentModel;
import Model.User;
import View.CommentView;
import View.LoginView;
import View.ProjectView;
import View.RegisterView;

public class MainController {
    private User user;
    private CommentView commentView;
    private LoginView loginView;
    private ProjectView projectView;
    private RegisterView registerview;
    private CommentController commentController;
    private LecturerController lecturerController;
    private ProjectController projectController;
    private RegisterController registerController;
    private CommentModel commentModel = new CommentModel();

    // public MainController(User user,CommentController comControl, LecturerController lecControl, ProjectController proControl, RegisterController regController) {

    //     this.user = user;
    //     this.commentController = comControl;
    //     this.lecturerController = lecControl;
    //     this.projectController = proControl;
    //     this.registerController = regController;
    // }

    public MainController(User user, CommentView cv, LoginView lv, ProjectView pv, RegisterView rv){
        this.user = user;
        this.commentView = cv;
        this.loginView = lv;
        this.projectView = pv;
        this.registerview = rv;

        this.commentController = new CommentController();
        this.lecturerController = new LecturerController();
        this.projectController = new ProjectController();
        this.registerController = new RegisterController();

    }


}
