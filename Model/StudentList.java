package Model;

import java.io.IOException;
import java.util.ArrayList;

public class StudentList implements JsonList<Student> {
    JsonParser<Student> parser = new JsonParser<>("/Database/student.json", Student.class);
    private ArrayList<Student> students;

    public StudentList() {
        setList();
    }

    public ArrayList<Student> getStudents(){
        return students;
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
            this.students = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Student item) {
        this.students.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getItem(String id) {
        for (int i = 0; i < students.size(); i++){
            Student tempStudent = students.get(i);
            if (tempStudent.getID().equals(id)){
                return tempStudent;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        return students.size();
    } 
}
