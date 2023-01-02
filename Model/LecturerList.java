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
            if (tempLecturer.getID().equals(id)){
                return tempLecturer;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        return lecturers.size();
    } 
}
