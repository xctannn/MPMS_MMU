package Model;

import java.util.ArrayList;

public class ProjectData {
    private ArrayList<Project> projects;
    private int projectCount;

    private ProjectData(){}

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    };

}
