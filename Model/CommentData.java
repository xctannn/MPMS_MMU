package Model;

import java.util.ArrayList;

public class CommentData {
    private ArrayList<CommentModel> comments;
    private int projectCount;

    private CommentData(){}

    public ArrayList<CommentModel> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentModel> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return projectCount;
    }

    public void setCommentCount(int projectCount) {
        this.projectCount = projectCount;
    };

}
