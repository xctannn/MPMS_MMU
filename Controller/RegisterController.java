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

/*
 * Chua Hui Yi
 * Purpose: Controller for obtaining the register information and handling the registration
 */
public class RegisterController {

    // Initializing instances
    private MainController mainController;
    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private RegisterView registerView;

     /*
      * Chua Hui Yi
      * Purpose: Constructor to build the register system view
      */
    public RegisterController(MainController mainController){
        this.mainController = mainController;
        this.registerView = new RegisterView();

        registerView.addRegisterButtonListener(new registerBtnListener());
        registerView.addUserTypeListener(new userTypeListener());
        registerView.addCancelButtonListener(new cancelButtonListener());
    }

    // Chua Hui Yi
    public JPanel getRegisterView(){
        return registerView;
    }

    /*
     * Chua Hui Yi
     * Purpose: Check the user type to enable or disable the specialization combobox
     */
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

    /*
     * Chua Hui Yi
     * Purpose: Save the user data to database if the inputs are valid
     */
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
                    Student newStudent = new Student(id, newUsername, newPassword, specialization, null);
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

    /*
      * Chua Hui Yi
      * Purpose: return to project view
      */
    class cancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainController.returnProjectView();
        }
    }
    
     /*
      * Chua Hui Yi
      * Purpose: Check if the username and password field are empty
      */
    private void checkNamePassword(String username, String password) throws IllegalArgumentException{
        if(username.isEmpty()){
            throw new IllegalArgumentException("Username must not be empty");
        }else if(password.isEmpty()){
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

     /*
      * Chua Hui Yi
      * Purpose: Get the user id
      */
    private String getUserID(String userType){
        if(userType == "Student"){
            return "S" + studentList.generateCode(studentList.getSize());
        }else if(userType == "Lecturer")
        {
            return "L" + lecturerList.generateCode(lecturerList.getSize());
        }else{
            return "A" + adminList.generateCode(adminList.getSize());
        }
    }

     /*
      * Chua Hui Yi
      * Purpose: Update the user id whenever a new account is created
      */
    private String updateID(String userType){
        if(userType == "Student"){
            return "S" + studentList.generateCode(studentList.getSize()+1);
        }else if(userType == "Lecturer")
        {
            return "L" + lecturerList.generateCode(lecturerList.getSize()+1);
        }else{
            return "A" + adminList.generateCode(adminList.getSize()+1);
        }
    }
}
