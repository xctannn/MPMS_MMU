package Model;

/**
 * Kam Kar Hou
 * Purpose: A comment will contain the project id of a project, the user who is commenting and the commented string
 */
public class CommentModel {
    private String commentID;
    private String userID;
    private String username;
    private String commentString;
    private String projectID;

    
    /**
     * Kam Kar Hou
     * Purpose: This constructor creates a new Comment object with the given commentID, projectID, user and commentString 
     */
    public CommentModel(String commentID, String projectID, User user , String commentString){
        this.commentID = commentID;
        this.projectID = projectID;
        this.userID = user.getId();
        this.username = user.getUsername();
        this.commentString = commentString;
    }
    
    /**
     * Kam Kar Hou
     */
    public String getCommentID() {
        return commentID;
    }
    /**
     * Kam Kar Hou
     */
    public String getUserID(){
        return userID;
    }
    /**
     * Kam Kar Hou
     */
    public String getUsername(){
        return username;
    }
    /**
     * Kam Kar Hou
     */
    public String getProjectID(){
        return projectID;
    }
    /**
     * Kam Kar Hou
     */
    public String getCommentString(){
        return commentString;
    }


}
