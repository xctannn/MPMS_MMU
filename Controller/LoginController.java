package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.LoginView;

import Model.User;

public class LoginController{
    private User userModel;
    private LoginView loginView;

    public LoginController(User model, LoginView view) {
        this.userModel = model;
        this.loginView = view;

        loginView.addLoginButtonListener(new loginButtonListener());
        loginView.addUserTypeListener(new userTypeListener());
    }

    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if(username.isBlank() || password.isBlank()){
                System.out.println("Please fill in the username and password");
            }else
                System.out.println(username + " " + password);
        }
    }

    class userTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String userType = loginView.getUserType();
            if(userType == "Student"){
                System.out.println("A Student");
            }else if(userType == "Lecturer"){
                System.out.println("A Lecturer");
            }else{
                System.out.println("An Admin");
            }
        }
    }
}
