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

public class CommentView {

    private String[] columnNames = {"ID", "UserID","Username","Comment"};

    public JPanel wrapper = new JPanel();
    // CardLayout (switch to comment sections)
    private JPanel panelCont = new JPanel();
    private JButton commentPanelButton = new JButton("To Comment Section");
    private JButton projectPanelButton = new JButton("To View Board");
    // private Dimension d = new Dimension(30,15);
    private CardLayout cl = new CardLayout();

    // Comment Section Components
    private JLabel commentTitleLabel = new JLabel("Class Comments: ");
    private JButton submitCommentButton = new JButton("Submit");
    private JTable commentTable = new JTable();


    public CommentView(){
        wrapper.setLayout(new GridLayout(1,2));
        
        panelCont.setLayout(cl);
        JPanel mainPanel = new JPanel();
        //Comment Section
        commentTitleLabel.setFont(new Font(commentTitleLabel.getFont().toString(), Font.BOLD, 16));
        mainPanel.add(commentTitleLabel, BorderLayout.CENTER);
        // projectPanelButton.setEnabled(false);
        // commentPanelButton.setEnabled(false);

        //Past Comments Table
        JPanel commentTablePanel = new JPanel();
        commentTablePanel.add(new JScrollPane(commentTable));
        commentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainPanel.add(commentTablePanel,BorderLayout.CENTER);

        JPanel oneButton = new JPanel();
        oneButton.add(commentPanelButton);
        panelCont.add(commentPanelButton,"1");
        mainPanel.add(oneButton,BorderLayout.SOUTH);
        commentPanelButton.setVisible(true);



        //Comment Section Text Box Setup
        JPanel commentSectionPanel = new JPanel();
        JTextField textBox = new JTextField();
        commentSectionPanel.setLayout(new BoxLayout(commentSectionPanel,BoxLayout.PAGE_AXIS));
        commentSectionPanel.add(commentTitleLabel);
        commentSectionPanel.add(textBox, Component.BOTTOM_ALIGNMENT);
        commentSectionPanel.add(Box.createHorizontalStrut(15));
        commentSectionPanel.add(submitCommentButton, Component.RIGHT_ALIGNMENT);
        submitCommentButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        commentSectionPanel.add(Box.createHorizontalGlue());
        commentSectionPanel.add(projectPanelButton);
        panelCont.add(projectPanelButton,"2");

        commentPanelButton.addActionListener(new actionListener());
        projectPanelButton.addActionListener(new actionListener());

        //Default Panel
        cl.show(panelCont,"1");
        mainPanel.add(panelCont);
        
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
}
