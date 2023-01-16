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

/*
 * Chua Hui Yi
 * Purpose: Controller for obtaining the login information and handling the login
 */
public class LoginController{

    // Initializing instances
    private MainController mainController;
    private StudentList studentList = new StudentList();
    private LecturerList lecturerList = new LecturerList();
    private AdministratorList adminList = new AdministratorList();

    private LoginView loginView;

     /*
      * Chua Hui Yi
      * Purpose: Constructor to build the login system view
      */
    public LoginController(MainController mainController) {
        this.mainController = mainController;
        this.loginView = new LoginView();

        loginView.addLoginButtonListener(new loginButtonListener());
    }

    // Chua Hui Yi
    public JPanel getLoginView(){
        return loginView;
    }

    /*
     * Chua Hui Yi
     * Purpose: Handle the user login if inputs are valid and redirect to the project view according to the user type
     */
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
                
                if(userType == "Student"){
                    mainController.switchProjectView(studentList.getItem(userID));
                }else if(userType == "Lecturer")
                    mainController.switchProjectView(lecturerList.getItem(userID));
                else{
                    mainController.switchProjectView(adminList.getItem(userID));
                }

            }catch(IllegalArgumentException exception){
                LoginView.displayErrorMessage(exception.getMessage());
            }
        }
    }

     /*
      * Chua Hui Yi
      * Purpose: Check the user type and return the object according to the user type
      */
    private Object getModel(String userType){
        String userID = loginView.getUserID();
        if(userType == "Student"){
            studentList.setList();
            return studentList.getItem(userID);
        }else if(userType == "Lecturer"){
            lecturerList.setList();
            return lecturerList.getItem(userID);
        }else{
            adminList.setList();
            return adminList.getItem(userID);
        }
    }

    /*
     * Chua Hui Yi
     * Purpose: Check if the username and password field are empty
     */
    private void checkNamePassword(String userID, String password) throws IllegalArgumentException{
        if(userID.isEmpty()){
            throw new IllegalArgumentException("Username must not be empty");
        }else if(password.isEmpty()){
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

     /*
      * Chua Hui Yi
      * Purpose: Check if the user exists in database and check if the password match
      */
    private void checkUserExists(String userType, String password){
        Object model = getModel(userType);
        if(model == null){
            throw new IllegalArgumentException("User account does not exist");
        }else if(!(password.equals(((User) model).getPassword()))){
            throw new IllegalArgumentException("Password does no match");
        }
    }
}
