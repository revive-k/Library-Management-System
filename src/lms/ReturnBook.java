package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;

public class ReturnBook extends JPanel{
    private JLabel label1;
    private JLabel label3;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField field1;
    private JTextField field3;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;
    private JButton returnbutton;
    private JButton finebutton;
    private JLabel lsearch;
    private JTextField tsearch;
    private JButton bsearch;
    private JLabel dob;
    private JLabel fine;
    private JTextField tfine;
    private String rdate = "";
    
    void getrdate(String date){
        rdate = date;
    }
            
    public ReturnBook(){
        init();
        add(label1);
        add(label3);
        add(label5);
        add(label6);
        add(label7);
        add(field1);
        add(field3);
        add(field5);
        add(field6);
        add(field7);
        add(returnbutton);
        add(finebutton);
        add(dob);
        add(lsearch);
        add(tsearch);
        add(bsearch);
        add(fine);
        add(tfine);
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
        finebutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==finebutton){
                    String getactreturndate = "";
                    String getexpreturndate = "";
                    getactreturndate = field7.getText();
                    getexpreturndate = rdate;
                    try{
                       if(getactreturndate.equals("") || getexpreturndate.equals("")){
                           JOptionPane.showMessageDialog(null, "Date is empty!", "Alert",JOptionPane.WARNING_MESSAGE);
                       } 
                       else{
                           java.util.Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(getexpreturndate);
                           java.util.Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(getactreturndate);
                           long ms = d2.getTime()-d1.getTime();
                           long days = TimeUnit.MILLISECONDS.toDays(ms);
                           if(days<0)
                               days = 0;
                           tfine.setText(String.valueOf(days));
                       }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        returnbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==returnbutton){
                    String getbid = "";
                    String getsid = "";
                    String getissuedate = "";
                    String getactualreturndate = "";
                    String getfine = "";
                    int bcount = 0;
                    getbid = field1.getText();
                    getsid = field3.getText();
                    getissuedate = field5.getText();
                    getactualreturndate = field7.getText();
                    getfine = tfine.getText();
                    try{
                        if(getbid.equals("") || getsid.equals("") || getissuedate.equals("") || getactualreturndate.equals("") || getfine.equals("")){
                            JOptionPane.showMessageDialog(null, "Some fields are empty. Get them filled!", "Alert",JOptionPane.WARNING_MESSAGE); 
                        }
                        else{
                            putpastinfo(getbid,getsid,getissuedate,getactualreturndate,getfine);
                            DBManager.getUpdate("DELETE from current_records where Book_id = '"+getbid+"'");
                            DBManager.getUpdate("UPDATE book_details SET Availability = 'YES' where Book_id = '"+getbid+"'");
                            ResultSet rs = DBManager.getResultSet("SELECT bookcount from student_details where st_id = '"+getsid+"'");
                            if(rs.next()){
                                bcount = rs.getInt("bookcount");
                            }
                            bcount = bcount - 1;
                            DBManager.getUpdate("UPDATE student_details SET bookcount = '"+bcount+"' where st_id = '"+getsid+"'");
                            JOptionPane.showMessageDialog(null, "Successfully Returned!");
                            refresh();
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
        String getid;
        getid = tsearch.getText();
        try{
            ResultSet rs = DBManager.getResultSet("SELECT * from current_records where Book_id = '"+getid+"'");
            if(rs.next()){
                field1.setText(rs.getString("Book_id"));
                field3.setText(rs.getString("st_id"));
                field5.setText(rs.getString("issue_date"));
                field6.setText(rs.getString("exp_return_date"));
                ReturnBook.this.getrdate(rs.getString("exp_return_date")); 
            }
            else if(getid.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                refresh();
            }
            else{
                JOptionPane.showMessageDialog(null, "This Book is not issued yet!", "Alert",JOptionPane.WARNING_MESSAGE);
                refresh();
            }
            tfine.setText("");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void refresh(){
        field1.setText("");
        field3.setText("");
        field5.setText("");
        field6.setText("");
        rdate = "";
        tsearch.setText(""); 
        tfine.setText("");
    }
    
    public void putpastinfo(String bkid, String sdid, String issued, String returnd, String fine){
        try{
            DBManager.getUpdate("INSERT INTO past_records VALUES('"+bkid+"','"+sdid+"','"+issued+"','"+returnd+"','"+fine+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        lsearch = new JLabel("Book ID");
        lsearch.setBounds(320, 30, 100, 14);
        
        tsearch = new JTextField();
        tsearch.setBounds(420, 30, 160, 20);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(600, 30, 80, 20);
        
        label1 = new JLabel("Book ID");
        label1.setBounds(65, 100, 100, 14);
        
        label3 = new JLabel("Borrower ID");
        label3.setBounds(65, 165, 100, 14);
        
        label5 = new JLabel("Date of Issue");
        label5.setBounds(600, 100, 100, 14);
        
        label6 = new JLabel("Last Date to Return");
        label6.setBounds(600, 165, 120, 14);
        
        label7 = new JLabel("Actual Return Date");
        label7.setBounds(286, 230, 120, 14);
        
        field1 = new JTextField();
        field1.setBounds(215, 98, 180, 20);
        field1.setEditable(false);
        
        field3 = new JTextField();
        field3.setBounds(215, 163, 180, 20);
        field3.setEditable(false);
        
        field5 = new JTextField();
        field5.setBounds(750, 98, 180, 20);
        field5.setEditable(false);
        
        field6 = new JTextField();
        field6.setBounds(750, 163, 180, 20);
        field6.setEditable(false);
        
        field7 = new JTextField();
        field7.setBounds(420, 228, 160, 20);
        field7.setEditable(false); 
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String s = sdf.format(date);
        field7.setText(s);
        
        dob = new JLabel("(YYYY-MM-DD)");
        dob.setBounds(600, 227, 180, 20);
        
        returnbutton = new JButton("Return");
        returnbutton.setBounds(425, 358, 89, 23);
        
        fine = new JLabel("Fine");
        fine.setBounds(370, 300, 100, 14);
        
        tfine = new JTextField();
        tfine.setBounds(420, 298, 120, 20);
        tfine.setEditable(false);
        
        finebutton = new JButton("Calculate Fine");
        finebutton.setBounds(550, 298, 120, 20);
    }
}
