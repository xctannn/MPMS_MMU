import java.util.ArrayList;

import Controller.ProjectController;
import Model.Project;
import Model.ProjectList;
import View.ProjectView;

public class Main {
    public static void main(String[] args){
        ProjectView projectView = new ProjectView();
        Project projectModel = new Project();
        ProjectList projectList = new ProjectList();
        
        projectList.addItem(new Project());
        
        

        ProjectController projectController = new ProjectController(projectModel, projectView);
        // projectView.setVisible(true);
        projectView.setAssignButtonVisible();
    } 
}
