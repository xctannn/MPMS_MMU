package View;

import java.awt.*;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class ProjectView {
    private static final String[] projectSpecializationOptions = {"---Select Here---", "Software Engineering", "Game Development", "Data Science" , "Cybersecurity", "Artificial Intelligence"};

    // Projects Table View Components
    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());
    private String[] columnNames = {"ID", "Name", "Lecturer"};

    // Project Detail View Components
    JPanel projectPanel = new JPanel(new BorderLayout());
    private JTable projectTable = new JTable(); 
    private JButton addProjectButton = new JButton("Add Project");
    private JTextField projectName = new JTextField("Project"); 
    private JLabel projectLecturer = new JLabel("By ");
    private JTextField projectSpecialization = new JTextField("Specialization: ");
    private JComboBox<String> projectSpecializationPicker = new JComboBox<>(projectSpecializationOptions);
    private JTextArea projectContent = new JTextArea();
    private JPanel projectBody = new JPanel();

    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton editContentButton = new JButton("Edit");
    private JButton saveEditButton = new JButton("Save");
    private JPanel projectPanelButtons = new JPanel();
    private JButton toggleProjectButton = new JButton("Activate");
    private JPanel assignButtonsWrapper = new JPanel();
    private JButton assignStudentButton = new JButton("Assign");
    private JButton unassignStudentButton = new JButton("Unassign");
    private JButton confirmAddProjectButton = new JButton("Confirm");
    private JButton deleteProjectButton = new JButton("Delete");


    public ProjectView(){
        wrapper.setLayout(new GridLayout(1,2));

        // Project Table View Initial Setup
         
        JPanel tableWrapper = new JPanel();
        tableWrapper.setLayout(new BoxLayout(tableWrapper, BoxLayout.PAGE_AXIS));
        tableWrapper.add(new JScrollPane(projectTable));
        tableWrapper.add(addProjectButton);
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableView.add(tableWrapper, BorderLayout.CENTER);
        
        // Disable all texts on Project Panel on startup
        disableAllPanelTexts();

        // Project Panel Title Setup
        JPanel projectPanelTitle = new JPanel();
        projectPanelTitle.setLayout(new BoxLayout(projectPanelTitle, BoxLayout.Y_AXIS));
        JScrollPane projectNameWrapper = new JScrollPane(projectName, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectNameWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectPanelTitle.add(projectNameWrapper);
        JPanel projectLecturerWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectLecturerWrapper.add(projectLecturer);
        projectPanelTitle.add(projectLecturerWrapper);
        JPanel projectSpecializationWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectSpecialization.setMinimumSize(new Dimension(250,20));
        projectSpecialization.setColumns(15);

        projectSpecializationPicker.setMaximumSize(new Dimension(200,20));
        projectSpecializationPicker.setVisible(false);
        projectSpecializationWrapper.add(projectSpecialization);
        projectSpecializationWrapper.add(projectSpecializationPicker);
        projectPanelTitle.add(projectSpecializationWrapper);
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);

        //Project Panel Content Setup
        JPanel projectPanelContent = new JPanel(new BorderLayout());
        projectBody.setLayout(new BoxLayout(projectBody, BoxLayout.PAGE_AXIS));
        projectBody.setBorder(BorderFactory.createEmptyBorder(30,20,20,30));
        JScrollPane projectContentWrapper = new JScrollPane(projectContent);
        projectBody.add(projectContentWrapper);
        
        
        projectPanelContent.add(projectBody, BorderLayout.CENTER);

        projectPanelContent.add(projectStudent, BorderLayout.SOUTH);
        projectPanel.add(projectPanelContent);

        // Project Panel Buttons Setup
        assignButtonsWrapper.add(assignStudentButton);
        assignButtonsWrapper.add(unassignStudentButton);
        projectPanelButtons.setLayout(new BoxLayout(projectPanelButtons, BoxLayout.LINE_AXIS));

        

        setupProjectPanelTextProperties();
        wrapper.add(tableView);
        wrapper.add(projectPanel);
    }

    public void defaultProjectView(Administrator adminUser){
        projectLecturer.setVisible(true);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(false);
        assignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(false);
        confirmAddProjectButton.setVisible(false);
        assignButtonsWrapper.setVisible(false);

        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(deleteProjectButton);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanel.add(projectPanelButtons, BorderLayout.SOUTH);
    }

    public void defaultProjectView(Lecturer lecturerUser){
        projectLecturer.setVisible(true);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(true);
        assignStudentButton.setVisible(true);
        unassignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(true);
        confirmAddProjectButton.setVisible(false);
        assignButtonsWrapper.setVisible(true);

        JPanel projectPanelEditWrapper = new JPanel();
        projectPanelEditWrapper.add(editContentButton);
        projectPanelEditWrapper.add(saveEditButton);
        projectBody.add(projectPanelEditWrapper);

        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(assignButtonsWrapper);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(toggleProjectButton);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(confirmAddProjectButton);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanel.add(projectPanelButtons, BorderLayout.SOUTH);
    }

    public void defaultProjectView(Student studentUser){
        addProjectButton.setVisible(false);
        projectLecturer.setVisible(true);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(false);
        assignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(false);
        confirmAddProjectButton.setVisible(false);
        assignButtonsWrapper.setVisible(false);
    }

    public void setupLecturerAddProjectPanel(){
        projectLecturer.setVisible(false);
        projectStudent.setEnabled(false);
        setProjectStudentLabel(null);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(false);
        assignStudentButton.setVisible(false);
        unassignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(false);
        confirmAddProjectButton.setVisible(true);
                

        emptyAllProjectPanelTexts();
        projectName.setEditable(true);
        projectName.setOpaque(true);
        projectName.setText("Project Name");
        projectContent.setEditable(true);
        projectContent.setOpaque(true);
        projectContent.setText("Enter project content here");
    }

    public void emptyAllProjectPanelTexts(){
        setProjectNameLabel("");
        projectSpecialization.setVisible(false);
        projectSpecializationPicker.setVisible(true);
        setProjectContentArea("");
    }



    private void resizePanelTextsFont(){
        projectName.setFont(new Font(projectName.getFont().toString(), Font.BOLD, 30));
        projectLecturer.setFont(new Font(projectLecturer.getFont().toString(), Font.BOLD, 18));
        projectSpecialization.setFont(new Font(projectSpecialization.getFont().toString(), Font.BOLD, 13));
        projectStudent.setFont(new Font(projectStudent.getFont().toString(), Font.BOLD, 13));
    }

    private void setupProjectPanelTextProperties(){
        resizePanelTextsFont();
        
        projectName.setOpaque(false);
        projectName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectName.setEditable(false);

        projectSpecialization.setOpaque(false);
        projectSpecialization.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectSpecialization.setEditable(false);
        
        projectContent.setEditable(false);
        projectContent.setLineWrap(true);
        projectContent.setOpaque(false);
    }

    public void disableAllPanelTexts(){
        projectLecturer.setEnabled(false);
        projectName.setEnabled(false);
        projectStudent.setEnabled(false);
        projectSpecialization.setEnabled(false);
        editContentButton.setEnabled(false);
        toggleProjectButton.setEnabled(false);
        assignStudentButton.setEnabled(false);
        unassignStudentButton.setEnabled(false);
        deleteProjectButton.setEnabled(false);
    }

    public void enablePanelButtons(){
        editContentButton.setEnabled(true);
        toggleProjectButton.setEnabled(true);
        assignStudentButton.setEnabled(true);
        unassignStudentButton.setEnabled(true);
        deleteProjectButton.setEnabled(true);
    }

    public void enableContentEditMode(){
        editContentButton.setVisible(false);
        saveEditButton.setVisible(true);

        projectName.setEditable(true);
        projectName.setOpaque(true);

        projectSpecializationPicker.setVisible(true);
        projectSpecialization.setVisible(false);
        projectSpecialization.setOpaque(true);

        projectContent.setEditable(true);
        projectContent.setOpaque(true);

        assignButtonsWrapper.setVisible(false);
        toggleProjectButton.setVisible(false);
    }

    public void disableContentEditMode(){
        saveEditButton.setVisible(false);
        editContentButton.setVisible(true);

        projectName.setEditable(false);
        projectName.setOpaque(false);

        projectSpecializationPicker.setVisible(false);
        projectSpecialization.setVisible(true);
        projectSpecialization.setOpaque(false);

        projectLecturer.setVisible(true);

        projectContent.setEditable(false);
        projectContent.setOpaque(false);

        assignButtonsWrapper.setVisible(true);
        toggleProjectButton.setVisible(true);
        confirmAddProjectButton.setVisible(false);
    }

    public void resetSpecializationPicker(){
        projectSpecializationPicker.setSelectedItem(0);
    }

    public static void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

    }

    public String getStudentToAssign(ArrayList<String> availableStudentIds){
        int arrayLength = availableStudentIds.size();
        String[] studentOptions = new String[arrayLength];
        for(int i = 0; i < studentOptions.length; i++){
            studentOptions[i] = availableStudentIds.get(i);
        }

        Object selected =  JOptionPane.showInputDialog(null,
                                                        "Which student would you like to assign the project to?",
                                                        "Please select the student to assign the project", 
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        studentOptions, 
                                                        studentOptions[0]);
        
        if (selected == null){
            return "";
        }else return selected.toString();
    }

    public boolean getUnassignConfirmation(String message){
        int result = JOptionPane.showConfirmDialog(null, message, "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) return true;
        else return false;
    }

    public void enableAssign(){
        unassignStudentButton.setVisible(false);
        assignStudentButton.setVisible(true);
    }

    public void enableUnassign(){
        unassignStudentButton.setVisible(true);
        assignStudentButton.setVisible(false);
    }

    public int getSelectedRow(){
        return projectTable.getSelectedRow();
    }

    public String[] getColumnNames(){
        return columnNames;
    } 

    public JTable getProjectTable(){
        return projectTable;
    }

    public String getProjectName(){
        return projectName.getText();
    }

    public JComboBox<String> getSpecializationPicker(){
        return projectSpecializationPicker;
    }

    public String getSelectedSpecialization(){
        return projectSpecializationPicker.getSelectedItem().toString();
    }

    public String getProjectSpecialization(){
        return projectSpecialization.getText();
    }

    public String getProjectContent(){
        return projectContent.getText();
    }
    
    public void setProjectNameLabel(String name){
        projectName.setText(name);
        projectName.setEnabled(true);
    }

    public void setProjectLecturerLabel(String lecturer){
        projectLecturer.setText("By: " + lecturer);
        projectLecturer.setEnabled(true);
    }

    public void setProjectSpecializationLabel(String specialization){
        projectSpecialization.setText(specialization);
        projectSpecialization.setEnabled(true);
    }

    public void setProjectContentArea(String content){
        projectContent.setText(content);
    }

    public void setToggleButtonText(boolean isActive){
        if (isActive){
            toggleProjectButton.setText("Dectivate");
        }
        else if (!(isActive)){
            toggleProjectButton.setText("Activate");
        }
    }

    public void setAssignMode(boolean isAssigned){
        if (isAssigned){
            enableUnassign();
        }
        else if (!(isAssigned)){
            enableAssign();
        }
    }

    public void setProjectStudentLabel(String studentName){
        if (!(studentName == null)){
            projectStudent.setText("Assigned to: " + studentName);
            projectStudent.setEnabled(true);
        }
        else {
            projectStudent.setText("Assigned to: ");
            projectStudent.setEnabled(false);
        }
    }

    public void addAddProjectButtonListerner(ActionListener addProjectButtonListener){
        addProjectButton.addActionListener(addProjectButtonListener);
    } 

    public void addConfirmAddProjectButtonListerner(ActionListener confirmAddProjectButtonListener){
        confirmAddProjectButton.addActionListener(confirmAddProjectButtonListener);
    } 

    public void addProjectSpecializationPickerListerner(ActionListener projectSpecializationPickerListener){
        projectSpecializationPicker.addActionListener(projectSpecializationPickerListener);
    }

    public void addEditContentButtonListener(ActionListener editContentButtonListener){
        editContentButton.addActionListener(editContentButtonListener);
    }

    public void addSaveEditButtonListener(ActionListener saveEditButtonListener){
        saveEditButton.addActionListener(saveEditButtonListener);
    }

    public void addToggleProjectButtonListener(ActionListener toggleProjectButtonListener){
        toggleProjectButton.addActionListener(toggleProjectButtonListener);
    }

    public void addAssignButtonListener(ActionListener assignButtonListener){
        assignStudentButton.addActionListener(assignButtonListener);
    }

    public void addUnassignButtonListener(ActionListener unassignButtonListener){
        unassignStudentButton.addActionListener(unassignButtonListener);
    }

    public void addDeleteProjectButtonListener(ActionListener deleteProjectButtonListener){
        deleteProjectButton.addActionListener(deleteProjectButtonListener);
    }

    public void addTableSelectionListener(ListSelectionListener tableSelectionListener){
        projectTable.getSelectionModel().addListSelectionListener(tableSelectionListener);
    }

}
