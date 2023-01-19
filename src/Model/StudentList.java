package src.Model;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Chua Hui Yi
 * Purpose: Implemented the JsonList to the class to manipulate the list of student objects in Student.json
 */
public class StudentList implements JsonList<Student> {
    JsonParser<Student> parser = new JsonParser<>("/Database/student.json", Student.class);
    private ArrayList<Student> students;

    /* 
     * Chua Hui Yi
     * No-Arg Constructor
     */
    public StudentList() {
        setList();
    }

    /* 
     * Chua Hui Yi
     * Purpose: Return the arraylist of student
     */
    public ArrayList<Student> getStudents(){
        return students;
    }
    
    /* 
     * Chua Hui Yi
     * Purpose: Filter out the student that matched the specialization and has not assigned to a project yet
     */
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

    /* 
     * Chua Hui Yi
     * Purpose: Get the studen id of the student that is filtered out
     */
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

    /* 
     * Chua Hui Yi
     * Purpose: Save the assigned project to student to the database
     */
    public void saveProjectAssignedToStudent(String studentId, String projectId){
        Student student = getItem(studentId);
        student.setAssignedProjectID(projectId);

        save();
    }

    /* 
     * Chua Hui Yi
     * Purpose: Save the unassigned student to the database, the project assigned will be null
     */
    public void saveUnassignedStudent(String studentId){
        Student student = getItem(studentId);
        student.setAssignedProjectID(null);

        save();
    }

    /* 
     * Chua Hui Yi
     * Purpose: Remove the assigned project if the project is being deleted
     */
    public void saveProjectDeletion(String studentId){
        Student student = getItem(studentId);
        student.removeAssignedProject();

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
     * Purpose: Save the student into Student.json
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
     * Purpose: Reads the student and populates data into the studentList
     */
    @Override
    public void setList(){
        try {
            this.students = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Chua Hui Yi
     * Purpose: Add new student object into the arraylist Student
     */
    @Override
    public void addItem(Student item) {
        setList();
        this.students.add(item);  
        save();
    }

    /* 
     * Chua Hui Yi
     * Purpose: To get the student object based on the id
     */
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

    /* 
     * Chua Hui Yi
     * Purpose: To get the size of the arraylist
     */
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
