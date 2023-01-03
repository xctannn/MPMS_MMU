package Controller;

import Model.Project;

public class LecturerController {
    
    private Project projectModel;

    public LecturerController(Project projectModel) {
        this.projectModel = projectModel;
    }


    // Thinking this as a checkbox
    public void updateProjectActive() {
        if(projectModel.getIsActive() == true){
            projectModel.setIsActive(false);
        }else{
            projectModel.setIsActive(true);
        }
    }

    public void updateProjectAssigned() {
        if(projectModel.getIsAssigned() == true){
            projectModel.setIsAssigned(false);
        }else{
            projectModel.setIsAssigned(true);
        }
    }

}
