package Model;

import java.io.IOException;
import java.util.ArrayList;


public class ReportList implements JsonList<Project>{
    JsonParser<Project> parser = new JsonParser<>("/Database/project.json", Project.class);
    private Project project;
    private ArrayList<Project> reports;
    //private int projectCount; ini pending

    //handles data logics
    //interact with db
    public ReportList(){   
        try{
            this.reports = parser.deserialize();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    // get all projects
    // public ReportList(Project project){
    //     try{
    //         this.reports = parser.deserialize();
    //         for (int i = 0; i < reports.size(); i++){
    //             Report report = reports.get(i);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } 
    // }

    public ArrayList<Project> getAllProjects(){
        ArrayList<Project> allProjectReport = new ArrayList<>();

        for(int i = 0; i < allProjectReport.size(); i++){
            Project report = allProjectReport.get(i);
        }   
        return allProjectReport;
    }

    // removing soon
    public ArrayList<Project> getBySpecialization(String specialization){
        ArrayList<Project> filteredProjects = new ArrayList<>();

        for(int i = 0; i < filteredProjects.size(); i++){
                Project report = filteredProjects.get(i);
                if(!(specialization.equals(report.getSpecialization()))){
                    reports.remove(i);
                    i--;
                }
        }   
        return filteredProjects;
    }


    public ArrayList<Project> getReports(){
        return reports;
    }

    //public String generateCode(){
    //     return String.format("%04d", projectCount + 1); ini pending
    // }

    // public int getProjectCount(){
    //     return projectData.getProjectCount(); ini pending
    // }

    @Override
    public void setList() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addItem(Project item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Project getItem(String id) {
        for (int i = 0; i < reports.size(); i++){
            Project tempReport = reports.get(i);
            if (tempReport.getId().equals(id)){
                return tempReport;
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}
