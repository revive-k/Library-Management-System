package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class IssueBooks extends JFrame{
    private JTabbedPane tab;
    private JPanel borrowbook;
    private JPanel borrowlist;
    private JPanel returnbook;
    private JPanel searchbook;
    private JPanel pastborrowlist;
    private JButton home;
    private JButton logout;
    
    public IssueBooks(){
        //create items
        tab = new JTabbedPane();
        tab.setBounds(102, 89, 1054, 450);
        tab.setBackground(Color.LIGHT_GRAY);
        //Frame
        setTitle("Issue Books - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        borrowbook = new BorrowBook();
        borrowbook.setBackground(Color.LIGHT_GRAY);
        tab.add("Issue Book",borrowbook);
        
        searchbook = new SearchBook();
        searchbook.setBackground(Color.LIGHT_GRAY);
        tab.add("Search Book",searchbook);
        
        returnbook = new ReturnBook();
        returnbook.setBackground(Color.LIGHT_GRAY);
        tab.add("Return Book",returnbook);
        
        borrowlist = new BorrowerList();
        borrowlist.setBackground(Color.LIGHT_GRAY);
        tab.add("Current Borrower List",borrowlist);
        
        pastborrowlist = new PastBorrowerList();
        pastborrowlist.setBackground(Color.LIGHT_GRAY);
        tab.add("Past Borrower List",pastborrowlist);
        
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
    
    public void disposed(){
        dispose();
    }
    
    public static void main(String[] args){
        IssueBooks obj = new IssueBooks();
    }
}
