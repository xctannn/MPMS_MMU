package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView{
    private String[] userList = {"Student", "Lecturer", "Admin"};

    private JComboBox userType = new JComboBox(userList);
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton loginButton = new JButton("Login");

    public JPanel loginPanel = new JPanel();

    public LoginView(){

        loginPanel.setLayout(null);
        
        userTypeLabel.setBounds(10,15,80,25);
        usernameLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(10,90,80,25);

        userType.setBounds(100, 15, 80, 25);

        usernameField.setBounds(100, 50,150,25);
        passwordField.setBounds(100, 90,150,25);
        loginButton.setBounds(10, 120, 100, 25);
        
        loginPanel.add(userTypeLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(userType);
        loginPanel.add(usernameField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

    }

    public void addLoginButtonListener(ActionListener listenforLoginButton){
        loginButton.addActionListener(listenforLoginButton);
    }

    public void addUserTypeListener(ActionListener listenforUserType){
        userType.addActionListener(listenforUserType);
    }

    public String getUserType(){
        return userType.getSelectedItem().toString();
    }

    public String getUsername(){
        return usernameField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }
}  

