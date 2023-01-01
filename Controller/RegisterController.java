package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.User;
import View.RegisterView;

public class RegisterController {
    private User userModel;
    private RegisterView registerView;

    public RegisterController(User model, RegisterView view){
        this.userModel = model;
        this.registerView = view;

        registerView.addRegisterButtonListener(new registerBtnListener());
        registerView.addUserTypeListener(new userTypeListener());
    }

    class registerBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("testing");

            String username = registerView.getUsername();
            String password = registerView.getPassword();
            System.out.println(username + " " + password);
        }
    }

    class userTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String userType = registerView.getUserType();
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
