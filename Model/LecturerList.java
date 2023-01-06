package Model;

import java.io.IOException;
import java.util.ArrayList;

public class LecturerList implements JsonList<Lecturer> {
    JsonParser<Lecturer> parser = new JsonParser<>("/Database/lecturer.json", Lecturer.class);
    private ArrayList<Lecturer> lecturers;

    public LecturerList() {
        setList();
    }

    public ArrayList<Lecturer> getLecturers(){
        return lecturers;
    }

    public void saveNewProject(String lecturerId, String projectId){
        Lecturer lecturer = getItem(lecturerId);
        lecturer.addProject(projectId);

        save();
    }

    public void saveProjectDeletion(String lecturerId, String projectId){
        Lecturer lecturer = getItem(lecturerId);
        lecturer.removeProject(projectId);

        save();
    }

    @Override
    public void save(){
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(){
        try {
            this.lecturers = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Lecturer item) {
        this.lecturers.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Lecturer getItem(String id) {
        for (int i = 0; i < lecturers.size(); i++){
            Lecturer tempLecturer = lecturers.get(i);
            if (tempLecturer.getId().equals(id)){
                return tempLecturer;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        ArrayList<Lecturer> tempList;
        try {
            tempList = parser.deserialize();
            return tempList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
