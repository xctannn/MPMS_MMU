package View;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ProjectView {

    // Projects Table View Components
    public JPanel wrapper = new JPanel();
    JPanel tableView = new JPanel();
    private String[] columnNames = {"ID", "Name", "Lecturer"};

    // Project Detail View Components
    JPanel projectPanel = new JPanel();
    private JLabel projectName = new JLabel(); 
    private JLabel projectLecturer = new JLabel("By ");
    private JLabel projectSpecialization = new JLabel();
    private JTextArea projectContent = (new JTextArea(5,10));
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton returnButton = new JButton("Return");
    private JButton assignStudentButton = new JButton("Assign");
    private JTable projectTable = new JTable(); 

    public ProjectView(){

        wrapper.setLayout(new GridLayout(1,2));

        tableView.add(new JScrollPane(projectTable));

        projectPanel.add(projectName);
        projectPanel.add(projectLecturer);
        projectPanel.add(projectSpecialization);
        projectPanel.add(projectContent);
        projectContent.setEditable(false);
        projectPanel.add(projectStudent);
        projectPanel.add(returnButton);
        projectPanel.add(assignStudentButton);
        assignStudentButton.setVisible(false);

        wrapper.add(tableView);
        wrapper.add(projectPanel);
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

    public String[] getColumnNames(){
        return columnNames;
    } 

    public JTable getProjectTable(){
        return projectTable;
    }

    public String getStudentID(){
        return JOptionPane.showInputDialog("Enter the Student's ID");
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
