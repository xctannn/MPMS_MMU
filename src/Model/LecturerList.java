package src.Model;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Chua Hui Yi
 * Purpose: Implemented the JsonList to the class to manipulate the list of lecturer objects in Lecturer.json
 */
public class LecturerList implements JsonList<Lecturer> {
    JsonParser<Lecturer> parser = new JsonParser<>("/Database/lecturer.json", Lecturer.class);
    private ArrayList<Lecturer> lecturers;

    /* 
     * Chua Hui Yi
     * No-Arg Constructor
     */
    public LecturerList() {
        setList();
    }

    /* 
     * Chua Hui Yi
     * Purpose: Return the arraylist of lecturers
     */
    public ArrayList<Lecturer> getLecturers(){
        return lecturers;
    }

    // Chua Hui Yi
    public void saveNewProject(String lecturerId, String projectId){
        Lecturer lecturer = getItem(lecturerId);
        lecturer.addProject(projectId);

        save();
    }

    // Chua Hui Yi
    public void saveProjectDeletion(String lecturerId, String projectId){
        Lecturer lecturer = getItem(lecturerId);
        lecturer.removeProject(projectId);

        save();
    }

    /*
     * Chua Hui Yi
     * Purpose: Generate the user id
     */
    public String generateCode(int listSize){
        return String.format("%04d", listSize + 1);
    }

    /* 
     * Chua Hui Yi
     * Purpose: Save the lecturer into lecturer.json
     */
    @Override
    public void save(){
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Chua Hui Yi
     * Purpose: Reads the lecturer and populates data into the lecturerList
     */
    @Override
    public void setList(){
        try {
            this.lecturers = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Chua Hui Yi
     * Purpose: Add new lecturer object into the arraylist Lecturer
     */
    @Override
    public void addItem(Lecturer item) {
        setList();
        this.lecturers.add(item);  
        save();
    }

    /* 
     * Chua Hui Yi
     * Purpose: To get the lecturer object based on the id
     */
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

    /* 
     * Chua Hui Yi
     * Purpose: To get the size of the arraylist
     */
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
