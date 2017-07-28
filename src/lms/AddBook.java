package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;

public class AddBook extends JPanel{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JComboBox field4;
    private JTextField field5;
    private JTextField field6;
    private JButton button;
    private String getid;
    private String getname;
    private String getauthor;
    private String getcategory;
    private String getprice;
    private String getedition;
    
    public AddBook(){
        init();
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(field1);
        add(field2);
        add(field3);
        add(field4);
        add(field5);
        add(field6);
        add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==button){
                    getid = field1.getText();
                    getname = field2.getText();
                    getauthor = field3.getText();
                    getcategory = (String)field4.getSelectedItem();
                    getprice = field5.getText();
                    getedition = field6.getText();
                    if(isValidID(getid) && isValidPrice(getprice) && isValidEdition(getedition)){
                        try{
                            ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null, "This Book ID already exists!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else if(getid.equals("") || getname.equals("") || getauthor.equals("") || getcategory.equals("") || getprice.equals("") || getedition.equals("")){
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                String getavail = "YES";
                                putbookinfo(getid,getname,getauthor,getcategory,getprice,getedition,getavail);
                                JOptionPane.showMessageDialog(null, "Added Successfully!");
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        if(!isValidID(getid))
                            JOptionPane.showMessageDialog(null, "Please Enter correct Book ID. ID Format is any number between 0 to 99999999");
                        else if(!isValidPrice(getprice))
                            JOptionPane.showMessageDialog(null, "Please Enter correct Book Price");
                        else if(!isValidEdition(getedition))
                            JOptionPane.showMessageDialog(null, "Please Enter correct Book Edition(Number)");
                    }
                }
            }
        });
    }
    
    public static boolean isValidID(String s){
        try{
            int x = Integer.parseInt(s); 
            if(x>=0 && x<999999999){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static boolean isValidPrice(String s){
        try{
            Float x = Float.parseFloat(s); 
            if(x>=0.00 && x<999999999.00){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static boolean isValidEdition(String s){
        try{
            int x = Integer.parseInt(s); 
            if(x>=0 && x<1000){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    
    public void putbookinfo(String gid, String gname, String gauthor, String gcategory, String gprice, String gedition,String gavail){
        try{
            DBManager.getUpdate("INSERT INTO book_details VALUES('"+gid+"','"+gname+"','"+gauthor+"','"+gcategory+"','"+gprice+"','"+gedition+"','"+gavail+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        label1 = new JLabel("Book ID");
        label1.setBounds(95, 78, 100, 14);
        
        label2 = new JLabel("Book Name");
        label2.setBounds(95, 141, 100, 14);
        
        label3 = new JLabel("Author Name");
        label3.setBounds(95, 204, 100, 14);
        
        label4 = new JLabel("Category");
        label4.setBounds(632, 78, 100, 14);
        
        label5 = new JLabel("Price");
        label5.setBounds(632, 141, 100, 14);
        
        label6 = new JLabel("Edition");
        label6.setBounds(632, 204, 100, 14);
        
        field1 = new JTextField();
        field1.setBounds(212, 75, 180, 20);
        
        field2 = new JTextField();
        field2.setBounds(212, 138, 180, 20);
        
        field3 = new JTextField();
        field3.setBounds(212, 196, 180, 20);
        
        String category[] = {"Programming","Mathematics","Physics","Chemistry","Management","Biology","Inspirational","Journals","Others"};
        field4 = new JComboBox(category);
        field4.setBounds(748, 75, 180, 20);
        
        field5 = new JTextField();
        field5.setBounds(748, 138, 180, 20);
        
        field6 = new JTextField();
        field6.setBounds(748, 201, 180, 20);
        
        button = new JButton("Add");
        button.setBounds(475, 298, 89, 23);
    }
} 
