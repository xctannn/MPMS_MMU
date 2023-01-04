package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class CommentList implements JsonList<CommentModel> {
    JsonParser<CommentModel> parser = new JsonParser<>("/Database/comments.json", CommentModel.class);
    private ArrayList<CommentModel> comments;
    private Object getComments;

    public CommentList(){
        try{
            this.comments = parser.deserialize();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //CommentList will contain all the comments of the project
    public CommentList(Project project){
        try {
            this.comments = parser.deserialize();
            for (int i = 0; i < comments.size(); i++){
                CommentModel comment = comments.get(i);
                String projectID = comment.getProjectID();
                if (!(projectID.equals(project.getId()))){
                    comments.remove(i);
                    i--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    public ArrayList<CommentModel> getComments(){
        return comments;
    }

    public void writeAllData(CommentModel listComments){
        ObjectMapper om = new ObjectMapper();

        try{
            om.writerWithDefaultPrettyPrinter().writeValue(new File("Database/comments.json"), listComments);;
        }catch(JsonGenerationException e){
            e.printStackTrace();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
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

    //TEST DATA ENTRY
    
    // public static void main(String[] args){
    //     Lecturer lec = new Lecturer("L007", "Mickey Deez", "DeezNutz123");
    //     Student stu = new Student("S001", "Goofy Ass", "HoeDuck", "Hotel Management", "P001");
    //     Project pro = new Project("P001","Manage Hotel", "Hotel Management", "Assign people to love hotel room 1001", lec, stu, true, true, false);

    //     Comment c1 = new Comment("C1", pro, lec, stu, "Sir how do we access this file?");
    //     CommentList cl = new CommentList();
    //     cl.writeAllData(c1);
    // }

}




