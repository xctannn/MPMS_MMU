package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame{
    private JRadioButton studentBtn = new JRadioButton("Student");
    private JRadioButton lecturerBtn = new JRadioButton("Lecturer");
    private JRadioButton adminBtn = new JRadioButton("Admin");
    
    private JLabel userTypeLabel = new JLabel("User Type: ");
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton registerButton = new JButton("Register");

    public RegisterView(){
        JPanel registerPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        registerPanel.setLayout(null);
        ButtonGroup group = new ButtonGroup();
        group.add(studentBtn);
        group.add(lecturerBtn);
        group.add(adminBtn);

        userTypeLabel.setBounds(10,15,80,25);
        usernameLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(10,90,80,25);

        studentBtn.setBounds(100,15,80,25);
        lecturerBtn.setBounds(180,15,80,25);
        adminBtn.setBounds(260,15,80,25);

        usernameField.setBounds(100, 50,150,25);
        passwordField.setBounds(100, 90,150,25);
        registerButton.setBounds(10, 120, 100, 25);

        registerPanel.add(userTypeLabel);
        registerPanel.add(usernameLabel);
        registerPanel.add(passwordLabel);

        registerPanel.add(studentBtn);
        registerPanel.add(lecturerBtn);
        registerPanel.add(adminBtn);

        registerPanel.add(usernameField);
        registerPanel.add(passwordField);
        registerPanel.add(registerButton);

        this.add(registerPanel);
    }

    public void addRegisterButtonListener(ActionListener listenforRegisterButton){
        registerButton.addActionListener(listenforRegisterButton);
    }
    
    public void studentBtnListener(ActionListener listenforStudentBtn){
        studentBtn.addActionListener(listenforStudentBtn);
    }

    public void lecturerBtnListener(ActionListener listenforLecturerBtn){
        lecturerBtn.addActionListener(listenforLecturerBtn);
    }

    public void adminBtnListener(ActionListener listenforAdminBtn){
        adminBtn.addActionListener(listenforAdminBtn);
    }

    public String getUsername(){
        return usernameField.getText();
    }

    public String getPassword(){
        return passwordField.getText();
    }
}
