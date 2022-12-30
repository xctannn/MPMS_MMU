package View;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

    // Projects Table View Components
    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());
    private String[] columnNames = {"ID", "Name", "Lecturer"};

    // Project Detail View Components
    JPanel projectPanel = new JPanel(new BorderLayout());
    private JLabel projectName = new JLabel("Project"); 
    private JLabel projectLecturer = new JLabel("By ");
    private JLabel projectSpecialization = new JLabel("Specialization: ");
    private JTextArea projectContent = (new JTextArea(25, 50));
    private JLabel projectStudent = new JLabel("Assigned to: ");
    private JButton assignStudentButton = new JButton("Assign");
    private JTable projectTable = new JTable(); 

    // Comment Section Components
    private JLabel commentTitleLabel = new JLabel("Class Comments: ");
    private JButton submitCommentButton = new JButton("Submit");
    private JTable commentTable = new JTable();

    public ProjectView(){
        wrapper.setLayout(new GridLayout(1,2));

        // Project Table View Initial Setup 
        JPanel tableWrapper = new JPanel();
        tableWrapper.add(new JScrollPane(projectTable));
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableView.add(tableWrapper, BorderLayout.CENTER);
        
        // Disable all texts on Project Panel on startup
        disableAllTextsInPanel();

        // Project Panel Title Setup
        resizePanelTextsFont();
        JPanel projectPanelTitle = new JPanel();
        projectPanelTitle.setLayout(new BoxLayout(projectPanelTitle, BoxLayout.PAGE_AXIS));
        projectPanelTitle.add(projectName);
        projectPanelTitle.add(projectLecturer);
        projectPanelTitle.add(projectSpecialization);
        projectPanelTitle.add(commentTitleLabel);
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);

        //Project Panel Content Setup
        JPanel projectPanelContent = new JPanel(new BorderLayout());
        JPanel projectContentWrapper = new JPanel();
        projectContentWrapper.setBorder( BorderFactory.createEmptyBorder(30,20,20,20));
        projectContentWrapper.add(projectContent);
        setupProjectContentProperties();
        projectPanelContent.add(projectContentWrapper, BorderLayout.CENTER);
        projectPanelContent.add(projectStudent, BorderLayout.SOUTH);
        projectPanel.add(projectPanelContent);

        //Past Comments Table
        JPanel commentTablePanel = new JPanel();
        commentTablePanel.add(new JScrollPane(commentTable));
        projectPanel.add(commentTablePanel);

        //Comment Section Text Box Setup
        // JPanel commentSectionPanel = new JPanel();
        // JTextField textBox = new JTextField();
        // commentSectionPanel.setLayout(new BoxLayout(commentSectionPanel,BoxLayout.X_AXIS));
        // commentSectionPanel.add(textBox, Component.BOTTOM_ALIGNMENT);
        // commentSectionPanel.add(Box.createHorizontalStrut(15));
        // commentSectionPanel.add(submitCommentButton, Component.RIGHT_ALIGNMENT);
        // submitCommentButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        // commentSectionPanel.add(Box.createHorizontalGlue());
        // projectPanel.add(commentSectionPanel);
        // projectPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        
        // Project Panel Buttons Setup
        JPanel projectPanelButtons = new JPanel();
        projectPanelButtons.add(assignStudentButton);
        projectPanel.add(projectPanelButtons, BorderLayout.SOUTH);
        assignStudentButton.setVisible(true);

        wrapper.add(tableView);
        wrapper.add(projectPanel);
    }

    private void resizePanelTextsFont(){
        projectName.setFont(new Font(projectName.getFont().toString(), Font.BOLD, 35));
        projectLecturer.setFont(new Font(projectLecturer.getFont().toString(), Font.BOLD, 18));
        projectSpecialization.setFont(new Font(projectSpecialization.getFont().toString(), Font.BOLD, 13));
        projectStudent.setFont(new Font(projectStudent.getFont().toString(), Font.BOLD, 13));
        //Comment Section
        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));
    }

    private void setupProjectContentProperties(){
        projectContent.setEditable(false);
        projectContent.setLineWrap(true);
        projectContent.setOpaque(false);
    }

    private void disableAllTextsInPanel(){
        projectLecturer.setEnabled(false);
        projectName.setEnabled(false);
        projectStudent.setEnabled(false);
        projectSpecialization.setEnabled(false);
        assignStudentButton.setEnabled(false);
    }

    public void addAssignButtonListener(ActionListener assignButtonListener){
        assignStudentButton.addActionListener(assignButtonListener);
    }

    public void addTableSelectionListener(ListSelectionListener tableSelectionListener){
        projectTable.getSelectionModel().addListSelectionListener(tableSelectionListener);
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
