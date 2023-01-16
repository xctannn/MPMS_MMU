package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Model.Administrator;
import Model.CommentList;
import Model.Lecturer;
import Model.LecturerList;
import Model.Project;
import Model.ProjectList;
import Model.Student;
import Model.StudentList;
import Model.User;
import View.ProjectView;

/*
 * Purpose: Controller for obtaining information ProjectView to manipulate Project data 
 */
public class ProjectController {
    private MainController mainController;
    private User user;
    private Project projectModel;
    private ProjectList projectList = new ProjectList();
    private ArrayList<Project> filteredProjectList;
    private ProjectView projectView;
    private LecturerList lecturerList = new LecturerList();
    private StudentList studentList = new StudentList();
    private CommentList commentList = new CommentList();
    private ReportController reportController;
    private ArrayList<String> selectionWheelOptions = new ArrayList<String>();
     /*
     * Yaw Boon Zhe
     * Constructor to build view for Administrator users
     */
    public ProjectController(MainController mainController, Administrator user){
        this.mainController = mainController;
        this.user = user;
        this.projectView = new ProjectView(user);
        this.filteredProjectList = new ArrayList<>(projectList.getProjects());
        this.reportController = new ReportController(user, projectView);

        projectView.addLogoutButtonListener(new LogoutButtonListener());
        projectView.addRegisterAccountButtonListener(new RegisterAccountButtonListener());
        projectView.addAdminAddProjectButtonListener(new AdminAddProjectButtonListener());
        projectView.addGenerateReportButtonListener(new GenerateReportButtonListener());
        projectView.addProjectLecturerSelectorListener(new ProjectLecturerSelectorListener());
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        projectView.addConfirmAdminAddProjectButtonListener(new ConfirmAdminAddProjectButtonListener());
        projectView.addDeleteProjectButtonListener(new DeleteProjectButtonListener());
        projectView.addProjectCommentsButtonListener(new ProjectCommentsButtonListener());

        populateTable();
    }

     /*
     * Yaw Boon Zhe
     * Constructor to build view for Lecturer users
     */
    public ProjectController(MainController mainController, Lecturer user){
        this.mainController = mainController;
        this.user = user;
        this.projectView = new ProjectView(user);
        this.filteredProjectList = projectList.getFilteredProjects(user);

        // new ProjectController.Listeners
        projectView.addLogoutButtonListener(new LogoutButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        projectView.addLecturerAddProjectButtonListener(new LecturerAddProjectButtonListener());
        projectView.addAdminAddProjectButtonListener(new AdminAddProjectButtonListener());
        projectView.addConfirmLecturerAddProjectButtonListener(new ConfirmLecturerAddProjectButtonListener());
        projectView.addProjectSpecializationSelectorListener(new ProjectSpecializationSelectorListener());
        projectView.addEditContentButtonListener(new EditButtonListener());
        projectView.addSaveEditButtonListener(new SaveEditButtonListener());
        projectView.addToggleProjectButtonListener(new ToggleProjectButtonListener());
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addUnassignButtonListener(new UnassignButtonListener());
        projectView.addProjectCommentsButtonListener(new ProjectCommentsButtonListener());
        
        populateTable();
    }

     /*
     * Yaw Boon Zhe
     * Constructor to build view for Student users
     */
    public ProjectController(MainController mainController, Student user){
        this.mainController = mainController;
        this.user = user;
        this.projectView = new ProjectView(user);
        this.filteredProjectList = projectList.getFilteredProjects(user);
        
        projectView.addLogoutButtonListener(new LogoutButtonListener());
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        projectView.addProjectCommentsButtonListener(new ProjectCommentsButtonListener());

        populateTable();
    }

    // Yaw Boon Zhe
    public JPanel getProjectView(){
        return projectView;
    }

    /*
     * Yaw Boon Zhe
     * Purpose: Populate panel on the right of ProjectView with information of the project selected in the table
     */
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

    /*
     * Yaw Boon Zhe
     * Purpose: Populate table on the left of ProjectView
     */
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

    // Yaw Boon Zhe
    public void addNewProjectToTable(Project newProject){
        JTable projectTable = projectView.getProjectTable();
        DefaultTableModel projectTableModel = (DefaultTableModel) projectTable.getModel();
        String projectID = newProject.getId();
        String projectName = newProject.getName();
        String projectLecturerName = newProject.getLecturerName();
        Object[] row = {projectID, projectName, projectLecturerName};

        projectTableModel.addRow(row);
    }

    // Yaw Boon Zhe
    public void deleteProjectFromTable(){
        int selectedRow = projectView.getSelectedRow();

        filteredProjectList.remove(selectedRow);
        populateTable();
    }

    /**
     * Kam Kar Hou
     * Purpose: Removes all the comments which contains the same 
     * projectID as the selected row and remove all of them
     */
    public void deleteAllCommentsForProject(String projectId){
        commentList.removeCommentsWithProjectID(projectId);
    }

    // Event handler methods
    // Yaw Boon Zhe
    public class LogoutButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!projectView.getUnassignConfirmation("Are you sure you want to log out?")){
                return;
            }

            mainController.switchLoginView();
        }
    }

    // Yaw Boon Zhe
    public class RegisterAccountButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainController.switchRegisterView();
        }
    }

    // Yaw Boon Zhe
    public class LecturerAddProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectView.setupLecturerAddProjectPanel();
            projectView.enablePanelButtons();
            projectView.resetSpecializationSelector();
        }
    }

    // Yaw Boon Zhe
    class AdminAddProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            lecturerList.setList();
            ArrayList<String> lecturerOptions = new ArrayList<String>();
            ArrayList<Lecturer> lecturers = lecturerList.getLecturers();
            for(int i = 0; i < lecturers.size(); i++){
                Lecturer lecturer = lecturers.get(i);
                lecturerOptions.add(lecturer.getId());
            }

            projectView.setupAdminAddProjectPanel();
            projectView.populateLecturerPicker(lecturerOptions);
            projectView.enablePanelButtons();
            projectView.resetSpecializationSelector();
        }
    }

    // Yaw Boon Zhe
    class GenerateReportButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(selectionWheelOptions.isEmpty()){
                projectView.getGenerateReportList(selectionWheelOptions);
            }

            try{
                String selectedOptions = projectView.getGenerateReportOptions(selectionWheelOptions);
                if (selectedOptions.equals(selectionWheelOptions.get(1))){
                    reportController.allProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(2))){
                    reportController.specializationProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(3))){
                    reportController.lecturerProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(4))){
                    reportController.inactiveProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(5))){
                    reportController.activeProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(6))){
                    reportController.assignedProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(7))){
                    reportController.unassignedProjectList();
                }
                else if (selectedOptions.equals(selectionWheelOptions.get(8))){
                    reportController.commentProjectList();
                }

            }catch (IllegalArgumentException exception){
                ProjectView.displayErrorMessage("No projects found, report will not be generated");
            }
        }
    }

    // Yaw Boon Zhe
    class ConfirmLecturerAddProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String newProjectId = "P" + projectList.generateCode();
                String newProjectName = projectView.getProjectName();
                String newProjectLecturerId = user.getId();
                String newProjectLecturerName = user.getUsername();
                String newProjectSpecialization = projectView.getProjectSpecialization();
                String newProjectContent = projectView.getProjectContent();

                checkNameValidity(newProjectName);
                checkSpecializationValidity(projectView.getSpecializationSelector().getSelectedIndex());

                Project newProject = new Project(newProjectId, newProjectName, newProjectSpecialization, newProjectContent, newProjectLecturerId, newProjectLecturerName);
                lecturerList.saveNewProject(newProjectLecturerId, newProjectId);
                projectList.addItem(newProject);
                filteredProjectList.add(newProject);
                projectList.saveProjectCountIncrement();
                addNewProjectToTable(newProject);
                projectView.setupLecturerAddProjectPanel();

            } catch (IllegalArgumentException exception){
                ProjectView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    // Yaw Boon Zhe
    class ConfirmAdminAddProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                checkSpecializationValidity(projectView.getSpecializationSelector().getSelectedIndex());
                String newProjectId = "P" + projectList.generateCode();
                String newProjectName = projectView.getProjectName();
                String newProjectLecturerId = projectView.getSelectedLecturer();
                String newProjectLecturerName = lecturerList.getItem(newProjectLecturerId).getUsername();
                String newProjectSpecialization = projectView.getSelectedSpecialization();
                String newProjectContent = projectView.getProjectContent();
                checkNameValidity(newProjectName);

                Project newProject = new Project(newProjectId, newProjectName, newProjectSpecialization, newProjectContent, newProjectLecturerId, newProjectLecturerName);
                lecturerList.saveNewProject(newProjectLecturerId, newProjectId);
                projectList.addItem(newProject);
                filteredProjectList.add(newProject);
                projectList.saveProjectCountIncrement();
                addNewProjectToTable(newProject);
                projectView.setupAdminAddProjectPanel();

            } catch (IllegalArgumentException exception){
                ProjectView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    // Yaw Boon Zhe
    private void checkNameValidity(String name) throws IllegalArgumentException{
        if(name.isEmpty()){
            throw new IllegalArgumentException("Missing Project Name");
        }
    }

    // Yaw Boon Zhe
    private void checkSpecializationValidity(int index) throws IllegalArgumentException{
        if(index == 0){
            throw new IllegalArgumentException("Please pick a Specialization");
        }
    }
    
    // Yaw Boon Zhe
    class ProjectLecturerSelectorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String selectedLecturer = projectView.getSelectedLecturer();
            projectView.setProjectLecturerLabel(selectedLecturer);
        }
    }
    // Yaw Boon Zhe
    class ProjectSpecializationSelectorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String selectedSpecialization = projectView.getSelectedSpecialization();
            projectView.setProjectSpecializationLabel(selectedSpecialization);
        }
    }

    // Tan Xiao Chin
    class EditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectView.enableContentEditMode();
        }
    }

    // Tan Xiao Chin
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
                checkSpecializationValidity(projectView.getSpecializationSelector().getSelectedIndex());

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

    // Tan Xiao Chin
    class ToggleProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            projectList.toggleProject(projectModel.getId());
            projectView.setToggleButtonText(projectModel.getIsActive());
        }
    }

    // Tan Xiao Chin
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

    // Tan Xiao Chin
    class UnassignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!projectView.getUnassignConfirmation("Are you sure you want to unassign the student?")){
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

    // Yaw Boon Zhe
    class DeleteProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!projectView.getUnassignConfirmation("Are you sure you want to delete this project?")){
                return;
            }

            String projectId = projectModel.getId();
            String projectLecturerId = projectModel.getLecturerId();
            String projectStudentId = projectModel.getStudentAssignedId();

            deleteProjectFromTable();
            deleteAllCommentsForProject(projectId);
            if (projectModel.getIsAssigned()) studentList.saveProjectDeletion(projectStudentId);
            lecturerList.saveProjectDeletion(projectLecturerId, projectId);
            projectList.saveProjectDeletion(projectId);
            projectView.disableAllPanelElements();
        }
    }

    // Tan Xiao Chin
    class ProjectCommentsButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String selectedProjectId = projectModel.getId();
            mainController.switchCommentView(selectedProjectId, user);
        }
    }

    // Tan Xiao Chin
    class TableSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // get selected row's project object
            JTable table = projectView.getProjectTable();
            int selectedRow = projectView.getSelectedRow();

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1){
                String selectedRowID = (String) table.getValueAt(selectedRow, 0);
                projectModel = projectList.getItem(selectedRowID);
                populateModelView();
            }

            // configure projectPanel properties
            projectView.enablePanelButtons();
            projectView.disableContentEditMode();
        }
    }

    // public static void main(String[] args){
        // ProjectList projectList = new ProjectList();
        // Lecturer lecturer2 = new Lecturer("L0001", "Tan", "l01");
        // Administrator admin = new Administrator("A0001", "Admin", "a01");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // Student student = new Student("S001", "S1", "s01", "Data Science", "P0002");

        // lecturer2.addproject("P0001");
        // lecturer2.addproject("P0002");
        // lecturer2.addproject("P0004");
        // projectList.addItem(new Project("P0003", "Final Year", "Data", "Build this game for me or else you fail your FYP and spend another 10k on your degree", lecturer2));

        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(1200, 700);
        
        // ProjectController projectController = new ProjectController(this, student);
        // frame.add(projectController.getProjectView());
        // frame.setVisible(true);
    // }
}
