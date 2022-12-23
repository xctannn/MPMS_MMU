package View;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProjectView extends JFrame{
    private JLabel projectName = new JLabel(); 
    private JLabel projectLecturer = new JLabel("By ");
    private JLabel projectSpecialization = new JLabel();
    private JTextArea projectContent = (new JTextArea(5,10));
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton returnButton = new JButton("Return");
    private JButton assignStudentButton = new JButton("Assign");

    public ProjectView(){
        JPanel ProjectPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        ProjectPanel.add(projectName);
        ProjectPanel.add(projectLecturer);
        ProjectPanel.add(projectSpecialization);
        ProjectPanel.add(projectContent);
        projectContent.setEditable(false);
        ProjectPanel.add(projectStudent);
        ProjectPanel.add(returnButton);
        ProjectPanel.add(assignStudentButton);
        assignStudentButton.setVisible(false);


        this.add(ProjectPanel);
    }


    public void addReturnButtonListener(ActionListener listenforReturnButton){
        returnButton.addActionListener(listenforReturnButton);
    }

    public void addAssignButtonListener(ActionListener listenforAssignButton){
        assignStudentButton.addActionListener(listenforAssignButton);
    }

    public void setAssignButtonVisible(){
        assignStudentButton.setVisible(true);
    }

    public void getStudentID(){
        JOptionPane.showInputDialog("Enter the Student's ID");
    }
    
    public void setProjectNameLabel(String name){
        projectName.setText(name);
    }

    public void setProjectLecturerLabel(String lecturer){
        projectLecturer.setText(lecturer);
    }

    public void setProjectSpecializationLabel(String specialization){
        projectSpecialization.setText(specialization);
    }

    public void setProjectContentArea(String content){
        projectContent.setText(content);
    }

    public void setProjectStudentLabel(String studentName){
        projectName.setText("Assigned to: " + studentName);
    }

}
