package lms;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class First extends JFrame{
    private JButton admin;
    private JButton newadmin;
    private JLabel head1;
    private JLabel head2;
    private JLabel user;
    private JLabel newuser;
    private JLabel intro;
    
    public First(){
        //Creating items
        init();
        //Frame
        setTitle("Main - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon("C:\\LMS\\img6.jpg"));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        add(admin);
        add(newadmin);
        add(head1);
        add(head2);
        add(user);
        add(newuser);
        add(intro);
        //Event Handling
        EventHandler handler = new EventHandler();
        admin.addActionListener(handler);
        newadmin.addActionListener(handler);
        //visible
        add(background);
        setVisible(true);
    }
    
    public void init(){
        
        ImageIcon icon = new ImageIcon("C:\\LMS\\user1.png");
        Image img = icon.getImage() ;  
        Image newimg = img.getScaledInstance( 160, 160, Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        admin = new JButton("",icon);
        admin.setToolTipText("Click here to Login");
        admin.setBackground(Color.GRAY);
        admin.setBounds(460, 320, 149, 162);
        
        ImageIcon icon1 = new ImageIcon("C:\\LMS\\newuser1.png");
        Image img1 = icon1.getImage() ;  
        Image newimg1 = img1.getScaledInstance( 155, 155, Image.SCALE_SMOOTH ) ;  
        icon1 = new ImageIcon( newimg1 );
        newadmin = new JButton("",icon1);
        newadmin.setToolTipText("Click here to add admin");
        newadmin.setBackground(Color.GRAY);
        newadmin.setBounds(703, 320, 149, 162);
        
        head1 = new JLabel("Welcome to Library Management System");
        head1.setForeground(Color.LIGHT_GRAY);
        head1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        head1.setBounds(335, -200, 900, 600);
       
        head2 = new JLabel("Admin Console");
        head2.setForeground(Color.LIGHT_GRAY);
        head2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        head2.setBounds(535, -20, 400, 400);
        
        user = new JLabel("Existing User");
        user.setForeground(Color.WHITE);
        user.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,18));
        user.setBounds(480, 445, 150, 150);
        
        newuser = new JLabel("New User");
        newuser.setForeground(Color.WHITE);
        newuser.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,18));
        newuser.setBounds(735, 447, 150, 150);
        
        intro = new JLabel("- Developed By Vivek Ranjan");
        intro.setForeground(Color.LIGHT_GRAY);
        intro.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        intro.setBounds(1000, 420, 400, 400);
        
    }
    
    public class EventHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource()==admin){
                Login.status = 1;
                new Login();
                dispose();
            }
            if(event.getSource()==newadmin){
                new AddAdmin();
                dispose();
            }
        } 
    }
    
    public static void main(String[] args){
        First obj = new First();
        //JOptionPane.showMessageDialog(null, "Please go through the README.txt file before running this application. This application will require some configurations to be made.", "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
