package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class CommentList implements JsonList<CommentModel> {
    JsonParser<CommentData> parser = new JsonParser<>("/Database/comments.json", CommentData.class);
    private ArrayList<CommentModel> comments;
    private CommentData commentData;
    private int commentCount;

    public CommentList(){
        setList();
        this.commentCount = commentData.getCommentCount();
    }
    //CommentList will contain all the comments of the project
    public ArrayList<CommentModel> getFilteredComments(Project project){
        ArrayList<CommentModel> filteredComments= new ArrayList<>(comments);
        for (int i = 0; i < filteredComments.size(); i++){
            CommentModel comment = filteredComments.get(i);
            String projectID = comment.getProjectID();
            if (!(projectID.equals(project.getId()))){
                filteredComments.remove(i);
                i--;
            }

        }
        return filteredComments;
    }

    public ArrayList<CommentModel> getComments(){
        return comments;
    }
    public void saveProjectCountIncrement(){
        commentCount++;
        commentData.setCommentCount(commentCount);

        save();
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

    public String generateCode(){
        return String.format("%04d", commentCount + 1);
    }
    // For adding new json data
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

    @Override
    public void setList() {
        try {
            this.commentData = parser.deserializeObject();
            this.comments = commentData.getComments();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void save() {
        try {
            parser.serializeObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    //TEST DATA ENTRY
    
    // public static void main(String[] args){
    //     Lecturer lec = new Lecturer("L007", "Mickey Deez", "DeezNutz123");
    //     Student stu = new Student("S001", "Goofy Ass", "HoeDuck", "Hotel Management", "P001");
    //     User user = new User("L0007","Danish","Abc123");
    //     Project pro = new Project("P001","Manage Hotel", "Hotel Management", "Assign people to love hotel room 1001", lec, stu, true, true, false);

    //     CommentModel c1 = new CommentModel("C0001", pro,user,"Sir do we have class tmrw?" );
    //     CommentList cl = new CommentList();
    //     cl.writeAllData(c1);
    // }

}





