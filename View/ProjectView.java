package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
    private JPanel tableView = new JPanel(new BorderLayout());
    private String[] columnNames = {"ID", "Name", "Lecturer"};

    // Project Detail View Components
    JPanel projectPanel = new JPanel(new BorderLayout());
    private JLabel projectName = new JLabel("Project"); 
    private JLabel projectLecturer = new JLabel("By ");
    private JLabel projectSpecialization = new JLabel("Specialization: ");
    private JTextArea projectContent = (new JTextArea(5,10));
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton assignStudentButton = new JButton("Assign");
    private JTable projectTable = new JTable(); 

    public ProjectView(){

        wrapper.setLayout(new GridLayout(1,2));

        tableView.add(new JScrollPane(projectTable), BorderLayout.CENTER);

        // Project Panel Title Setup
        projectName.setFont(new Font(projectName.getFont().toString(), Font.BOLD, 25));
        projectLecturer.setEnabled(false);

        JPanel projectPanelTitle = new JPanel();
        projectPanelTitle.setLayout(new BoxLayout(projectPanelTitle, BoxLayout.PAGE_AXIS));
        projectPanelTitle.add(projectName);
        projectPanelTitle.add(projectLecturer);
        projectPanelTitle.add(projectSpecialization);
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);

        //Project Panel Content Setup
        projectPanel.add(projectContent, BorderLayout.CENTER);
        projectContent.setEditable(false);
        projectPanel.add(projectStudent, BorderLayout.CENTER);

        // Project Panel Buttons Setup
        projectPanel.add(assignStudentButton, BorderLayout.SOUTH);
        assignStudentButton.setVisible(true);

        wrapper.add(tableView);
        wrapper.add(projectPanel);
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
