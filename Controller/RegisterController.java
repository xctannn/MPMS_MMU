package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Model.Lecturer;
import Model.Student;
import Model.Administrator;
import Model.StudentList;
import Model.LecturerList;
import Model.AdministratorList;
import View.RegisterView;

public class RegisterController {
    private Lecturer lecturerModel = new Lecturer();
    private Student studentModel = new Student();
    private Administrator adminModel = new Administrator();
    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();
    private RegisterView registerView;

    public RegisterController(RegisterView view){
        this.registerView = view;

        registerView.addRegisterButtonListener(new registerBtnListener());
        registerView.addUserTypeListener(new userTypeListener());
    }

    class userTypeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JComboBox specializationType = registerView.getComboBox();
            String userType = registerView.getUserType();
            String id = getUserID(userType);
            
            if(userType == "Student"){
                specializationType.setEnabled(true);
            }else if(userType == "Lecturer"){
                specializationType.setEnabled(false);
            }else{
                specializationType.setEnabled(false);
            }
            registerView.setID(id);
        }
    }

    class registerBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String newUsername = registerView.getUsername();
            String newPassword = registerView.getPassword();
            String userType = registerView.getUserType();
            String id = getUserID(userType);
            String newID = updateID(userType);
            
            if (newUsername.isBlank() || newPassword.isBlank()){
                JOptionPane.showMessageDialog(null, "Please fill in the username and password", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(userType == "Student"){
                String specialization = registerView.getSpecialization();
                Student newStudent = new Student(id, newUsername, newPassword, specialization, "");
                studentList.addItem(newStudent);
                JOptionPane.showMessageDialog(null, "Register Successful");
                registerView.setID(newID);

            }else if(userType == "Lecturer"){
                Lecturer newLecturer = new Lecturer(id, newUsername, newPassword);
                lecturerList.addItem(newLecturer);
                JOptionPane.showMessageDialog(null, "Register Successful");
                registerView.setID(newID);

            }else{
                Administrator newAdmin = new Administrator(id, newUsername, newPassword);
                adminList.addItem(newAdmin);
                JOptionPane.showMessageDialog(null, "Register Successful");
                registerView.setID(newID);
            }
        }
    }

    // class cancelButtonListener implements ActionListener{

    // }

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
