package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.LoginView;

import Model.User;

public class LoginController{
    private User userModel;
    private LoginView loginView;
    private String userType;

    public LoginController(User model, LoginView view) {
        this.userModel = model;
        this.loginView = view;

        loginView.addLoginButtonListener(new loginButtonListener());
        loginView.studentButtonListener(new studentListener());
        loginView.lecturerButtonListener(new lecturerListener());
        loginView.adminButtonListener(new adminListener());
    }

    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            System.out.println(username + " " + password + " " + userType);
        }
    }

    class studentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            userType = "Student";
        }
    }
    class lecturerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            userType = "Lecturer";
        }
    }
    class adminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            userType = "Admin";
        }
    }
}
