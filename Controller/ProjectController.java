package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Model.Administrator;
import Model.Lecturer;
import Model.LecturerList;
import Model.Project;
import Model.ProjectList;
import Model.Student;
import Model.StudentList;
import Model.User;
import View.ProjectView;

public class ProjectController {
    private User user;
    private Project projectModel;
    private ProjectList projectList = new ProjectList();
    private ArrayList<Project> filteredProjectList;
    private ProjectView projectView;
    private LecturerList lecturerList = new LecturerList();
    private StudentList studentList = new StudentList();

    public ProjectController(Administrator user, ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = new ArrayList<>(projectList.getProjects());

        projectView.defaultProjectView(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());

        populateTable();
    }

    public ProjectController(Lecturer user,ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = projectList.getFilteredProjects(user);

        projectView.defaultProjectView(user);
        projectView.addTableSelectionListener(new TableSelectionListener());
        projectView.addAddProjectButtonListerner(new addProjectButtonListener());
        projectView.addConfirmAddProjectButtonListerner(new confirmAddProjectButtonListener());
        projectView.addProjectSpecializationPickerListerner(new projectSpecializationPickerListener());
        projectView.addEditContentButtonListener(new EditButtonListener());
        projectView.addSaveEditButtonListener(new SaveEditButtonListener());
        projectView.addToggleProjectButtonListener(new ToggleProjectButtonListener());
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addUnassignButtonListener(new UnassignButtonListener());
        
        populateTable();
    }

    public ProjectController(Student user, ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = projectList.getFilteredProjects(user);


        projectView.defaultProjectView(user);
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());

        populateTable();
    }


    public void populateModelView(){
        String projectName = projectModel.getName();
        String projectLecturerName = projectModel.getLecturerName();
        String projectSpecialization = projectModel.getSpecialization();
        String projectContent = projectModel.getContent();
        boolean isProjectActive = projectModel.getIsActive();
        boolean isProjectAssigned = projectModel.getIsAssigned();
        String projectStudentName = projectModel.getStudentAssignedName();
        
        // populating project panel values
        projectView.setProjectNameLabel(projectName);
        projectView.setProjectLecturerLabel(projectLecturerName);
        projectView.setProjectSpecializationLabel(projectSpecialization);
        projectView.setProjectContentArea(projectContent);
        projectView.setToggleButtonText(isProjectActive);
        projectView.setProjectStudentLabel(projectStudentName);
        projectView.setAssignMode(isProjectAssigned);
    }

    // public void populateTable(){
    //     // TableColumnModel columnModel = new TableColumnModel();
    // }

    public void populateTable(){
        DefaultTableModel projectTableModel = new DefaultTableModel(projectView.getColumnNames(), 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        // constructing table models
        for (int i = 0; i < filteredProjectList.size(); i++){
            Project project = filteredProjectList.get(i);
            String projectID = project.getId();
            String projectName = project.getName();
            String projectLecturerName = project.getLecturerName();
            Object[] row = {projectID, projectName, projectLecturerName};

            projectTableModel.addRow(row);
        }
        JTable viewTable = projectView.getProjectTable();
        viewTable.setModel(projectTableModel);

        // Configure table column widths
        TableColumnModel columnModel = viewTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
    }

    public void addNewProjectToTable(Project newProject){
        JTable projectTable = projectView.getProjectTable();
        DefaultTableModel projectTableModel = (DefaultTableModel) projectTable.getModel();
        String projectID = newProject.getId();
        String projectName = newProject.getName();
        String projectLecturerName = newProject.getLecturerName();
        Object[] row = {projectID, projectName, projectLecturerName};

        projectTableModel.addRow(row);
    }

    class addProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectView.setupLecturerAddProjectPanel();
            projectView.enablePanelButtons();
            projectView.resetSpecializationPicker();
        }
    }

    class confirmAddProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                System.out.println("Test");
                String newProjectId = "P" + projectModel.generateCode(projectList.getSize());
                String newProjectName = projectView.getProjectName();
                String newProjectLecturerId = user.getId();
                String newProjectLecturerName = user.getUsername();
                String newProjectSpecialization = projectView.getProjectSpecialization();
                String newProjectContent = projectView.getProjectContent();

                checkNameValidity(newProjectName);
                checkSpecializationValidity(projectView.getSpecializationPicker().getSelectedIndex());

                Project newProject = new Project(newProjectId, newProjectName, newProjectSpecialization, newProjectContent, newProjectLecturerId, newProjectLecturerName);
                lecturerList.saveNewProject(newProjectLecturerId, newProjectId);
                projectList.addItem(newProject);
                addNewProjectToTable(newProject);
                projectView.setupLecturerAddProjectPanel();

            } catch (IllegalFormatException exception){
                ProjectView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private void checkNameValidity(String name) throws IllegalFormatException{
        if(name.isEmpty()){
            throw new IllegalArgumentException("Missing Project Name");
        }

    }

    private void checkSpecializationValidity(int index) throws IllegalFormatException{
        if(index == 0){
            throw new IllegalArgumentException("Please pick a Specialization");
        }
    }

    class projectSpecializationPickerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String selectedSpecialization = projectView.getSelectedSpecialization();
            projectView.setProjectSpecializationLabel(selectedSpecialization);
        }
    }

    class EditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectView.enableContentEditMode();
        }
    }

    class SaveEditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                // Get edited values
                String newName = projectView.getProjectName();
                String newSpecialization = projectView.getProjectSpecialization();
                String newContent = projectView.getProjectContent();
                String projectID = projectModel.getId();

                checkNameValidity(newName);
                checkSpecializationValidity(projectView.getSpecializationPicker().getSelectedIndex());

                // Update project name in table
                JTable table = projectView.getProjectTable();
                int selectedRow = table.getSelectedRow();
                table.setValueAt(newName, selectedRow, 1);

                // Save project edit to json
                projectList.saveProjectName(projectID, newName);
                projectList.saveProjectSpecialization(projectID, newSpecialization);
                projectList.saveProjectContent(projectID, newContent);
                projectView.disableContentEditMode();

            }catch (IllegalArgumentException exception){
                ProjectView.displayErrorMessage(exception.getMessage());
            }
            
        }
    }

    class ToggleProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectList.toggleProject(projectModel.getId());
            projectView.setToggleButtonText(projectModel.getIsActive());
        }
    }

    class AssignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String projectSpecialization = projectModel.getSpecialization();
            String projectId = projectModel.getId();

            try{
                ArrayList<String> availableStudentIds = studentList.getFilteredStudentsIds(projectSpecialization);
                if(availableStudentIds.size() == 0) throw new IllegalArgumentException();

                String selectedStudentId = projectView.getStudentToAssign(availableStudentIds);
                if (!selectedStudentId.isEmpty()){
                    Student selectedStudent = studentList.getItem(selectedStudentId);
                    String selectedStudentName = selectedStudent.getUsername();

                    projectList.saveProjectStudentAssigned(projectId, selectedStudentId, selectedStudentName);
                    studentList.saveProjectAssignedToStudent(selectedStudentId, projectId);
                    projectView.setProjectStudentLabel(selectedStudentName);
                    projectView.enableUnassign();
                }
                
            }catch(IllegalArgumentException exception){
                ProjectView.displayErrorMessage("There are no students available to assign");
            }
            
        }
    }

    class UnassignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!projectView.getUnassignConfirmation()){
                return;
            }

            String projectId = projectModel.getId();
            String projectStudentAssigned = projectModel.getStudentAssignedId();

            // projectModel.setIsAssigned(false);
            projectList.saveProjectUnassigned(projectId);
            studentList.saveUnassignedStudent(projectStudentAssigned);

            projectView.setProjectStudentLabel("");
            projectView.enableAssign();
        }
    }

    class TableSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // get selected row's project object
            JTable table = projectView.getProjectTable();
            int selectedRow = table.getSelectedRow();
            String selectedRowID = (String) table.getValueAt(selectedRow, 0);
            projectModel = projectList.getItem(selectedRowID);

            // configure projectPanel properties
            projectView.enablePanelButtons();
            projectView.disableContentEditMode();

            populateModelView();
        }
    }
}
