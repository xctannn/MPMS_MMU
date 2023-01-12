package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Lecturer;
import Model.Student;
import Model.Administrator;
import Model.StudentList;
import Model.LecturerList;
import Model.AdministratorList;
import View.RegisterView;

public class RegisterController {

    // Initializing instances
    private Lecturer lecturerModel = new Lecturer();
    private Student studentModel = new Student();
    private Administrator adminModel = new Administrator();
    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private RegisterView registerView;

    // Construct register controller
    public RegisterController(){
        this.registerView = new RegisterView();

        registerView.addRegisterButtonListener(new registerBtnListener());
        registerView.addUserTypeListener(new userTypeListener());
    }

    public JPanel getRegisterView(){
        return registerView;
    }

    // Implementing the action listener to the user type combobox
    class userTypeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JComboBox<String> specializationType = registerView.getComboBox();
            String userType = registerView.getUserType();
            String id = getUserID(userType);
            
            if(userType == "Student"){
                specializationType.setEnabled(true);
            }else{
                specializationType.setEnabled(false);
            }
            registerView.setID(id);
        }
    }

    // Implementing the action listener to the register button
    class registerBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String newUsername = registerView.getUsername();
                String newPassword = registerView.getPassword();
                String userType = registerView.getUserType();
                String id = getUserID(userType);
                String newID = updateID(userType);

                checkNamePassword(newUsername, newPassword);
            
                if(userType == "Student"){
                    String specialization = registerView.getSpecialization();
                    Student newStudent = new Student(id, newUsername, newPassword, specialization, "");
                    studentList.addItem(newStudent);
                }else if(userType == "Lecturer"){
                    Lecturer newLecturer = new Lecturer(id, newUsername, newPassword);
                    lecturerList.addItem(newLecturer);
                }else{
                    Administrator newAdmin = new Administrator(id, newUsername, newPassword);
                    adminList.addItem(newAdmin);
                }
                registerView.setID(newID);
                JOptionPane.showMessageDialog(null, "Register Successful");
            
            }catch(IllegalArgumentException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // class cancelButtonListener implements ActionListener{
    //      mainController.switchProjectView(adminList.getItem(userID));
    // }

    // Validating the inputs
    private void checkNamePassword(String username, String password) throws IllegalArgumentException{
        if(username.isEmpty()){
            throw new IllegalArgumentException("Username must not be empty");
        }else if(password.isEmpty()){
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

    // Generate the user ID
    private String getUserID(String userType){
        if(userType == "Student"){
            return "S" + studentModel.generateCode(studentList.getSize());
        }else if(userType == "Lecturer")
        {
            return "L" + lecturerModel.generateCode(lecturerList.getSize());
        }else{
            return "A" + adminModel.generateCode(adminList.getSize());
        }
    }

    // Update the user ID whenever a new account is created
    private String updateID(String userType){
        if(userType == "Student"){
            return "S" + studentModel.generateCode(studentList.getSize()+1);
        }else if(userType == "Lecturer")
        {
            return "L" + lecturerModel.generateCode(lecturerList.getSize()+1);
        }else{
            return "A" + adminModel.generateCode(adminList.getSize()+1);
        }
    }
}
