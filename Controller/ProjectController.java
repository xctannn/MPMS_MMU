package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Project;
import View.ProjectView;

public class ProjectController {
    private Project projectModel;
    private ProjectView projectView;

    public ProjectController(Project model, ProjectView view){
        this.projectModel = model;
        this.projectView = view;

        projectView.addReturnButtonListener(new ReturnButtonListener());
        projectView.addAssignButtonListener(new AssignButtonListener());
    }

    public void populateModelView(){
        projectView.setProjectNameLabel(projectModel.getName());
        projectView.setProjectLecturerLabel(projectModel.getLecturer().getUsername());
        projectView.setProjectSpecializationLabel(projectModel.getSpecialization());
        projectView.setProjectContentArea(projectModel.getContent());
        projectView.setProjectStudentLabel(projectModel.getStudentAssigned().getUsername());
        
    }

    class ReturnButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Return to main page
        }
    }

    class AssignButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String id = projectView.getStudentID();
            System.out.println(id);
        }
    }
    
}
