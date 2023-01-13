package Model;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A list of comments will have several comments from comments.json which implements a parser from JsonList.java
 * @author Kam Kar Hou
 * @version 1.0
 */
public class CommentList implements JsonList<CommentModel> {
    JsonParser<CommentModel> parser = new JsonParser<>("/Database/comments.json", CommentModel.class);
    private ArrayList<CommentModel> comments;

    /**
     * A default constructors that creates an arraylist object with the model of CommentModel.java
     * and reads the comments.java file into the arraylist comments.
     */
    public CommentList(){
        try{
            this.comments = parser.deserialize();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * A contructor that filters out the projects which are not the current project
     * to see the current project's comments
     * @param currentProject represents the project that is current viewed
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
     * Getter for Arraylist of comments
     * @return Arraylist of comments
     */
    public ArrayList<CommentModel> getComments(){
        return comments;
    }
    /**
     * This is to return a comment id with the format 0001 to 9999
     * to be added with a string "C" to produce a unique ID e.g. C0001
     * @return Comment Id Number
     */
    public String generateCommentIdNum(){
        return String.format("%04d", comments.size() + 1);
    }

    @Override
    public void addItem(CommentModel item) {
        this.comments.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A getter for a specific comment id in an arraylist of comments
     * @return comment
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

    @Override
    public void setList() {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * A method that saves the commentList into the database comments.json
     */
    @Override
    public void save() {
        // Write comments to file using JSON parser
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Database/comments.json"), this.comments);
        }catch(IOException ex){
            ex.printStackTrace();

        }

        
    }
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}





