package Model;

import java.util.ArrayList;

public class CommentData {
    private ArrayList<CommentModel> comments;
    private int commentCount;

    private CommentData(){}

    public ArrayList<CommentModel> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentModel> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    };

}
