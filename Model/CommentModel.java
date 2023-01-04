package Model;

public class CommentModel {
    private String commentID;

    // private String lecturerID;
    // private String studentID;
    private String userID;
    private String username;
    private String commentString;
    private String projectID;


    public CommentModel(){}

    public CommentModel(String commentID, Project project, Administrator admin,String username , String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.userID = admin.getID();
        this.username = admin.getUsername();
        this.commentString = commentString;
    }
    //Lecturer lecturer, Student student,
    public CommentModel(String commentID, Project project, Lecturer lecturer,String username , String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.userID = lecturer.getID();
        this.username = lecturer.getUsername();
        this.commentString = commentString;
    }

    public CommentModel(String commentID, Project project, Student student,String username , String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.userID = student.getID();
        this.username = student.getUsername();
        this.commentString = commentString;
    }
    
    public String getCommentID() {
        return commentID;
    }
    public String getUsername(){
        return username;
    }
    public String getProjectID(){
        return projectID;
    }
    public String getUserID(){
        return userID;
    }
    public String getCommentString(){
        return commentString;
    }

    public void setCommentString(String newComment){
        this.commentString = newComment;
    }

}
