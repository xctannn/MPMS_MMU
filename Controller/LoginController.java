package Controller;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.LoginView;
import Model.User;
import Model.LecturerList;
import Model.StudentList;
import Model.AdministratorList;

public class LoginController{

    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private LoginView loginView;

    public LoginController(LoginView view) {
        this.loginView = view;

        loginView.addLoginButtonListener(new loginButtonListener());
    }

    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String userID = loginView.getUserID();
                String password = loginView.getPassword();
                String userType = loginView.getUserType();

                checkNamePassword(userID, password);
                checkUserExists(userType, password);
                JOptionPane.showMessageDialog(null, "Login Successful");
                
                // if(userType == "Student"){
                //     mainController.switchProjectView(studentList.getItem(userID));
                // }else if(userType == "Lecturer")
                //     mainController.switchProjectView(lecturerList.getItem(userID));
                // else{
                //     mainController.switchProjectView(adminList.getItem(userID));
                // }

            }catch(IllegalArgumentException exception){
                LoginView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private Object getModel(String userType){
        String userID = loginView.getUserID();
        if(userType == "Student"){
            return studentList.getItem(userID);
        }else if(userType == "Lecturer"){
            return lecturerList.getItem(userID);
        }else{
            return adminList.getItem(userID);
        }
    }

    private void checkNamePassword(String userID, String password) throws IllegalArgumentException{
        if(userID.isEmpty()){
            throw new IllegalArgumentException("Username must not be empty");
        }else if(password.isEmpty()){
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

    private void checkUserExists(String userType, String password){
        Object model = getModel(userType);
        if(model == null){
            throw new IllegalArgumentException("User account does not exist");
        }else if(!(password.equals(((User) model).getPassword()))){
            throw new IllegalArgumentException("Password do no match");
        }
    }


}
