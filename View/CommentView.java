package View;

import java.awt.*;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import Model.Project;

public class CommentView extends JPanel {


    private JPanel tableView = new JPanel(new BorderLayout());

    // CardLayout (switch to comment sections)
    private JPanel panelCont = new JPanel();
    private JButton commentPanelButton = new JButton("To Comment Section");
    private JButton projectPanelButton = new JButton("To View Board");


    private CardLayout cl = new CardLayout();

    // Comment Section Components
    private JLabel commentTitleLabel = new JLabel("Class Comments: ");

    private JPanel commentBlock = new JPanel();
    private JTextField commentField = new JTextField(); 
    private JButton submitButton = new JButton("Submit");
    private JTextArea commentArea= new JTextArea();
    private JScrollPane commentScroll= new JScrollPane(commentArea);
    private JPanel submitButtonWrapper = new JPanel();
    private JPanel commentBoxWrapper = new JPanel();


    public CommentView(){
        this.setLayout(new GridLayout(1,1));
        
        panelCont.setLayout(cl);
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1200,700));
        mainPanel.setMaximumSize(new Dimension(1200,700));
        mainPanel.setMinimumSize(new Dimension(1000,500));
        //Comment Section
        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));
        mainPanel.add(commentTitleLabel, BorderLayout.CENTER);


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
        // commentTablePanel.add(commentScroll,BorderLayout.SOUTH);
        tableView.add(commentTablePanel,BorderLayout.CENTER);

        JPanel oneButton = new JPanel();
        oneButton.add(commentPanelButton);
        panelCont.add(commentPanelButton,"1");
        mainPanel.add(oneButton,BorderLayout.SOUTH);
        commentPanelButton.setVisible(true);



        //Comment Section Text Box Setup
        JPanel commentSectionPanel = new JPanel();
        JTextField textBox = new JTextField();
        commentSectionPanel.setLayout(new BoxLayout(commentSectionPanel,BoxLayout.PAGE_AXIS));

        commentSectionPanel.add(Box.createHorizontalStrut(15));

        commentSectionPanel.add(Box.createHorizontalGlue());
        commentSectionPanel.add(projectPanelButton);
        panelCont.add(projectPanelButton,"2");
        mainPanel.add(commentSectionPanel,BorderLayout.PAGE_END);

        commentPanelButton.addActionListener(new actionListener());
        projectPanelButton.addActionListener(new actionListener());

        //Default Panel
        cl.show(panelCont,"1");
        mainPanel.add(panelCont);
        this.add(tableView);
        //wrapper.add(mainPanel);
        
    }

    // Universal cardlayout changer
    private class actionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if(src.equals(commentPanelButton)){
                cl.show(panelCont, "2");
            }
            if(src.equals(projectPanelButton)){
                cl.show(panelCont,"1");
            }
        }
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
