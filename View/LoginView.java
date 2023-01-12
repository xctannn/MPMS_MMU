package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginView extends JPanel{
    private String[] userList = {"Student", "Lecturer", "Admin"};
    GridBagConstraints gbc = new GridBagConstraints();

    private JComboBox userType = new JComboBox(userList);
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel userIDLabel = new JLabel("User ID: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField userIDField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JButton loginButton = new JButton("Login");


    public LoginView(){

        JPanel loginForm = new JPanel();
        this.setLayout(new BorderLayout());
        loginForm.setLayout(new GridBagLayout());
        gbc.insets = new Insets(3,3,3,3);
        
        addComponent(loginForm, userTypeLabel, userType, gbc, 0, 0);
        addComponent(loginForm, userIDLabel, userIDField, gbc, 0, 1);
        addComponent(loginForm, passwordLabel, passwordField, gbc, 0,2);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginForm.add(loginButton, gbc);

        this.add(loginForm, BorderLayout.CENTER);
    }

    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(label, gbc);
        gbc.gridx = gridx + 1;
        panel.add(component, gbc);
    }

    public static void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addLoginButtonListener(ActionListener listenforLoginButton){
        loginButton.addActionListener(listenforLoginButton);
    }

    public String getUserType(){
        return userType.getSelectedItem().toString();
    }

    public String getUserID(){
        return userIDField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }
}  

