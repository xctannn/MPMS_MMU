package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import Model.StudentList;

/*
 * Chua Hui Yi
 * Purpose: A view class for the register system
 */
public class RegisterView extends JPanel{

    // Initializing instances
    private StudentList studentList = new StudentList();

    String studentId = "S" + studentList.generateCode(studentList.getSize());

    // Arrays that contain choices to be set into the combobox
    private String[] userList = {"Student", "Lecturer", "Admin"};
    private String[] specializationList = {"Data Science", "Software Engineering", "Game Development", "Cybersecurity", "Artificial Intelligence"};
    
    // Register View Components
    private JComboBox<String> userType = new JComboBox<>(userList);
    private JComboBox<String> specialization = new JComboBox<>(specializationList);
    public JPanel registerPanel= new JPanel();
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel userIDLabel = new JLabel("User ID: ");
    private JLabel userID = new JLabel(studentId);
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel specializationLabel = new JLabel("Specialization: ");
    private JTextField usernameField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JButton registerButton = new JButton("Register");
    private JButton cancelButton = new JButton("Cancel");

    private GridBagConstraints gbc = new GridBagConstraints();
    
    /*
    * Chua Hui Yi
    * Constructor to build the view for register system
    */
    public RegisterView() {
        userType.setSelectedItem("Student");
        this.setLayout(new BorderLayout());
        registerPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 5, 5, 5);

        // RegisterView Components Setup
        addComponent(registerPanel, userTypeLabel, userType, gbc,0,1);
        addComponent(registerPanel, userIDLabel, userID, gbc,0,2);
        addComponent(registerPanel, usernameLabel, usernameField, gbc,0,3);
        addComponent(registerPanel, passwordLabel, passwordField, gbc,0,4);
        addComponent(registerPanel, specializationLabel, specialization, gbc,0,5);

        // Set the gridbagconstraints for the buttons
        gbc.gridx = 1;
        gbc.gridy = 6;
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        registerPanel.add(cancelButton, gbc);
        this.add(registerPanel, BorderLayout.CENTER);
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
    public void addUserTypeListener(ActionListener listenForUserType){
        userType.addActionListener(listenForUserType);
    }

    // Chua Hui Yi
    public void addRegisterButtonListener(ActionListener listenforRegisterButton){
        registerButton.addActionListener(listenforRegisterButton);
    }

    public void addCancelButtonListener(ActionListener listenforCancelButton){
        cancelButton.addActionListener(listenforCancelButton);
    }

    // GETTERS AND SETTERS
    // Chua Hui Yi
    public void setID(String userId){
        userID.setText(userId);
    }

    // Chua Hui Yi
    public JComboBox<String> getComboBox(){
        return specialization;
    }

    // Chua Hui Yi
    public String getSpecialization(){
        return specialization.getSelectedItem().toString();
    }

    // Chua Hui Yi
    public String getUserType(){
        return userType.getSelectedItem().toString();
    }

    // Chua Hui Yi
    public String getUsername(){
        return usernameField.getText();
    }

    // Chua Hui Yi
    public String getPassword(){
        return passwordField.getText();
    }
}
