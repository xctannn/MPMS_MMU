package Model;


// A comment will contain the project id of a project, the user who is commenting and the commented string
// Author: Kam Kar Hou
public class CommentModel {
    private String commentID;
    private String userID;
    private String username;
    private String commentString;
    private String projectID;

    
    // This constructor creates a new Comment object with the given commentID, projectID, user and commentString 
    public CommentModel(String commentID, String projectID, User user , String commentString){
        this.commentID = commentID;
        this.projectID = projectID;
        this.userID = user.getId();
        this.username = user.getUsername();
        this.commentString = commentString;
    }
    

    // Getter for Comment ID
    public String getCommentID() {
        return commentID;
    }

    // Getter for User ID
    public String getUserID(){
        return userID;
    }
        

    // Getter for Username
    public String getUsername(){
        return username;
    }
        

    // Getter for Project ID
    public String getProjectID(){
        return projectID;
    }
        

    // Getter for Written Comment
    public String getCommentString(){
        return commentString;
    }


}
