package src.Model;

import java.util.ArrayList;

/*
 * Purpose: A class created specifically to map Project.json to an existing object, for ease of parsing
 */
public class ProjectData {
    private ArrayList<Project> projects;
    private int projectCount;

    /* 
     * Tan Xiao Chin
     * No-Arg Constructor
     * Purpose: For JSON serializer to serialize and deserialize Project object into and from JSON file
     */
    private ProjectData(){}

    // GETTERS AND SETTERS
     
    // Tan Xiao Chin
    public ArrayList<Project> getProjects() {
        return projects;
    }
     
    // Tan Xiao Chin
    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
     
    // Tan Xiao Chin
    public int getProjectCount() {
        return projectCount;
    }
     
    // Tan Xiao Chin
    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }
}
