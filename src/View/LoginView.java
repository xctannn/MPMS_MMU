package src.View;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Chua Hui Yi
 * Purpose: A view class for the login system
 */
public class LoginView extends JPanel{
    private String[] userList = {"Student", "Lecturer", "Admin"};
    GridBagConstraints gbc = new GridBagConstraints();

    // Login View Components
    private JComboBox<String> userType = new JComboBox<>(userList);
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel userIDLabel = new JLabel("User ID: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField userIDField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");

    /*
    * Chua Hui Yi
    * Constructor to build the view for login system
    */
    public LoginView(){

        JPanel loginForm = new JPanel();
        this.setLayout(new BorderLayout());
        loginForm.setLayout(new GridBagLayout());
        gbc.insets = new Insets(3,3,3,3);
        
        // LoginView Components Setup
        addComponent(loginForm, userTypeLabel, userType, gbc, 0, 0);
        addComponent(loginForm, userIDLabel, userIDField, gbc, 0, 1);
        addComponent(loginForm, passwordLabel, passwordField, gbc, 0,2);

        // Set the gridbagconstraints for login button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginForm.add(loginButton, gbc);

        this.add(loginForm, BorderLayout.CENTER);
    }

    /*
     * Chua Hui Yi
     * Purpose: To set the gridbagconstraints to add the components to the panel
     */
    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(label, gbc);
        gbc.gridx = gridx + 1;
        panel.add(component, gbc);
    }

    // Chua Hui Yi
    public static void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Chua Hui Yi
    public void addLoginButtonListener(ActionListener listenforLoginButton){
        loginButton.addActionListener(listenforLoginButton);
    }

    // GETTERS AND SETTERS
    // Chua Hui Yi
    public String getUserType(){
        return userType.getSelectedItem().toString();
    }

    // Chua Hui Yi
    public String getUserID(){
        return userIDField.getText();
    }

    // Chua Hui Yi
    public char[] getPassword(){
        return passwordField.getPassword();
    }
}  

