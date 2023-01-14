// package View;

// import java.awt.*;
// import java.awt.CardLayout;
// import java.awt.BorderLayout;
// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.GridLayout;
// import java.awt.Font;
// import java.awt.event.*;
// import java.util.ArrayList;

// import javax.swing.BorderFactory;
// import javax.swing.Box;
// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
// import javax.swing.ListSelectionModel;
// import javax.swing.event.ListSelectionListener;


// import Model.Project;
// import Model.Report;

// public class ReportView {
//     //private ArrayList<String> selectionWheelOptions = new ArrayList<String>();
//     private JButton generateButton = new JButton("Generate"); //no
//     public JPanel generateWrapperPanel = new JPanel();

//     //view text file
//     //hailat kepala sakit woi
//     //button similan all here
//     public ReportView(){ // this need to remobe later on

//         generateWrapperPanel.setLayout(new GridLayout(1,1));
//         generateWrapperPanel.add(generateButton);
        
//     }

//     public static void displayErrorMessage(String message){
//         JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
//     }

//     public String getGenerateReportOptions(ArrayList<String> selectionWheelOptions){
    
//         Object[] wheelOptions = selectionWheelOptions.toArray();

//         Object selected =  JOptionPane.showInputDialog(null,
//                                                         "Please Select Options",
//                                                         "Project List Report Generation", 
//                                                         JOptionPane.QUESTION_MESSAGE, 
//                                                         null, 
//                                                         wheelOptions, 
//                                                         wheelOptions[0]);
        
//         if (selected == null){
//             return "";
//         }else return selected.toString();
//     }

//     //specialization wheel options
//     public String getSpecializationOptions(ArrayList<String> specializationWheelOptions){
    
//         Object[] specializationOptions = specializationWheelOptions.toArray();

//         Object selected =  JOptionPane.showInputDialog(null,
//                                                         "Select Specialization",
//                                                         "Specialization Options", 
//                                                         JOptionPane.QUESTION_MESSAGE, 
//                                                         null, 
//                                                         specializationOptions, 
//                                                         specializationOptions[0]);
        
//         if (selected == null){
//             return "";
//         }else return selected.toString();
//     }

//     //Lecturer wheel options
//     public String getLecturerOptions(ArrayList<String> selectionWheelOptions){
    
//         Object[] wheelOptions = selectionWheelOptions.toArray();

//         Object selected =  JOptionPane.showInputDialog(null,
//                                                         "Select Lecturer",
//                                                         "Lecturer Options",  
//                                                         JOptionPane.QUESTION_MESSAGE, 
//                                                         null, 
//                                                         wheelOptions, 
//                                                         wheelOptions[0]);
        
//         if (selected == null){
//             return "";
//         }else return selected.toString();
//     }

// 	public void addGenerateButtonListener(ActionListener generateButtonListener) {
//         generateButton.addActionListener(generateButtonListener);
//     }



// }

