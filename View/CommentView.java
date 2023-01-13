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
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;


public class CommentView extends JPanel{


    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());


    private JLabel commentTitleLabel = new JLabel("Class Comments: ");

    // Comment Section Components
    private JPanel commentBlock = new JPanel();
    private JTextField commentField = new JTextField(); 
    private JButton submitButton = new JButton("Submit");
    private JTextArea commentArea= new JTextArea();
    private JScrollPane commentScroll= new JScrollPane(commentArea);
    private JPanel submitButtonWrapper = new JPanel();
    private JPanel commentBoxWrapper = new JPanel();
    private final Dimension COMMENT_SCROLL_DIMENTSION = new Dimension(720,400);
    private final Dimension COMMENT_SCROLL_PANE_DIMENSION = new Dimension(650,100);


    public CommentView(){
        this.setLayout(new GridLayout(1,1));

        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));

        //Past Comments Table
        JPanel commentTablePanel = new JPanel();
        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleWrapper.add(commentTitleLabel);
        commentTablePanel.setLayout(new BoxLayout(commentTablePanel,BoxLayout.Y_AXIS));
        commentTablePanel.setBorder(BorderFactory.createEtchedBorder());

        commentTablePanel.add(titleWrapper,BorderLayout.EAST);
        JScrollPane commentsScrollPane = new JScrollPane(commentBlock,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        commentsScrollPane.setPreferredSize(COMMENT_SCROLL_DIMENTSION);
        commentsScrollPane.setMinimumSize(COMMENT_SCROLL_DIMENTSION);
        commentsScrollPane.setMaximumSize(COMMENT_SCROLL_DIMENTSION);
        commentTablePanel.add(commentsScrollPane,BorderLayout.CENTER);


        submitButtonWrapper.add(submitButton);
        commentScroll.setPreferredSize(COMMENT_SCROLL_PANE_DIMENSION);
        commentScroll.setMaximumSize(COMMENT_SCROLL_PANE_DIMENSION);
        commentScroll.setMinimumSize(COMMENT_SCROLL_PANE_DIMENSION);
        commentBoxWrapper.add(commentScroll,BorderLayout.CENTER);
        commentTablePanel.add(commentBoxWrapper,  BorderLayout.SOUTH);
        commentTablePanel.add(submitButtonWrapper, BorderLayout.SOUTH);
        tableView.add(commentTablePanel,BorderLayout.CENTER);

        this.add(tableView);

    }

    public JTextField getCommentField() {
        return commentField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextArea getCommentArea() {
        return commentArea;
    }
    public JPanel getCommentBlock(){
        return commentBlock;
    }
}
