package Model;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectList implements JsonList<Project>{
    JsonParser<Project> parser = new JsonParser<>("/Database/project.json", Project.class);
    private ArrayList<Project> projects;
    public Object getProjects;
    
    public ProjectList(){
        
        try {
            this.projects = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < projects.size(); i++){
            System.out.println(projects.get(i).getName());
        }
    }

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    @Override
    public void addItem(Project item) {
        this.projects.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Project getItem(String id) {
        for (int i = 0; i < projects.size(); i++){
            Project tempProject = projects.get(i);
            if (tempProject.getId() == id){
                return tempProject;
            }
        }
        return null;
    }
}
