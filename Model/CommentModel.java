package Model;

/**
 * A comment will contain the project id of a project, the user who is commenting and the commented string
 * @author Kam Kar Hou
 * @version 1.0
 */
public class CommentModel {
    private String commentID;
    private String userID;
    private String username;
    private String commentString;
    private String projectID;

    /**
     * This constructor creates a new Comment object with the given commentID, project, user, commentString
     * @param commentID represents an unique id to be identified in the comments.json
     * @param project represents for the current project the users are commenting into
     * @param user represents the current author of the comment
     * @param commentString represents the written comment in the project
     */
    public CommentModel(String commentID, Project project, User user , String commentString){
        this.commentID = commentID;
        this.projectID = project.getId();
        this.userID = user.getId();
        this.username = user.getUsername();
        this.commentString = commentString;
    }
    
    /**
     * Getter for Comment ID
     * @return commentID
     */
    public String getCommentID() {
        return commentID;
    }

    /**
     * Getter for User ID
     * @return userID
     */
    public String getUserID(){
        return userID;
    }
        
    /**
     * Getter for Username
     * @return username
     */
    public String getUsername(){
        return username;
    }
        
    /**
     * Getter for Project ID
     * @return projectID
     */
    public String getProjectID(){
        return projectID;
    }
        
    /**
     * Getter for Written Comment
     * @return commentString
     */
    public String getCommentString(){
        return commentString;
    }


}
