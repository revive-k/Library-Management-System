package lms;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Home extends JFrame{
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JLabel head1;
    private JLabel head2;
    
    public Home(){
        //create items
        init();
        //Frame
        setTitle("Home - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        p1.add(b1);
        ImageIcon icon1 = new ImageIcon(new ImageIcon(this.getClass().getResource("images/book3.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        JLabel book = new JLabel();
        book.setIcon(icon1);
        book.setBounds(30, 25, 120, 120); 
        p1.add(book);
        p1.add(background());
        
        p2.add(b2);
        ImageIcon icon2 = new ImageIcon(new ImageIcon(this.getClass().getResource("images/student.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        JLabel student = new JLabel();
        student.setIcon(icon2);
        student.setBounds(35, 25, 120, 120); 
        p2.add(student);
        p2.add(background());
        
        p3.add(b3);
        ImageIcon icon3 = new ImageIcon(new ImageIcon(this.getClass().getResource("images/issue.png")).getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_DEFAULT));
        JLabel issue = new JLabel();
        issue.setIcon(icon3);
        issue.setBounds(30, 25, 120, 120); 
        p3.add(issue);
        p3.add(background());
        
        p4.add(b4);
        ImageIcon icon4 = new ImageIcon(new ImageIcon(this.getClass().getResource("images/report.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        JLabel report = new JLabel();
        report.setIcon(icon4);
        report.setBounds(35, 25, 120, 120); 
        p4.add(report);
        p4.add(background());
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(head1);
        add(head2);
        //Event Handling
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==b1){
                    new BookDetails();
                    dispose();
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==b2){
                    new StudentDetails();
                    dispose();
                }
            }
        });
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==b3){
                    new IssueBooks();
                    dispose();
                }
            }
        });
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==b4){
                    new GenerateReport();
                    dispose();
                }
            }
        });
        //visible
        add(background);
        setVisible(true);
    }
  
    public JLabel background(){
        JLabel panelback=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        panelback.setBounds(5, 5, 170, 182);
        return panelback;
    }
    
    public void init(){
        p1 = new JPanel();
        p1.setBounds(109, 317, 180, 192);
        p1.setLayout(null);
        
        p2 = new JPanel();
        p2.setBounds(416, 317, 180, 192);
        p2.setLayout(null);
        
        p3 = new JPanel();
        p3.setBounds(741, 317, 180, 192);
        p3.setLayout(null);
        
        p4 = new JPanel();
        p4.setBounds(1058, 317, 180, 192);
        p4.setLayout(null);
        
        b1 = new JButton("Book Details");
        b1.setBounds(25, 158, 125, 23);
        
        b2 = new JButton("Student Details");
        b2.setBounds(25, 158, 125, 23);
        
        b3 = new JButton("Issue Books");
        b3.setBounds(25, 158, 125, 23);
        
        b4 = new JButton("Generate Report");
        b4.setBounds(25, 158, 130, 23);
        
        head1 = new JLabel("Library Management System");
        head1.setForeground(Color.LIGHT_GRAY);
        head1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
        head1.setBounds(365, -200, 900, 600);
       
        head2 = new JLabel("Admin Console");
        head2.setForeground(Color.LIGHT_GRAY);
        head2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        head2.setBounds(535, -20, 400, 400);
    }
    
    public static void main(String[] args){
        Home obj = new Home();
    }
}
