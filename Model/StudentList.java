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
    
    private ArrayList<Student> getFilteredStudents(String specialization){
        ArrayList<Student> filteredStudents = new ArrayList<>(students);
        
        for (int i = 0; i < filteredStudents.size(); i++){
            Student student = filteredStudents.get(i);
            if ((!(student.getSpecialization().equals(specialization))) || (!(student.getAssignedProjectID() == null))){
                filteredStudents.remove(i);
                i--;
            }
        }

        return filteredStudents;
    }

    public ArrayList<String> getFilteredStudentsIds(String specialization){
        ArrayList<Student> filteredStudents = getFilteredStudents(specialization);
        ArrayList<String> filteredStudentIds = new ArrayList<>();

        for (int i = 0; i < filteredStudents.size(); i++){
            Student student = filteredStudents.get(i);
            String studentId = student.getId(); 
            
            filteredStudentIds.add(studentId);
        }

        return filteredStudentIds;
    }

    public void saveProjectAssignedToStudent(String studentId, String projectId){
        Student student = getItem(studentId);
        student.setAssignedProjectID(projectId);

        save();
    }

    public void saveUnassignedStudent(String studentId){
        Student student = getItem(studentId);
        student.setAssignedProjectID(null);

        save();
    }

    public void saveProjectDeletion(String studentId){
        Student student = getItem(studentId);
        student.removeAssignedProject();

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
            this.students = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Student item) {
        setList();

        this.students.add(item);
         
        save();
    }

    @Override
    public Student getItem(String id) {
        for (int i = 0; i < students.size(); i++){
            Student tempStudent = students.get(i);
            if (tempStudent.getId().equals(id)){
                return tempStudent;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        ArrayList<Student> tempList;
        try {
            tempList = parser.deserialize();
            return tempList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
