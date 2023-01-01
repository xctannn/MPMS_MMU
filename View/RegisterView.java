package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterView{
    private String[] userList = {"Student", "Lecturer", "Admin"};

    private JComboBox userType = new JComboBox(userList);
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton registerButton = new JButton("Register");
    public JPanel registerPanel = new JPanel();

    public RegisterView(){

        registerPanel.setLayout(null);

        userTypeLabel.setBounds(10,15,80,25);
        usernameLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(10,90,80,25);
        userType.setBounds(100, 15, 80, 25);
        usernameField.setBounds(100, 50,150,25);
        passwordField.setBounds(100, 90,150,25);
        registerButton.setBounds(10, 120, 100, 25);

        registerPanel.add(userTypeLabel);
        registerPanel.add(usernameLabel);
        registerPanel.add(passwordLabel);
        registerPanel.add(userType);
        registerPanel.add(usernameField);
        registerPanel.add(passwordField);
        registerPanel.add(registerButton);
    }

    public void addRegisterButtonListener(ActionListener listenforRegisterButton){
        registerButton.addActionListener(listenforRegisterButton);
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
