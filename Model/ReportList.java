package Model;

import java.io.IOException;
import java.util.ArrayList;


public class ReportList implements JsonList<Report>{
    JsonParser<Report> parser = new JsonParser<>("/Database/project.json", Report.class);
    private ProjectData projectData;
    private ArrayList<Report> reports;
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

    public ArrayList<Report>  getAllProjects (){
        ArrayList<Report> allProjectReport = new ArrayList<>(reports);

        for(int i = 0; i < allProjectReport.size(); i++){
            Report report = allProjectReport.get(i);

        }
        return allProjectReport;
    }

    
    // filter by 
    // public ArrayList<String> getReportList(){

    // }

    public ArrayList<Report> getReports(){
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
    public void addItem(Report item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Report getItem(String id) {
        for (int i = 0; i < reports.size(); i++){
            Report tempReport = reports.get(i);
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
