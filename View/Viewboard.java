// package View;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JTable;
// import javax.swing.JTextArea;

// import Model.Project;

// public class Viewboard extends JFrame{
//     // The type can be changed to List<Project> projects
//     // [subject to change later]
// <<<<<<< Updated upstream
//     public void printListOfProjects(Project projects) {
//         System.out.println("Project: " + projects.getId() + 
//         " " + projects.getName() + 
//         " " + projects.getSpecialization() + 
//         " " + projects.getLecturer() + 
//         " " + projects.getContent() + 
//         " " + projects.getLecturer().getUsername() + 
//         " " + projects.getStudentAssigned().getUsername() + 
//         " " + projects.getIsAssigned() + 
// <<<<<<< HEAD
//         " " + projects.getIsActive() +
//         " " + projects.getFavourite());
// =======

//     // public void printListOfProjects(Project projects) {
//     //     System.out.println("Project: " + projects.getId() + 
//     //     " " + projects.getName() + 
//     //     " " + projects.getSpecialization() + 
//     //     " " + projects.getLecturer() + 
//     //     " " + projects.getContent() + 
//     //     " " + projects.getLecturer().getUsername() + 
//     //     " " + projects.getStudentAssigned().getUsername() + 
//     //     " " + projects.getIsAssigned() + 
//     //     " " + projects.getIsActive());
//     // }
    
//     private JLabel studentID = new JLabel("StudentID: ");
//     private JLabel studentName = new JLabel("Name: ");
//     private JLabel projectName = new JLabel(); 
//     private JLabel projectLecturer = new JLabel("Lecturer");
//     private JLabel projectSpecialization = new JLabel();
//     private JTextArea projectContent = (new JTextArea(5,10));
    
//     private JButton returnButton = new JButton("Return");


//     public Viewboard(){
//         JPanel ViewboardPanel = new JPanel();

//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setSize(600, 200);

//         ViewboardPanel.add(studentID);
//         ViewboardPanel.add(studentName);

//         JTable table = new JTable();

//         table.add(projectName);
//         table.add(projectLecturer);
//         table.add(projectSpecialization);
//         table.add(projectContent);
//         projectContent.setEditable(false);

//         //will add this later
//         //ViewboardPanel.add(returnButton);


//         this.add(ViewboardPanel);
// >>>>>>> Stashed changes
// =======
//         " " + projects.getIsActive());
// >>>>>>> main
//     }
// }
