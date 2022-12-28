package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    private JRadioButton studentButton = new JRadioButton("Student");
    private JRadioButton lecturerButton = new JRadioButton("Lecturer");
    private JRadioButton adminButton = new JRadioButton("Admin");
    

    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton loginButton = new JButton("Login");

    public LoginView(){
        JPanel loginPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        loginPanel.setLayout(null);
        ButtonGroup group = new ButtonGroup();
        group.add(studentButton);
        group.add(lecturerButton);
        group.add(adminButton);
        
        userTypeLabel.setBounds(10,15,80,25);
        usernameLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(10,90,80,25);

        studentButton.setBounds(100,15,80,25);
        lecturerButton.setBounds(180,15,80,25);
        adminButton.setBounds(260,15,80,25);

        usernameField.setBounds(100, 50,150,25);
        passwordField.setBounds(100, 90,150,25);
        loginButton.setBounds(10, 120, 100, 25);
        
        loginPanel.add(userTypeLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(passwordLabel);

        loginPanel.add(studentButton);
        loginPanel.add(lecturerButton);
        loginPanel.add(adminButton);

        loginPanel.add(usernameField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        this.add(loginPanel);
    }

    public void addLoginButtonListener(ActionListener listenforLoginButton){
        loginButton.addActionListener(listenforLoginButton);
    }

    public void studentButtonListener(ActionListener listenforStudentButton){
        studentButton.addActionListener(listenforStudentButton);
    }

    public void lecturerButtonListener(ActionListener listenforLecturerButton){
        lecturerButton.addActionListener(listenforLecturerButton);
    }

    public void adminButtonListener(ActionListener listenforAdminButton){
        adminButton.addActionListener(listenforAdminButton);
    }

    // public String getStudentType(){
    //     return studentButton.getText();
    // }

    // public String getLecturerType(){
    //     return lecturerButton.getText();
    // }

    // public String getAdminType(){
    //     return adminButton.getText();
    // }

    public String getUsername(){
        return usernameField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }
}    

