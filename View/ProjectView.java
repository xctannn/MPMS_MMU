package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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

import Model.Administrator;
import Model.Lecturer;
import Model.Student;

/*
 * Purpose: For user to view a list of projects and its related details 
 */
public class ProjectView extends JPanel{
    private static final String[] projectSpecializationOptions = {"---Select Here---", "Software Engineering", "Game Development", "Data Science" , "Cybersecurity", "Artificial Intelligence"};

    // Projects Table View Components
    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());
    private String[] columnNames = {"ID", "Name", "Lecturer"};
    private JTable projectTable = new JTable(); 
    private JButton logoutButton = new JButton("Log out");
    private JButton registerAccountButton = new JButton("Register Account");
    private JButton lecturerAddProjectButton = new JButton("Add Project");
    private JButton adminAddProjectButton = new JButton("Add Project");
    private JButton filterProjectsButton = new JButton("Filter Projects");

    // Project Detail View Components
    JPanel projectPanel = new JPanel(new BorderLayout());
    private JTextField projectName = new JTextField("Project"); 
    private JTextField projectLecturer = new JTextField("By ");
    private JComboBox<String> projectLecturerSelector = new JComboBox<>();
    private JTextField projectSpecialization = new JTextField("Specialization: ");
    private JComboBox<String> projectSpecializationSelector = new JComboBox<>(projectSpecializationOptions);

    private JPanel projectBody = new JPanel();
    private JTextArea projectContent = new JTextArea();
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton editContentButton = new JButton("Edit");
    private JButton saveEditButton = new JButton("Save");

    private JButton toggleProjectButton = new JButton("Activate");
    private JPanel assignButtonsWrapper = new JPanel();
    private JButton assignStudentButton = new JButton("Assign");
    private JButton unassignStudentButton = new JButton("Unassign");
    private JButton confirmLecturerAddProjectButton = new JButton("Confirm");
    private JButton confirmAdminAddProjectButton = new JButton("Confirm");
    private JButton deleteProjectButton = new JButton("Delete");
    private JButton projectCommentsButton = new JButton("Comments");

    /*
     * Yaw Boon Zhe
     * Constructor to build view for Administrator users
     */
    public ProjectView(Administrator adminUser){
        this.setLayout(new GridLayout(1,2));

        // Project Table View Setup
        tableView.add(adminProjectTable(), BorderLayout.CENTER);

        // Project Panel Setup
        projectPanel.add(projectPanelTitle(), BorderLayout.NORTH);
        projectPanel.add(projectPanelContent());
        projectPanel.add(adminProjectPanelButtons(), BorderLayout.SOUTH);
        
        setupProjectPanelTextProperties();
        disableAllPanelElements();
        confirmAdminAddProjectButton.setVisible(false);
        this.add(tableView);
        this.add(projectPanel);
    }

    /*
     * Yaw Boon Zhe
     * Constructor to build view for Lecturer users
     */
    public ProjectView(Lecturer lecturerUser){
        this.setLayout(new GridLayout(1,2));

        // Project Table View Setup
        tableView.add(lecturerProjectTable(), BorderLayout.CENTER);

        // Project Panel Setup
        projectPanel.add(projectPanelTitle(), BorderLayout.NORTH);
        projectPanel.add(projectPanelContent());
        projectBody.add(editButton());
        projectPanel.add(lecturerProjectPanelButtons(), BorderLayout.SOUTH);

        // Disable all texts on Project Panel on startup
        setupProjectPanelTextProperties();
        disableAllPanelElements();
        confirmLecturerAddProjectButton.setVisible(false);
        saveEditButton.setVisible(false);
        unassignStudentButton.setVisible(false);
        this.add(tableView);
        this.add(projectPanel);
    }

    /*
     * Yaw Boon Zhe
     * Constructor to build view for Student users
     */
    public ProjectView(Student studentUser){
        this.setLayout(new GridLayout(1,2));

        // Project Table View Setup
        tableView.add(studentProjectTable(), BorderLayout.CENTER);

        // Project Panel Setup
        projectPanel.add(projectPanelTitle(), BorderLayout.NORTH);
        projectPanel.add(projectPanelContent());
        projectPanel.add(studentProjectPanelButtons(), BorderLayout.SOUTH);
        
        setupProjectPanelTextProperties();
        disableAllPanelElements();
        this.add(tableView);
        this.add(projectPanel);
    }

    
    // Yaw Boon Zhe    
    private JPanel adminProjectTable(){
        JPanel tableWrapper = new JPanel();
        tableWrapper.setLayout(new BoxLayout(tableWrapper, BoxLayout.PAGE_AXIS));
        JPanel accountButtons = logoutButton();
        accountButtons.add(registerAccountButton);
        
        tableWrapper.add(accountButtons);
        tableWrapper.add(new JScrollPane(projectTable));
        tableWrapper.add(adminProjectTableButtons());
        
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return tableWrapper;
    }

    
    // Yaw Boon Zhe    
    private JPanel lecturerProjectTable(){
        JPanel tableWrapper = new JPanel();
        tableWrapper.setLayout(new BoxLayout(tableWrapper, BoxLayout.PAGE_AXIS));

        tableWrapper.add(logoutButton());
        tableWrapper.add(new JScrollPane(projectTable));
        tableWrapper.add(lecturerProjectTableButtons());

        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        return tableWrapper;
    }

    
    // Yaw Boon Zhe    
    private JPanel studentProjectTable(){
        JPanel tableWrapper = new JPanel();
        tableWrapper.setLayout(new BoxLayout(tableWrapper, BoxLayout.PAGE_AXIS));

        tableWrapper.add(logoutButton());
        tableWrapper.add(new JScrollPane(projectTable));
        
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return tableWrapper;
    }

    
    // Yaw Boon Zhe    
    private JPanel logoutButton(){
        JPanel logoutButtonWrapper = new JPanel();
        logoutButtonWrapper.setLayout(new BoxLayout(logoutButtonWrapper, BoxLayout.LINE_AXIS));
        logoutButtonWrapper.add(logoutButton);
        logoutButtonWrapper.add(Box.createHorizontalGlue());

        return logoutButtonWrapper;
    }
    
    /*
     * Yaw Boon Zhe
     * Purpose: add buttons to add project and generate report at buttom of projectTable
     */
    private JPanel adminProjectTableButtons(){
        JPanel projectTableButtons = new JPanel();
        projectTableButtons.setLayout(new BoxLayout(projectTableButtons, BoxLayout.LINE_AXIS));

        projectTableButtons.add(adminAddProjectButton);
        projectTableButtons.add(Box.createHorizontalGlue());
        projectTableButtons.add(filterProjectsButton);
        return projectTableButtons;
    }

    /*
     * Yaw Boon Zhe
     * Purpose: add buttons to add project at buttom of projectTable
     */
    private JPanel lecturerProjectTableButtons(){
        JPanel projectTableButtons = new JPanel();
        projectTableButtons.setLayout(new BoxLayout(projectTableButtons, BoxLayout.LINE_AXIS));

        projectTableButtons.add(lecturerAddProjectButton);
        projectTableButtons.add(Box.createHorizontalGlue());
        return projectTableButtons;
    }
    
    
    // Yaw Boon Zhe    
    private JPanel projectPanelTitle(){
        JPanel projectPanelTitle = new JPanel();
        projectPanelTitle.setLayout(new BoxLayout(projectPanelTitle, BoxLayout.Y_AXIS));

        JScrollPane projectNameWrapper = new JScrollPane(projectName, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectNameWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectPanelTitle.add(projectNameWrapper);

        JPanel projectLecturerWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectLecturer.setColumns(28);
        projectLecturerSelector.setMaximumSize(new Dimension(200,20));
        projectLecturerSelector.setVisible(false);
        projectLecturerWrapper.add(projectLecturer);
        projectLecturerWrapper.add(projectLecturerSelector);
        projectPanelTitle.add(projectLecturerWrapper);

        JPanel projectSpecializationWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectSpecialization.setColumns(15);
        projectSpecializationSelector.setMaximumSize(new Dimension(200,20));
        projectSpecializationSelector.setVisible(false);
        projectSpecializationWrapper.add(projectSpecialization);
        projectSpecializationWrapper.add(projectSpecializationSelector);
        projectPanelTitle.add(projectSpecializationWrapper);

        return projectPanelTitle;
    }

    
    // Yaw Boon Zhe    
    private JPanel projectPanelContent(){
        projectBody.setLayout(new BoxLayout(projectBody, BoxLayout.PAGE_AXIS));
        projectBody.setBorder(BorderFactory.createEmptyBorder(30,20,20,30));
        JScrollPane projectContentWrapper = new JScrollPane(projectContent);
        projectBody.add(projectContentWrapper);

        JPanel projectPanelContent = new JPanel(new BorderLayout());
        projectPanelContent.add(projectBody, BorderLayout.CENTER);
        projectPanelContent.add(projectStudent, BorderLayout.SOUTH);

        return projectPanelContent;
    }

    
    // Tan Xiao Chin    
    private JPanel editButton(){
        JPanel projectPanelEditWrapper = new JPanel();
        projectPanelEditWrapper.add(editContentButton);
        projectPanelEditWrapper.add(saveEditButton); 

        return projectPanelEditWrapper;
    }

    
    // Tan Xiao Chin    
    private JPanel assignButton(){
        assignButtonsWrapper.add(assignStudentButton);
        assignButtonsWrapper.add(unassignStudentButton);

        return assignButtonsWrapper;
    }

    
    /*
     * Yaw Boon Zhe
     * Purpose: add buttons to delete project, confirm add project, and view comments at buttom of projectPanel
     */  
    private JPanel adminProjectPanelButtons(){
        JPanel projectPanelButtons = new JPanel();
        projectPanelButtons.setLayout(new BoxLayout(projectPanelButtons, BoxLayout.LINE_AXIS));

        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(deleteProjectButton);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(confirmAdminAddProjectButton);
        projectPanelButtons.add(projectCommentsButton);
        projectPanelButtons.add(Box.createHorizontalGlue());

        return projectPanelButtons;
    }

    
    /*
     * Yaw Boon Zhe
     * Purpose: add buttons to assign student to project,toggle Project, confirm add project, and view comments 
     *          at buttom of projectPanel
     */   
    private JPanel lecturerProjectPanelButtons(){
        JPanel projectPanelButtons = new JPanel();
        projectPanelButtons.setLayout(new BoxLayout(projectPanelButtons, BoxLayout.LINE_AXIS));
        projectPanelButtons.add(assignButton());
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(toggleProjectButton);
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(confirmLecturerAddProjectButton);
        projectPanelButtons.add(projectCommentsButton);
        projectPanelButtons.add(Box.createHorizontalGlue());

        return projectPanelButtons;
    }

    
    /*
     * Yaw Boon Zhe
     * Purpose: add buttons to view comments at buttom of projectPanel
     */   
    private JPanel studentProjectPanelButtons(){
        JPanel projectPanelButtons = new JPanel();
        projectPanelButtons.setLayout(new BoxLayout(projectPanelButtons, BoxLayout.LINE_AXIS));
        projectPanelButtons.add(Box.createHorizontalGlue());
        projectPanelButtons.add(projectCommentsButton);
        projectPanelButtons.add(Box.createHorizontalGlue());

        return projectPanelButtons;
    }

    
    /*
     * Yaw Boon Zhe
     * Purpose: Show certain components, while hiding other components to setup a view for lecturer to add projects
     */ 
    public void setupLecturerAddProjectPanel(){
        projectLecturer.setVisible(false);
        projectStudent.setEnabled(false);
        setProjectStudentLabel(null);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(false);
        assignStudentButton.setVisible(false);
        unassignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(false);
        confirmLecturerAddProjectButton.setVisible(true);
        projectCommentsButton.setVisible(false);
        
        emptyAllProjectPanelTexts();
        projectName.setEditable(true);
        projectName.setOpaque(true);
        projectName.setText("Project Name");
        projectContent.setEditable(true);
        projectContent.setOpaque(true);
        projectContent.setText("Enter project content here");
    }

    /*
     * Yaw Boon Zhe
     * Purpose: Show certain components, while hiding other components to setup a view for Administrator to add projects
     */ 
    public void setupAdminAddProjectPanel(){
        projectLecturer.setVisible(false);
        projectStudent.setEnabled(false);
        setProjectStudentLabel(null);
        saveEditButton.setVisible(false);
        editContentButton.setVisible(false);
        assignStudentButton.setVisible(false);
        unassignStudentButton.setVisible(false);
        toggleProjectButton.setVisible(false);
        deleteProjectButton.setVisible(false);
        confirmAdminAddProjectButton.setVisible(true);
        projectCommentsButton.setVisible(false);
        
        emptyAllProjectPanelTexts();
        projectName.setEditable(true);
        projectName.setOpaque(true);
        projectName.setText("Project Name");
        projectLecturer.setVisible(false);
        projectLecturerSelector.setVisible(true);
        projectContent.setEditable(true);
        projectContent.setOpaque(true);
        projectContent.setText("Enter project content here");
    }

    // Yaw Boon Zhe
    public void emptyAllProjectPanelTexts(){
        setProjectNameLabel("");
        projectSpecialization.setVisible(false);
        projectSpecializationSelector.setVisible(true);
        setProjectContentArea("");
    }

    // Yaw Boon Zhe
    private void resizePanelTextsFont(){
        projectName.setFont(new Font(projectName.getFont().toString(), Font.BOLD, 30));
        projectLecturer.setFont(new Font(projectLecturer.getFont().toString(), Font.BOLD, 18));
        projectSpecialization.setFont(new Font(projectSpecialization.getFont().toString(), Font.BOLD, 13));
        projectStudent.setFont(new Font(projectStudent.getFont().toString(), Font.BOLD, 13));
    }

    // Yaw Boon Zhe
    private void setupProjectPanelTextProperties(){
        resizePanelTextsFont();

        projectName.setOpaque(false);
        projectName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectName.setEditable(false);

        projectSpecialization.setOpaque(false);
        projectSpecialization.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectSpecialization.setEditable(false);

        projectLecturer.setOpaque(false);
        projectLecturer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        projectLecturer.setEditable(false);
        
        projectContent.setEditable(false);
        projectContent.setLineWrap(true);
        projectContent.setOpaque(false);
    }

    // Yaw Boon Zhe
    public void disableAllPanelElements(){
        projectLecturer.setEnabled(false);
        projectName.setEnabled(false);
        projectStudent.setEnabled(false);
        projectSpecialization.setEnabled(false);
        editContentButton.setEnabled(false);
        toggleProjectButton.setEnabled(false);
        assignStudentButton.setEnabled(false);
        unassignStudentButton.setEnabled(false);
        deleteProjectButton.setEnabled(false);
        projectCommentsButton.setEnabled(false);
    }

    // Yaw Boon Zhe
    public void enablePanelButtons(){
        editContentButton.setEnabled(true);
        toggleProjectButton.setEnabled(true);
        assignStudentButton.setEnabled(true);
        unassignStudentButton.setEnabled(true);
        deleteProjectButton.setEnabled(true);
        projectCommentsButton.setEnabled(true);
    }

    // Tan Xiao Chin
    public void enableContentEditMode(){
        editContentButton.setVisible(false);
        saveEditButton.setVisible(true);

        projectName.setEditable(true);
        projectName.setOpaque(true);

        projectSpecializationSelector.setVisible(true);
        projectSpecialization.setVisible(false);
        projectSpecialization.setOpaque(true);

        projectContent.setEditable(true);
        projectContent.setOpaque(true);

        assignButtonsWrapper.setVisible(false);
        toggleProjectButton.setVisible(false);
        projectCommentsButton.setVisible(false);
    }

    // Tan Xiao Chin
    public void disableContentEditMode(){
        saveEditButton.setVisible(false);
        editContentButton.setVisible(true);
        projectLecturerSelector.setVisible(false);

        projectName.setEditable(false);
        projectName.setOpaque(false);

        projectSpecializationSelector.setVisible(false);
        projectSpecialization.setVisible(true);
        projectSpecialization.setOpaque(false);

        projectLecturer.setVisible(true);

        projectContent.setEditable(false);
        projectContent.setOpaque(false);

        assignButtonsWrapper.setVisible(true);
        toggleProjectButton.setVisible(true);
        deleteProjectButton.setVisible(true);
        confirmLecturerAddProjectButton.setVisible(false);
        confirmAdminAddProjectButton.setVisible(false);
        projectCommentsButton.setVisible(true);
    }

    // Yaw Boon Zhe
    public void resetSpecializationSelector(){
        projectSpecializationSelector.setSelectedItem(0);
    }

    // Yaw Boon Zhe
    public static void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * Yaw Boon Zhe
     * populate comboBox with IDs of all the lectuer, for the administrator to choose when creatng a new project
     */
    public void populateLecturerPicker(ArrayList<String> lecturerIds){
        int arrayLength = lecturerIds.size();
        String[] lecturerOptions = new String[arrayLength];

        for(int i = 0; i < arrayLength; i++){
            lecturerOptions[i] = lecturerIds.get(i);
        }
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(lecturerOptions);
        projectLecturerSelector.setModel(comboBoxModel);
    }

    /*
     * Tan Xiao Chin
     * populate comboBox with eligible students, for the lectuer to choose when assigning a student
     */
    public String getStudentToAssign(ArrayList<String> availableStudentIds){
        Object[] studentOptions = availableStudentIds.toArray();

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

    // Tan Xiao Chin
    public boolean getUnassignConfirmation(String message){
        int result = JOptionPane.showConfirmDialog(null, message, "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) return true;
        else return false;
    }

    // Tan Xiao Chin
    public void enableAssign(){
        unassignStudentButton.setVisible(false);
        assignStudentButton.setVisible(true);
    }

    // Tan Xiao Chin
    public void enableUnassign(){
        unassignStudentButton.setVisible(true);
        assignStudentButton.setVisible(false);
    }

    // GETTERS AND SETTERS
    // Yaw Boon Zhe
    public int getSelectedRow(){
        return projectTable.getSelectedRow();
    }

    // Yaw Boon Zhe
    public String[] getColumnNames(){
        return columnNames;
    } 

    // Yaw Boon Zhe
    public JTable getProjectTable(){
        return projectTable;
    }

    // Yaw Boon Zhe
    public String getProjectName(){
        return projectName.getText();
    }

    // Yaw Boon Zhe
    public JComboBox<String> getLecturerSelector(){
        return projectLecturerSelector;
    }

    // Yaw Boon Zhe
    public String getSelectedLecturer(){
        return projectLecturerSelector.getSelectedItem().toString();
    }

    // Yaw Boon Zhe
    public JComboBox<String> getSpecializationSelector(){
        return projectSpecializationSelector;
    }

    // Yaw Boon Zhe
    public String getSelectedSpecialization(){
        return projectSpecializationSelector.getSelectedItem().toString();
    }

    // Yaw Boon Zhe
    public String getProjectSpecialization(){
        return projectSpecialization.getText();
    }

    // Yaw Boon Zhe
    public String getProjectContent(){
        return projectContent.getText();
    }
    
    // Yaw Boon Zhe
    public void setProjectNameLabel(String name){
        projectName.setText(name);
        projectName.setEnabled(true);
    }

    // Yaw Boon Zhe
    public void setProjectLecturerLabel(String lecturer){
        projectLecturer.setText("By: " + lecturer);
        projectLecturer.setEnabled(true);
    }

    // Yaw Boon Zhe
    public void setProjectSpecializationLabel(String specialization){
        projectSpecialization.setText(specialization);
        projectSpecialization.setEnabled(true);
    }

    // Yaw Boon Zhe
    public void setProjectContentArea(String content){
        projectContent.setText(content);
    }

    // tan Xiao Chin
    public void setToggleButtonText(boolean isActive){
        if (isActive){
            toggleProjectButton.setText("Dectivate");
        }
        else if (!(isActive)){
            toggleProjectButton.setText("Activate");
        }
    }

    // Tan Xiao Chin
    public void setAssignMode(boolean isAssigned){
        if (isAssigned){
            enableUnassign();
        }
        else if (!(isAssigned)){
            enableAssign();
        }
    }

    // Yaw Boon Zhe
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

    // Add Listeners to Buttons methods
    // Yaw Boon Zhe
    public void addLogoutButtonListener(ActionListener logoutButtonListener){
        logoutButton.addActionListener(logoutButtonListener);
    }

    // Yaw Boon Zhe
    public void addRegisterAccountButtonListener(ActionListener registerAccountButtonListener){
        registerAccountButton.addActionListener(registerAccountButtonListener);
    }

    // Yaw Boon Zhe
    public void addLecturerAddProjectButtonListener(ActionListener lecturerAddProjectButtonListener){
        lecturerAddProjectButton.addActionListener(lecturerAddProjectButtonListener);
    } 

    // Yaw Boon Zhe
    public void addProjectLecturerSelectorListener(ActionListener projectLecturerSelectorListener){
        projectLecturerSelector.addActionListener(projectLecturerSelectorListener);
    }

    // Yaw Boon Zhe
    public void addAdminAddProjectButtonListener(ActionListener adminAddProjectButtonListener){
        adminAddProjectButton.addActionListener(adminAddProjectButtonListener);
    } 

    // Yaw Boon Zhe
    public void addFilterProjectsButtonListener(ActionListener filterProjectsButtonListener){
        filterProjectsButton.addActionListener(filterProjectsButtonListener);
    }

    // Yaw Boon Zhe
    public void addConfirmLecturerAddProjectButtonListener(ActionListener confirmLecturerAddProjectButtonListener){
        confirmLecturerAddProjectButton.addActionListener(confirmLecturerAddProjectButtonListener);
    } 

    // Yaw Boon Zhe
    public void addConfirmAdminAddProjectButtonListener(ActionListener confirmAdminAddProjectButtonListener){
        confirmAdminAddProjectButton.addActionListener(confirmAdminAddProjectButtonListener);
    } 

    // Yaw Boon Zhe
    public void addProjectSpecializationSelectorListener(ActionListener projectSpecializationSelectorListener){
        projectSpecializationSelector.addActionListener(projectSpecializationSelectorListener);
    }

    // Tan Xiao Chin
    public void addEditContentButtonListener(ActionListener editContentButtonListener){
        editContentButton.addActionListener(editContentButtonListener);
    }

    // Tan Xiao Chin
    public void addSaveEditButtonListener(ActionListener saveEditButtonListener){
        saveEditButton.addActionListener(saveEditButtonListener);
    }

    // Tan Xiao Chin
    public void addToggleProjectButtonListener(ActionListener toggleProjectButtonListener){
        toggleProjectButton.addActionListener(toggleProjectButtonListener);
    }

    // Tan Xiao Chin
    public void addAssignButtonListener(ActionListener assignButtonListener){
        assignStudentButton.addActionListener(assignButtonListener);
    }

    // Tan Xiao Chin
    public void addUnassignButtonListener(ActionListener unassignButtonListener){
        unassignStudentButton.addActionListener(unassignButtonListener);
    }

    // Yaw Boon Zhe
    public void addDeleteProjectButtonListener(ActionListener deleteProjectButtonListener){
        deleteProjectButton.addActionListener(deleteProjectButtonListener);
    }

    // Yaw Boon Zhe
    public void addProjectCommentsButtonListener(ActionListener projectCommentsButtonListener){
        projectCommentsButton.addActionListener(projectCommentsButtonListener);
    }

    // Yaw Boon Zhe
    public void addTableSelectionListener(ListSelectionListener tableSelectionListener){
        projectTable.getSelectionModel().addListSelectionListener(tableSelectionListener);
    }
}
