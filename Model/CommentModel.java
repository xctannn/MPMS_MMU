package Model;

public class CommentModel {
    private String commentID;

    private String userID;
    private String username;
    private String commentString;
    private String projectID;



    public CommentModel(){}

    public CommentModel(String commentID, Project project, User user , String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.userID = user.getId();
        this.username = user.getUsername();
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
