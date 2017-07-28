package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.event.*;
import java.util.Date;

public class BorrowBook extends JPanel{
    private JButton issue;
    private JLabel line;
    private String bookid = "";
    private String sid = "";
    
    void getbookID(String id){
        bookid = id;
    }
    
    void getstudentID(String id){
        sid = id;
    }
    
    public BorrowBook(){
        Bookdetails();
        Borrowerdetails();
        issue = new JButton("Issue Book");
        issue.setBounds(485, 370, 100, 25);
        add(issue);
        line = new JLabel("<HTML>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br>|<br></HTML>");
        line.setBounds(530,10,5,350);
        add(line);
        issue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==issue){
                    String avail="";
                    int bcount=0;
                    String issuedate = "";
                    String expreturndate = "";
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT Availability from book_details where Book_id = '"+bookid+"'");
                        if(rs.next()){
                            avail = rs.getString("Availability");
                        } 
                        rs = DBManager.getResultSet("SELECT bookcount from student_details where st_id = '"+sid+"'");
                        if(rs.next()){
                            bcount = rs.getInt("bookcount");
                        }
                        if(bookid.equals("") || sid.equals(""))
                            JOptionPane.showMessageDialog(null, "Please select valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        else if(avail.equals("YES") && bcount<4){
                            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();
                            issuedate = sdf.format(date);
                            expreturndate = issuedate;
                            Calendar c = Calendar.getInstance();
                            c.setTime(sdf.parse(expreturndate));
                            c.add(Calendar.DATE, 30);
                            expreturndate = sdf.format(c.getTime());
                            putcurrentinfo(bookid,sid,issuedate,expreturndate);
                            DBManager.getUpdate("UPDATE book_details SET Availability = 'NO' where Book_id = '"+bookid+"'");
                            bcount = bcount + 1;
                            DBManager.getUpdate("UPDATE student_details SET bookcount = '"+bcount+"' where st_id = '"+sid+"'");
                            JOptionPane.showMessageDialog(null, "Successfully Issued!");
                        }
                        else{
                            if(avail.equals("NO"))
                                JOptionPane.showMessageDialog(null, "This Book is not available!", "Alert",JOptionPane.WARNING_MESSAGE);
                            if(bcount >= 4)
                                JOptionPane.showMessageDialog(null, "This Student has already taken four books. He can't borrow more books!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
    public void putcurrentinfo(String bkid, String sdid, String issued, String returnd){
        try{
            DBManager.getUpdate("INSERT INTO current_records VALUES('"+bkid+"','"+sdid+"','"+issued+"','"+returnd+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void refresh(JTextField s1, JTextField s2, JTextField s3, JTextField s4, JTextField s5, JTextField s6, JTextField s7){
        s1.setText("");
        s2.setText("");
        s3.setText("");
        s4.setText("");
        s5.setText("");
        s6.setText("");
        s7.setText("");
    }
    
    public void Bookdetails(){
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JLabel label4;
        JLabel label5;
        JLabel label6;
        JLabel label7;
        JTextField field1;
        JTextField field2;
        JTextField field3;
        JTextField field4;
        JTextField field5;
        JTextField field6;
        JTextField field7;
        JLabel lsearch;
        JTextField tsearch;
        JButton bsearch;

        setLayout(null);

        lsearch = new JLabel("Book ID");
        lsearch.setBounds(95, 30, 100, 14);

        tsearch = new JTextField();
        tsearch.setBounds(170, 30, 150, 20);

        bsearch = new JButton("Search");
        bsearch.setBounds(350, 30, 80, 20);

        label1 = new JLabel("Book ID");
        label1.setBounds(50, 100, 100, 14);

        label2 = new JLabel("Book Name");
        label2.setBounds(50, 165, 100, 14);

        label3 = new JLabel("Author Name");
        label3.setBounds(50, 230, 100, 14);

        label4 = new JLabel("Category");
        label4.setBounds(50, 295, 100, 14);

        label5 = new JLabel("Price");
        label5.setBounds(300, 100, 100, 14);

        label6 = new JLabel("Edition");
        label6.setBounds(300, 165, 100, 14);

        label7 = new JLabel("Availability");
        label7.setBounds(300, 230, 100, 14);

        field1 = new JTextField();
        field1.setBounds(135, 98, 140, 20);
        field1.setEditable(false);

        field2 = new JTextField();
        field2.setBounds(135, 163, 140, 20);
        field2.setEditable(false);

        field3 = new JTextField();
        field3.setBounds(135, 228, 140, 20);
        field3.setEditable(false);

        field4 = new JTextField();
        field4.setBounds(135, 293, 140, 20);
        field4.setEditable(false);

        field5 = new JTextField();
        field5.setBounds(380, 98, 120, 20);
        field5.setEditable(false);

        field6 = new JTextField();
        field6.setBounds(380, 163, 120, 20);
        field6.setEditable(false);

        field7 = new JTextField();
        field7.setBounds(380, 228, 120, 20);
        field7.setEditable(false);


        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(field1);
        add(field2);
        add(field3);
        add(field4);
        add(field5);
        add(field6);
        add(field7);
        add(lsearch);
        add(tsearch);
        add(bsearch);
        
        bsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==bsearch){
                    String getid;
                    getid = tsearch.getText();
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
                        if(rs.next()){
                            field1.setText(rs.getString("Book_id"));
                            field2.setText(rs.getString("Book_name"));
                            field3.setText(rs.getString("Author_name"));
                            field4.setText(rs.getString("Category"));
                            field5.setText(rs.getString("Price"));
                            field6.setText(rs.getString("Edition"));
                            field7.setText(rs.getString("Availability"));
                            BorrowBook.this.getbookID(getid);
                        }
                        else if(getid.equals("")){
                            JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                            refresh(field1,field2,field3,field4,field5,field6,field7);
                            bookid = "";
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Book ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                            refresh(field1,field2,field3,field4,field5,field6,field7);
                            bookid = "";
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        
        tsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String getid;
                getid = tsearch.getText();
                try{
                    ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getid+"'");
                    if(rs.next()){
                        field1.setText(rs.getString("Book_id"));
                        field2.setText(rs.getString("Book_name"));
                        field3.setText(rs.getString("Author_name"));
                        field4.setText(rs.getString("Category"));
                        field5.setText(rs.getString("Price"));
                        field6.setText(rs.getString("Edition"));
                        field7.setText(rs.getString("Availability"));
                        BorrowBook.this.getbookID(getid);
                    }
                    else if(getid.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        refresh(field1,field2,field3,field4,field5,field6,field7);
                        bookid = "";
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This Book ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                        refresh(field1,field2,field3,field4,field5,field6,field7);
                        bookid = "";
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
   
    public void Borrowerdetails(){
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JLabel label4;
        JLabel label5;
        JLabel label6;
        JLabel label7;
        JTextField field1;
        JTextField field2;
        JTextField field3;
        JTextField field4;
        JTextField field5;
        JTextField field6;
        JTextField field7;
        JLabel lsearch;
        JTextField tsearch;
        JButton bsearch;

        setLayout(null);

        lsearch = new JLabel("Student ID");
        lsearch.setBounds(645, 30, 100, 14);

        tsearch = new JTextField();
        tsearch.setBounds(730, 30, 150, 20);

        bsearch = new JButton("Search");
        bsearch.setBounds(905, 30, 80, 20);

        label1 = new JLabel("Student ID");
        label1.setBounds(565, 100, 100, 14);

        label2 = new JLabel("Name");
        label2.setBounds(565, 165, 100, 14);

        label3 = new JLabel("Email");
        label3.setBounds(565, 230, 100, 14);

        label4 = new JLabel("Contact No.");
        label4.setBounds(565, 295, 100, 14);

        label5 = new JLabel("DOB");
        label5.setBounds(815, 100, 100, 14);

        label6 = new JLabel("Department");
        label6.setBounds(815, 165, 100, 14);

        label7 = new JLabel("Year");
        label7.setBounds(815, 230, 100, 14);

        field1 = new JTextField();
        field1.setBounds(640, 98, 140, 20);
        field1.setEditable(false);

        field2 = new JTextField();
        field2.setBounds(640, 163, 140, 20);
        field2.setEditable(false);

        field3 = new JTextField();
        field3.setBounds(640, 228, 140, 20);
        field3.setEditable(false);

        field4 = new JTextField();
        field4.setBounds(640, 293, 140, 20);
        field4.setEditable(false);

        field5 = new JTextField();
        field5.setBounds(900, 98, 120, 20);
        field5.setEditable(false);

        field6 = new JTextField();
        field6.setBounds(900, 163, 120, 20);
        field6.setEditable(false);

        field7 = new JTextField();
        field7.setBounds(900, 228, 120, 20);
        field7.setEditable(false);


        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(field1);
        add(field2);
        add(field3);
        add(field4);
        add(field5);
        add(field6);
        add(field7);
        add(lsearch);
        add(tsearch);
        add(bsearch);
        
        bsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==bsearch){
                    String getid;
                    getid = tsearch.getText();
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
                        if(rs.next()){
                            field1.setText(rs.getString("st_id"));
                            field2.setText(rs.getString("st_name"));
                            field3.setText(rs.getString("st_email"));
                            field4.setText(rs.getString("st_contactnumber"));
                            field5.setText(rs.getString("st_DOB"));
                            field6.setText(rs.getString("st_dept"));
                            field7.setText(rs.getString("st_year"));
                            BorrowBook.this.getstudentID(getid);
                        }
                        else if(getid.equals("")){
                            JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                            refresh(field1,field2,field3,field4,field5,field6,field7);
                            sid = "";
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Student ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                            refresh(field1,field2,field3,field4,field5,field6,field7);
                            sid = "";
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        
        tsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String getid;
                getid = tsearch.getText();
                try{
                    ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
                    if(rs.next()){
                        field1.setText(rs.getString("st_id"));
                        field2.setText(rs.getString("st_name"));
                        field3.setText(rs.getString("st_email"));
                        field4.setText(rs.getString("st_contactnumber"));
                        field5.setText(rs.getString("st_DOB"));
                        field6.setText(rs.getString("st_dept"));
                        field7.setText(rs.getString("st_year"));
                        BorrowBook.this.getstudentID(getid);
                    }
                    else if(getid.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        refresh(field1,field2,field3,field4,field5,field6,field7);
                        sid = "";
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This Student ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                        refresh(field1,field2,field3,field4,field5,field6,field7);
                        sid = "";
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

} 
