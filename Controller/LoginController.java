package Controller;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.LoginView;
import Model.User;
import Model.Student;
import Model.Lecturer;
import Model.Administrator;
import Model.LecturerList;
import Model.StudentList;
import Model.AdministratorList;

public class LoginController{
    private User user;
    private Student studentModel;
    private Lecturer lecturerModel;
    private Administrator adminModel;

    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private LoginView loginView;

    public LoginController(User model, LoginView view) {
        this.user = model;
        this.loginView = view;

        loginView.addLoginButtonListener(new loginButtonListener());
    }

    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String userID = loginView.getUserID();
            String password = loginView.getPassword();

            if(userID.isBlank() || password.isBlank()){
                JOptionPane.showMessageDialog(null, "Please fill in the username and password");

            }else if(loginView.getUserType() == "Student"){
                studentModel = studentList.getItem(userID);
                if (studentModel == null){
                    JOptionPane.showMessageDialog(null, "User account does not exist");
                }else if (!(password.equals(studentModel.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }else {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                }
                
            }else if(loginView.getUserType() == "Lecturer"){
                lecturerModel = lecturerList.getItem(userID);
                if (lecturerModel == null){
                    JOptionPane.showMessageDialog(null, "User account does not exist");
                }else if (!(password.equals(lecturerModel.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }else {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                }

            }else{
                adminModel = adminList.getItem(userID);
                if (adminModel == null){
                    JOptionPane.showMessageDialog(null, "User account does not exist");
                }else if (!(password.equals(adminModel.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }else {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                }
            }
        }
    }
}
