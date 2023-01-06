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
            String userID = loginView.getUserID();
            String password = loginView.getPassword();
            String userType = loginView.getUserType();

            if(userID.isBlank() || password.isBlank()){
                JOptionPane.showMessageDialog(null, "Please fill in the username and password","Error", JOptionPane.ERROR_MESSAGE);

            }else if(userType == "Student"){
                checkUser(userType, userID, password);
            }else if(userType == "Lecturer"){
                checkUser(userType, userID, password);
            }else{
                checkUser(userType, userID, password);
            }
        }
    }

    public Object getModel(String userType){
        String userID = loginView.getUserID();
        if(userType == "Student"){
            return studentList.getItem(userID);
        }else if(userType == "Lecturer"){
            return lecturerList.getItem(userID);
        }else{
            return adminList.getItem(userID);
        }
    }

    public void checkUser(String userType, String userID, String password){
        Object model = getModel(userType);
        if(model == null){
            JOptionPane.showMessageDialog(null, "User account does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!(password.equals(((User) model).getPassword()))){
            JOptionPane.showMessageDialog(null, "Password do not match", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Login Successful");
        }
    }
}
