package Model;

public class CommentModel {
    private String commentID;

    // private Lecturer lecturer;
    // private Student student;
    private User user;
    private String commentString;
    private Project project;

    public CommentModel(){}

    // public CommentModel(String commentID, Project project, Lecturer lecturer, Student student, String commentString){
    //     this.commentID = commentID;
    //     this.project = project;
    //     this.lecturer = lecturer;
    //     this.student = student;
    //     this.commentString = commentString;
    // }
    public CommentModel(String commentID, Project project, User user, String commentString){
        this.commentID = commentID;
        this.project = project;
        this.user = user;
        this.commentString = commentString;
    }


    public String getCommentID() {
        return commentID;
    }

    public String getProjectID(){
        return project.getId();
    }

    public User getUser(){
        return user;
    }
    // public String getStudentID(){
    //     return student.getID();
    // }
    
    // public String getLecturerID(){
    //     return lecturer.getID();
    // }

    public String getCommentString(){
        return commentString;
    }

    public void setCommentString(String newComment){
        this.commentString = newComment;
    }

}
