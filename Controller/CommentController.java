// package Controller;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

// import javax.swing.JTable;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import javax.swing.table.DefaultTableModel;
// import javax.swing.table.TableColumnModel;
// import javax.xml.stream.events.Comment;

// import Model.CommentList;
// import Model.Lecturer;
// import Model.Project;
// import Model.ProjectList;
// import Model.Student;
// import Model.User;
// import View.CommentView;
// import View.ProjectView;

// public class CommentController {
//     private User user;
//     private Project projectModel;
//     private ProjectList projectList;
//     private ProjectView projectView;
//     private Comment commentModel;
//     private CommentView commentView;
//     private CommentList commentList;

//     //Comment Panel
//     public CommentController(Student user, Project model, CommentView view){
//         this.user = user;
//         this.projectModel = model;
//         this.commentView = view;
//         this.commentList = new CommentList(model);
//         populateCommentTable();
//     }

//     public void populateCommentTable(){
//         ArrayList<Comment> comments = commentList.getComments();
//         DefaultTableModel commentTableModel = new DefaultTableModel(commentView.getColumnNames(),0);

//         for(int i = 0; i < comments.size(); i++){
//             Comment comment = (Comment) comments.get(i);
//             String commentID = comments.getID();
//         }
//     }
// }
