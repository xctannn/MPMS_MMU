package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.xml.stream.events.Comment;

import Model.Administrator;
import Model.CommentList;
import Model.Lecturer;
import Model.LecturerList;
import Model.Project;
import Model.ProjectData;
import Model.ProjectList;
import Model.Student;
import Model.StudentList;
import Model.User;
import Model.CommentModel;
import View.CommentView;
import View.ProjectView;

public class ProjectController {
    private User user;
    private Project projectModel;
    private ProjectList projectList = new ProjectList();
    private ArrayList<Project> filteredProjectList;
    private ProjectView projectView;

    public ProjectController(Administrator user, ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = new ArrayList<>(projectList.getProjects());

        
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());
        projectView.addDeleteProjectButtonListener(new DeleteProjectButtonListener());


        populateTable();
        projectView.defaultProjectView(user);
    }
     //Comment Panel
    public ProjectController(CommentModel model, CommentView view,Project currentProject){
        this.commentModel = model;
        this.commentView = view;
        this.commentList = new CommentList();
        populateCommentTable(currentProject);
    }
     //Comment Panel
    public ProjectController(CommentModel model, CommentView view,Project currentProject){
        this.commentModel = model;
        this.commentView = view;
        this.commentList = new CommentList();
        populateCommentTable(currentProject);
    }

    public ProjectController(Lecturer user,ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = projectList.getFilteredProjects(user);

        
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
        projectView.defaultProjectView(user);
    }

    public ProjectController(Student user, ProjectView view){
        this.user = user;
        this.projectView = view;
        this.filteredProjectList = projectList.getFilteredProjects(user);


        
        projectView.addAssignButtonListener(new AssignButtonListener());
        projectView.addTableSelectionListener(new TableSelectionListener());

        populateTable();
        projectView.defaultProjectView(user);
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

    public void deleteProjectFromTable(){
        int selectedRow = projectView.getSelectedRow();

        filteredProjectList.remove(selectedRow);
        populateTable();
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
                String newProjectId = "P" + projectList.generateCode();
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
                projectList.saveProjectCountIncrement();
                addNewProjectToTable(newProject);
                projectView.setupLecturerAddProjectPanel();

            } catch (IllegalArgumentException exception){
                ProjectView.displayErrorMessage(exception.getMessage());
            }
        }
    }

    private void checkNameValidity(String name) throws IllegalArgumentException{
        if(name.isEmpty()){
            throw new IllegalArgumentException("Missing Project Name");
        }
    }

    private void checkSpecializationValidity(int index) throws IllegalArgumentException{
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


    public void populateCommentTable(Project currentProject){
        ArrayList<CommentModel> comments = commentList.getComments();
        DefaultTableModel commentTableModel = new DefaultTableModel(commentView.getColumnNames(),0);
        String currentProjectId = currentProject.getId();
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).getProjectID().equals(currentProjectId)){
                CommentModel comment = comments.get(i);
                String commentID = comment.getCommentID();
                String userID = comment.getUserID();
                String username = comment.getUsername();
                String commentString = comment.getCommentString();
                //"ID", "UserID", "Username","Comment",
                Object[] row = {commentID, userID,username, commentString};
                
                commentTableModel.addRow(row);
                
            }

        }
        JTable viewTable = commentView.getCommentTable();
        viewTable.setModel(commentTableModel);

        TableColumnModel columnModel = viewTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(250);   
        columnModel.getColumn(3).setPreferredWidth(250);  
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

    class DeleteProjectButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!projectView.getUnassignConfirmation("Are you sure you want to delete this project?")){
                return;
            }

            String projectId = projectModel.getId();
            String projectLecturerId = projectModel.getLecturerId();
            String projectStudentId = projectModel.getStudentAssignedId();

            if (projectModel.getIsAssigned()) studentList.saveProjectDeletion(projectStudentId);
            lecturerList.saveProjectDeletion(projectLecturerId, projectId);
            projectList.saveProjectDeletion(projectId);
            deleteProjectFromTable();
            projectView.disableAllPanelTexts();
        }
    }

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
}
