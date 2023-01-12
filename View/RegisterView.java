package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import Model.Student;
import Model.StudentList;

public class RegisterView extends JPanel{
    private Student studentModel = new Student();
    private StudentList studentList = new StudentList();
    String studentId = "S" + studentModel.generateCode(studentList.getSize());

    private String[] userList = {"Student", "Lecturer", "Admin"};
    private String[] specializationList = {"Data Science", "Software Engineering", "Game Development", "Cybersecurity"};
    
    private JComboBox userType = new JComboBox(userList);
    private JComboBox specialization = new JComboBox(specializationList);
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
    
    public RegisterView() {
        userType.setSelectedItem("Student");
        this.setLayout(new BorderLayout());
        registerPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 1;
        gbc.gridy = 0;

        addComponent(registerPanel, userTypeLabel, userType, gbc,0,1);
        addComponent(registerPanel, userIDLabel, userID, gbc,0,2);
        addComponent(registerPanel, usernameLabel, usernameField, gbc,0,3);
        addComponent(registerPanel, passwordLabel, passwordField, gbc,0,4);
        addComponent(registerPanel, specializationLabel, specialization, gbc,0,5);

        gbc.gridx = 1;
        gbc.gridy = 6;
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        registerPanel.add(cancelButton, gbc);
        this.add(registerPanel, BorderLayout.CENTER);
    }
    
    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(label, gbc);
        gbc.gridx = gridx + 1;
        panel.add(component, gbc);
    }

    public void addUserTypeListener(ActionListener listenForUserType){
        userType.addActionListener(listenForUserType);
    }

    public void addRegisterButtonListener(ActionListener listenforRegisterButton){
        registerButton.addActionListener(listenforRegisterButton);
    }

    // public void addCancelButtonListener(ActionListener listenforCancelButton){
    //     cancelButton.addActionListener(listenforCancelButton);
    // }

    public void setID(String userId){
        userID.setText(userId);
    }

    public JComboBox getComboBox(){
        return specialization;
    }

    public String getSpecialization(){
        return specialization.getSelectedItem().toString();
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
