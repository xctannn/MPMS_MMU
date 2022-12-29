package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.User;
import View.RegisterView;

public class RegisterController {
    private User userModel;
    private RegisterView registerView;
    private String userType;

    public RegisterController(User model, RegisterView view){
        this.userModel = model;
        this.registerView = view;

        registerView.addRegisterButtonListener(new registerBtnListener());
        registerView.studentBtnListener(new studentListener());
        registerView.lecturerBtnListener(new lecturerListener());
        registerView.adminBtnListener(new adminListener());
    }

    class registerBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("testing");

            String username = registerView.getUsername();
            String password = registerView.getPassword();
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
