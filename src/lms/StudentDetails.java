package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDetails extends JFrame{
    private JTabbedPane tab;
    private JPanel addstudent;
    private JPanel updatestudent;
    private JPanel viewstudent;
    private JButton home;
    private JButton logout;
    
    public StudentDetails(){
        //create items
        tab = new JTabbedPane();
        tab.setBounds(102, 89, 1054, 450);
        tab.setBackground(Color.LIGHT_GRAY);
        //Frame
        setTitle("Student Details - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        addstudent = new AddStudent();
        addstudent.setBackground(Color.LIGHT_GRAY);
        tab.add("Add Student",addstudent);
        updatestudent = new UpdateStudent();
        updatestudent.setBackground(Color.LIGHT_GRAY);
        tab.add("Update/Delete Student",updatestudent);
        viewstudent = new ViewStudent();
        viewstudent.setBackground(Color.LIGHT_GRAY);
        tab.add("View Student",viewstudent);
        
        ImageIcon icon = new ImageIcon(this.getClass().getResource("images/Home-icon.png"));
        java.awt.Image img = icon.getImage() ;  
        java.awt.Image newimg = img.getScaledInstance( 68, 68,  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        home = new JButton("",icon);
        home.setToolTipText("Home");
        home.setBounds(104, 486, 50, 50);
        add(home);
        
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("images/logout1.png"));
        java.awt.Image log = icon1.getImage() ;  
        newimg = log.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;  
        icon1 = new ImageIcon( newimg );
        logout = new JButton("",icon1);
        logout.setToolTipText("Logout");
        logout.setBounds(155, 486, 50, 50);
        add(logout);
        
        add(tab);
        //Event Handling
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==home){
                    new Home();
                    dispose();
                }
            }
        });
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==logout){
                    new First();
                    dispose();
                }
            }
        });
        //visible
        add(background);
        setVisible(true);
    }
   
    public static void main(String[] args){
        StudentDetails obj = new StudentDetails();
    }
}
