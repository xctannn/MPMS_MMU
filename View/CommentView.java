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

public class CommentView {

    private String[] columnNames = {"UserID","Username","Comment"};

    public JPanel wrapper = new JPanel();
    private JPanel tableView = new JPanel(new BorderLayout());

    // CardLayout (switch to comment sections)
    private JPanel panelCont = new JPanel();
    private JButton commentPanelButton = new JButton("To Comment Section");
    private JButton projectPanelButton = new JButton("To View Board");

    private CardLayout cl = new CardLayout();

    // Comment Section Components
    private JLabel commentTitleLabel = new JLabel("Class Comments: ");

    private JTable commentTable = new JTable();
    private JTextField commentField = new JTextField(); 
    private JButton submitButton = new JButton("Submit");
    private JTextArea commentArea= new JTextArea();
    private JScrollPane commentScroll= new JScrollPane(commentArea);


    public CommentView(){
        wrapper.setLayout(new GridLayout(1,2));
        
        panelCont.setLayout(cl);
        JPanel mainPanel = new JPanel();
        //Comment Section
        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));
        mainPanel.add(commentTitleLabel, BorderLayout.CENTER);


        //Past Comments Table
        JPanel commentTablePanel = new JPanel();
        commentTablePanel.setLayout(new BoxLayout(commentTablePanel,BoxLayout.Y_AXIS));
        commentTablePanel.add(commentTitleLabel, BorderLayout.NORTH);
        commentTablePanel.add(new JScrollPane(commentTable), BorderLayout.NORTH);
        // commentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        commentTablePanel.add(commentField,  BorderLayout.NORTH);
        commentTablePanel.add(submitButton, BorderLayout.CENTER);
        submitButton.setPreferredSize(new Dimension(250,100));
        submitButton.setAlignmentX(300);
        commentTablePanel.add(commentScroll,BorderLayout.SOUTH);
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
        wrapper.add(tableView);
        wrapper.add(mainPanel);
        
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


    public String[] getColumnNames(){
        return columnNames;
    } 
    public JTable getCommentTable(){
        return commentTable;
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
}
