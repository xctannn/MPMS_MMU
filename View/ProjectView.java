package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class ProjectView {

    // Projects Table View Components
    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());
    private String[] columnNames = {"ID", "Name", "Lecturer"};

    // Project Detail View Components
    JPanel projectPanel = new JPanel(new BorderLayout());
    private JTable projectTable = new JTable(); 
    private JLabel projectName = new JLabel("Project"); 
    private JLabel projectLecturer = new JLabel("By ");
    private JLabel projectSpecialization = new JLabel("Specialization: ");
    private JTextArea projectContent = new JTextArea();
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton editContentButton = new JButton("Edit");
    private JButton saveEditButton = new JButton("Save");
    private JButton activateButton = new JButton("Activate");
    private JButton deactivateButton = new JButton("Deactivate");
    private JButton assignStudentButton = new JButton("Assign");

    public ProjectView(){
        wrapper.setLayout(new GridLayout(1,2));

        // Project Table View Initial Setup 
        JPanel tableWrapper = new JPanel();
        tableWrapper.add(new JScrollPane(projectTable));
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableView.add(tableWrapper, BorderLayout.CENTER);
        
        // Disable all texts on Project Panel on startup
        disableAllPanelTexts();

        // Project Panel Title Setup
        resizePanelTextsFont();
        JPanel projectPanelTitle = new JPanel();
        projectPanelTitle.setLayout(new BoxLayout(projectPanelTitle, BoxLayout.PAGE_AXIS));
        projectPanelTitle.add(projectName);
        projectPanelTitle.add(projectLecturer);
        projectPanelTitle.add(projectSpecialization);
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);

        //Project Panel Content Setup
        JPanel projectPanelContent = new JPanel(new BorderLayout());
        JPanel projectBody = new JPanel();
        projectBody.setLayout(new BoxLayout(projectBody, BoxLayout.PAGE_AXIS));
        projectBody.setBorder( BorderFactory.createEmptyBorder(30,20,20,30));
        JScrollPane projectContentWrapper = new JScrollPane(projectContent);
        projectBody.add(projectContentWrapper);
        setupProjectContentProperties();
        JPanel projectPanelEditWrapper = new JPanel();
        projectPanelEditWrapper.add(editContentButton);
        projectPanelEditWrapper.add(saveEditButton);
        projectBody.add(projectPanelEditWrapper);
        projectPanelContent.add(projectBody, BorderLayout.CENTER);

        // projectPanelContent.add(projectPanelEditWrapper, BorderLayout.NORTH);
        projectPanelContent.add(projectStudent, BorderLayout.SOUTH);
        projectPanel.add(projectPanelContent);

        // Project Panel Buttons Setup
        JPanel projectPanelButtons = new JPanel();
        projectPanelButtons.add(assignStudentButton);
        projectPanel.add(projectPanelButtons, BorderLayout.SOUTH);
        assignStudentButton.setVisible(false);

        wrapper.add(tableView);
        wrapper.add(projectPanel);
    }

    public void setupLecterurProjectView(){
        saveEditButton.setVisible(false);
        editContentButton.setVisible(true);
        assignStudentButton.setVisible(true);
    }

    private void resizePanelTextsFont(){
        projectName.setFont(new Font(projectName.getFont().toString(), Font.BOLD, 35));
        projectLecturer.setFont(new Font(projectLecturer.getFont().toString(), Font.BOLD, 18));
        projectSpecialization.setFont(new Font(projectSpecialization.getFont().toString(), Font.BOLD, 13));
        projectStudent.setFont(new Font(projectStudent.getFont().toString(), Font.BOLD, 13));
    }

    private void setupProjectContentProperties(){
        projectContent.setEditable(false);
        projectContent.setLineWrap(true);
        projectContent.setOpaque(false);
    }

    private void disableAllPanelTexts(){
        projectLecturer.setEnabled(false);
        projectName.setEnabled(false);
        projectStudent.setEnabled(false);
        projectSpecialization.setEnabled(false);
        editContentButton.setEnabled(false);
        saveEditButton.setEnabled(false);
        activateButton.setEnabled(false);
        deactivateButton.setEnabled(false);
        assignStudentButton.setEnabled(false);
    }

    public void enablePanelButtons(){
        editContentButton.setEnabled(true);
        saveEditButton.setEnabled(true);
        activateButton.setEnabled(true);
        deactivateButton.setEnabled(true);
        assignStudentButton.setEnabled(true);
    }

    public void addAssignButtonListener(ActionListener assignButtonListener){
        assignStudentButton.addActionListener(assignButtonListener);
    }

    public void addEditContentButtonListener(ActionListener editContentButtonListener){
        editContentButton.addActionListener(editContentButtonListener);
    }

    public void addSaveEditButtonListener(ActionListener saveEditButtonListener){
        saveEditButton.addActionListener(saveEditButtonListener);
    }

    public void addActivateButtonListener(ActionListener activateButtonListener){
        activateButton.addActionListener(activateButtonListener);
    }

    public void addDectivateButtonListener(ActionListener deactivateButtonListener){
        deactivateButton.addActionListener(deactivateButtonListener);
    }

    public void addTableSelectionListener(ListSelectionListener tableSelectionListener){
        projectTable.getSelectionModel().addListSelectionListener(tableSelectionListener);
    }

    public void enableContentEditMode(){
        editContentButton.setVisible(false);
        saveEditButton.setVisible(true);
        projectContent.setEditable(true);
        projectContent.setOpaque(true);
    }

    public void disableContentEditMode(){
        saveEditButton.setVisible(false);
        editContentButton.setVisible(true);
        projectContent.setEditable(false);
        projectContent.setOpaque(false);
    }

    public String[] getColumnNames(){
        return columnNames;
    } 

    public JTable getProjectTable(){
        return projectTable;
    }

    public String getProjectContent(){
        return projectContent.getText();
    }

    public String getStudentID(){
        return JOptionPane.showInputDialog("Enter the Student's ID");
    }
    
    public void setProjectNameLabel(String name){
        projectName.setText(name);
        projectName.setEnabled(true);
    }

    public void setProjectLecturerLabel(String lecturer){
        projectLecturer.setText(lecturer);
        projectLecturer.setEnabled(true);
    }

    public void setProjectSpecializationLabel(String specialization){
        projectSpecialization.setText(specialization);
        projectSpecialization.setEnabled(true);
    }

    public void setProjectContentArea(String content){
        projectContent.setText(content);
    }

    public void setProjectStudentLabel(String studentName){
        projectStudent.setText("Assigned to: " + studentName);
        if (!(studentName.equals(""))){
            projectStudent.setEnabled(true);
        }
        else projectStudent.setEnabled(false);
    }
}
