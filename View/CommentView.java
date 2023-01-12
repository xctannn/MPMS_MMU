package View;

import java.awt.*;
import java.awt.BorderLayout;
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
        commentTablePanel.add(new JScrollPane(commentBlock),BorderLayout.NORTH);
        commentBlock.setPreferredSize(new Dimension(600,500));

        submitButtonWrapper.add(submitButton);
        commentScroll.setPreferredSize(new Dimension(550,100));

        commentBoxWrapper.add(commentScroll);
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
