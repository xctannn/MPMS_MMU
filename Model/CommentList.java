package Model;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Kam Kar Hou
 * Purpose: A list of comments will have several comments from comments.json which implements a parser from JsonList.java
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
     */
    public ArrayList<CommentModel> getComments(){
        return comments;
    }
    
    /**
     * Kam Kar Hou
     * Purpose: This is to return a comment id with the format 0001 to 9999
     * to be added with a string "C" to produce a unique ID e.g. C0001
     */
    public String generateCommentIdNum(){
        return String.format("%04d", comments.size() + 1);
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
     * Purpose: A getter for a specific comment id in an arraylist of comments
     */
    @Override
    public CommentModel getItem(String id) {
        for (int i = 0; i < comments.size(); i++){
            CommentModel tempComment = comments.get(i);
            if (tempComment.getCommentID() == id){
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

}





