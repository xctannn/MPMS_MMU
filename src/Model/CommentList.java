package src.Model;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Kam Kar Hou
 * Purpose: A list of comments consists several comments from comments.json which implements a parser from JsonList.java
 */
public class CommentList implements JsonList<CommentModel> {
    JsonParser<CommentModel> parser = new JsonParser<>("/Database/comments.json", CommentModel.class);
    private ArrayList<CommentModel> comments;

    /**
     * Kam Kar Hou
     * Purpose: A default constructors that creates an arraylist object with the model of CommentModel.java
     * and reads the comments.java file into the arraylist comments.
     */
    public CommentList(){
        setList();
    }
    
    /**
     * Kam Kar Hou
     * Purpose: A contructor that filters out the projects which are not the current project
     * to see the current project's comments
     */
    public CommentList(Project currentProject){
        try {
            this.comments = parser.deserialize();
            for (int i = 0; i < comments.size(); i++){
                CommentModel comment = comments.get(i);
                String projectID = comment.getProjectID();
                if (!(projectID.equals(currentProject.getId()))){
                    comments.remove(i);
                    i--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    /**
     * Kam Kar Hou
     * Purpose: To remove the comments in the database which contains
     * the specific ProjectID
     */
    public void removeCommentsWithProjectID(String projectID){
        for (int i = 0; i < comments.size(); i++){
            if (comments.get(i).getProjectID().equals(projectID)){
                comments.remove(comments.get(i));
                i--;
            }
        }
        save();
    }
    
    /**
     * Kam Kar Hou
     * Purpose: While adding a new comment, it will set the comments list again 
     * and add a new comment then save into comments.json
     */
    @Override
    public void addItem(CommentModel item) {
        setList();
        this.comments.add(item);
        save();
    }

    /**
     * Kam Kar Hou
     * Purpose: A getter for a specific project id in an arraylist of comments
     */
    @Override
    public CommentModel getItem(String projectID) {
        for (int i = 0; i < comments.size(); i++){
            CommentModel tempComment = comments.get(i);
            if (tempComment.getProjectID().equals(projectID)){
                return tempComment;
            }
        }
        return null;
    }

    /**
     * Kam Kar Hou
     * Purpose: Reads the comments.java file into the arraylist comments
     */
    @Override
    public void setList() {
        try{
            this.comments = parser.deserialize();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Kam Kar Hou
     * Purpose: A method that saves the commentList into the database comments.json
     */
    @Override
    public void save() {
        // Write comments to file using JSON parser
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Kam Kar Hou
     * Purpose: A method gets the size of the array
     */
    @Override
    public int getSize() {
        ArrayList<CommentModel> tempList;
        try {
            tempList = parser.deserialize();
            return tempList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Kam Kar Hou
    public ArrayList<CommentModel> getComments(){
        return comments;
    }

}   





