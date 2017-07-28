package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import static lms.AddBook.isValidEdition;
import static lms.AddBook.isValidID;
import static lms.AddBook.isValidPrice;

public class UpdateBook extends JPanel{
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
    private JButton updatebutton;
    private JButton deletebutton;
    private JLabel search;
    private JTextField tsearch;
    private JButton bsearch;
    private String getid;
    private String getname;
    private String getauthor;
    private String getcategory;
    private String getprice;
    private String getedition;
    
    public UpdateBook(){
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
        add(updatebutton);
        add(deletebutton);
        add(search);
        add(tsearch);
        add(bsearch);
        bsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==bsearch){
                    task();
                }
            }
        });
        tsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                task();
            }
        });
        updatebutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==updatebutton){
                    getid = field1.getText();
                    getname = field2.getText();
                    getauthor = field3.getText();
                    getcategory = (String)field4.getSelectedItem();
                    getprice = field5.getText();
                    getedition = field6.getText();
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
                        if(getid.equals("") || getname.equals("") || getauthor.equals("") || getcategory.equals("") || getprice.equals("") || getedition.equals("")){
                            if(getid.equals(""))
                                JOptionPane.showMessageDialog(null, "Please Enter Book ID!", "Alert",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else if((!isValidID(getid)) || (!isValidPrice(getprice)) || (!isValidEdition(getedition))){
                            if(!isValidID(getid))
                            JOptionPane.showMessageDialog(null, "Please Enter correct Book ID. ID Format is any number between 0 to 99999999");
                            else if(!isValidPrice(getprice))
                                JOptionPane.showMessageDialog(null, "Please Enter correct Book Price");
                            else if(!isValidEdition(getedition))
                                JOptionPane.showMessageDialog(null, "Please Enter correct Book Edition(Number)");
                        }
                        else if(rs.next()){
                            DBManager.getUpdate("UPDATE book_details SET Book_name = '"+getname+"',Author_name = '"+getauthor+"',Category = '"+getcategory+"',Price = '"+getprice+"',Edition = '"+getedition+"' where Book_id = '"+getid+"'");
                            JOptionPane.showMessageDialog(null, "Successfully Updated!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Book ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        deletebutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==deletebutton){
                    getid = field1.getText();
                    getname = field2.getText();
                    getauthor = field3.getText();
                    getcategory = (String)field4.getSelectedItem();
                    getprice = field5.getText();
                    getedition = field6.getText();
                    try{
                        if(getid.equals("") || getname.equals("") || getauthor.equals("") || getcategory.equals("") || getprice.equals("") || getedition.equals("")){
                            if(getid.equals(""))
                                JOptionPane.showMessageDialog(null, "Please Enter Book ID!", "Alert",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            ResultSet rs = DBManager.getResultSet("SELECT * from current_records where Book_id = '"+getid+"'");
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null, "This book is currently borrowed. You can't delete it!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
                                if(rs.next()){
                                    DBManager.getUpdate("DELETE from book_details where Book_id = '"+getid+"'");
                                    JOptionPane.showMessageDialog(null, "Successfully Deleted!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This Book ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();  
                    }
                }
            }
        });
    }
    
    public void task(){
        getid = tsearch.getText();
        try{
            ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
            if(rs.next()){
                field1.setText(rs.getString("Book_id"));
                field1.setEditable(false);
                field2.setText(rs.getString("Book_name"));
                field3.setText(rs.getString("Author_name"));
                field4.setSelectedItem(rs.getString("Category"));
                field5.setText(rs.getString("Price"));
                field6.setText(rs.getString("Edition"));
            }
            else if(getid.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                refresh();
            }
            else{
                JOptionPane.showMessageDialog(null, "This Book ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                refresh();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void refresh(){
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setSelectedItem("Programming");
        field5.setText("");
        field6.setText("");
    }
    
    public void init(){
        setLayout(null);
        
        search = new JLabel("Book ID");
        search.setBounds(423, 37, 46, 14);
        
        tsearch = new JTextField();
        tsearch.setBounds(515, 34, 130, 20);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(470, 75, 89, 22);
        
        label1 = new JLabel("Book ID");
        label1.setBounds(95, 138, 100, 14);
        
        label2 = new JLabel("Book Name");
        label2.setBounds(95, 201, 100, 14);
        
        label3 = new JLabel("Author Name");
        label3.setBounds(95, 264, 100, 14);
        
        label4 = new JLabel("Category");
        label4.setBounds(632, 138, 100, 14);
        
        label5 = new JLabel("Price");
        label5.setBounds(632, 201, 100, 14);
        
        label6 = new JLabel("Edition");
        label6.setBounds(632, 264, 100, 14);
        
        field1 = new JTextField();
        field1.setBounds(212, 135, 180, 20);
        field1.setEditable(false);
        
        field2 = new JTextField();
        field2.setBounds(212, 198, 180, 20);
        
        field3 = new JTextField();
        field3.setBounds(212, 256, 180, 20);
        
        String category[] = {"Programming","Mathematics","Physics","Chemistry","Management","Biology","Inspirational","Journals","Others"};
        field4 = new JComboBox(category);
        field4.setBounds(748, 135, 180, 20);
        
        field5 = new JTextField();
        field5.setBounds(748, 198, 180, 20);
        
        field6 = new JTextField();
        field6.setBounds(748, 261, 180, 20);
        
        updatebutton = new JButton("Update");
        updatebutton.setBounds(375, 358, 89, 23);
        
        deletebutton = new JButton("Delete");
        deletebutton.setBounds(575, 358, 89, 23);
    }
} 
