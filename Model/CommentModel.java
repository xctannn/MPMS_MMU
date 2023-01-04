package Model;

public class CommentModel {
    private String commentID;

    private String lecturerID;
    private String studentID;
    private String commentString;
    private String projectID;

    public CommentModel(){}

    public CommentModel(String commentID, Project project, Lecturer lecturer, Student student, String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.lecturerID = lecturer.getID();
        this.studentID = student.getID();
        this.commentString = commentString;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getProjectID(){
        return projectID;
    }

    public String getStudentID(){
        return studentID;
    }
    
    public String getLecturerID(){
        return lecturerID;
    }

    public String getCommentString(){
        return commentString;
    }

    public void setCommentString(String newComment){
        this.commentString = newComment;
    }

}
