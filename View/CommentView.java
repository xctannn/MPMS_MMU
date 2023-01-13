package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// A View class for the commenting system
// Author: Kam Kar Hou
public class CommentView extends JPanel{

    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());
    private JLabel commentTitleLabel = new JLabel("Class Comments: ");
    private JPanel commentBlock = new JPanel();
    private JButton submitButton = new JButton("Submit");
    private JTextArea commentArea= new JTextArea();
    private JScrollPane commentScroll= new JScrollPane(commentArea);
    private JPanel submitButtonWrapper = new JPanel();
    private JPanel commentBoxWrapper = new JPanel();
    private final Dimension COMMENT_SCROLL_DIMENTSION = new Dimension(720,400);
    private final Dimension COMMENT_SCROLL_PANE_DIMENSION = new Dimension(650,100);
    private JScrollPane commentsScrollPane = new JScrollPane(commentBlock,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    // A default constructor to call the GUI of the commenting system
    public CommentView(){

        // Properties for the Fonts, Panels, ScrollPane, Wrappers and Layout
        this.setLayout(new GridLayout(1,1));
        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));
        JPanel commentTablePanel = new JPanel();
        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleWrapper.add(commentTitleLabel);
        commentTablePanel.setLayout(new BoxLayout(commentTablePanel,BoxLayout.Y_AXIS));
        commentTablePanel.setBorder(BorderFactory.createEtchedBorder());
        commentTablePanel.add(titleWrapper,BorderLayout.EAST);
        commentsScrollPane.setPreferredSize(COMMENT_SCROLL_DIMENTSION);
        commentsScrollPane.setMinimumSize(COMMENT_SCROLL_DIMENTSION);
        commentsScrollPane.setMaximumSize(COMMENT_SCROLL_DIMENTSION);
        commentTablePanel.add(commentsScrollPane,BorderLayout.CENTER);
        submitButtonWrapper.add(submitButton);
        commentScroll.setPreferredSize(COMMENT_SCROLL_PANE_DIMENSION);
        commentScroll.setMaximumSize(COMMENT_SCROLL_PANE_DIMENSION);
        commentScroll.setMinimumSize(COMMENT_SCROLL_PANE_DIMENSION);


        // This JPanel Table is the container for all the comments in the database
        // it uses the JScrollPanel to allow scrolling if there are a lot of comments in the database
        commentBoxWrapper.add(commentScroll,BorderLayout.CENTER);
        // This is the comment area to allow the user to input comments
        commentTablePanel.add(commentBoxWrapper,  BorderLayout.SOUTH);
        // This is the submit button to for the user to click to confirm submission
        commentTablePanel.add(submitButtonWrapper, BorderLayout.SOUTH);

        tableView.add(commentTablePanel,BorderLayout.CENTER);
        this.add(tableView);
    }

    // Getter for the comment submit button 
    public JButton getSubmitButton() {
        return submitButton;
    }

    // Getter for the commenting area which allow user to input text
    public JTextArea getCommentArea() {
        return commentArea;
    }

    // Getter for the Comment Block 
    public JPanel getCommentBlock(){
        return commentBlock;
    }

    // Getter for the comment scrollable pane which contains comment blocks inside
    public JScrollPane getcommentsScrollPane(){
        return commentsScrollPane;
    }
}
