package Controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.LoginView;
import Model.User;
import Model.LecturerList;
import Model.StudentList;
import Model.AdministratorList;

public class LoginController{

    // Initializing instances
    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private LoginView loginView;

    // Construct login controller
    public LoginController() {
        this.loginView = new LoginView();

        loginView.addLoginButtonListener(new loginButtonListener());
    }

    public JPanel getLoginView(){
        return loginView;
    }

    // Implementing the action listener to the login button
    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String userID = loginView.getUserID();
                String password = new String(loginView.getPassword());
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

    // Get the usertype and return the object
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

    // Validating the inputs
    private void checkNamePassword(String userID, String password) throws IllegalArgumentException{
        if(userID.isEmpty()){
            throw new IllegalArgumentException("Username must not be empty");
        }else if(password.isEmpty()){
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

    // Check if the user existed in database and validate the password
    private void checkUserExists(String userType, String password){
        Object model = getModel(userType);
        if(model == null){
            throw new IllegalArgumentException("User account does not exist");
        }else if(!(password.equals(((User) model).getPassword()))){
            throw new IllegalArgumentException("Password does no match");
        }
    }


}
