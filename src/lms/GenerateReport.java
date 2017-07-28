package lms;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.io.*;
import java.sql.*;
import java.io.FileOutputStream;
import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReport extends JFrame{
    private JComboBox cb;
    private JButton button;
    private String getsearchtype;
    private JButton home;
    private JButton logout;
    
    public GenerateReport(){
        setTitle("Report Generator - Library Management System");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        
        String list[] = {"Book Details","Student Details","Current Borrow Records","Past Borrow Records","Individual Borrower Record(Current)","Individual Borrower Record(Past)","Particular Book Record(Current)","Particular Book Record(Past)"};
        cb = new JComboBox(list);
        cb.setBounds(450,300,400,30);
        cb.setBackground(Color.LIGHT_GRAY);
        add(cb);
        
        button = new JButton("Generate Report");
        button.setBounds(565,380,150,30);
        add(button);
          
        ImageIcon icon = new ImageIcon(this.getClass().getResource("images/Home-icon.png"));
        java.awt.Image img = icon.getImage() ;  
        java.awt.Image newimg = img.getScaledInstance( 68, 68,  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        home = new JButton("",icon);
        home.setToolTipText("Home");
        home.setBounds(590, 450, 50, 50);
        add(home);
        
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("images/logout1.png"));
        java.awt.Image log = icon1.getImage() ;  
        newimg = log.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;  
        icon1 = new ImageIcon( newimg );
        logout = new JButton("",icon1);
        logout.setToolTipText("Logout"); 
        logout.setBounds(640, 450, 50, 50);
        add(logout);
       
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
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==button){
                    getsearchtype = (String)cb.getSelectedItem();
                    try{
                        if(getsearchtype.equals("Book Details")){
                            ResultSet rs = DBManager.getResultSet("SELECT * from book_details");
                            Document doc = new Document();
                            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                            doc.open();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int num = rsmd.getColumnCount();
                            PdfPTable report_table = new PdfPTable(num);
                            PdfPCell table_cell;
                            String column[] = {"Book Id","Book Name","Author Name","Category","Price","Edition","Availability"};
                            for(int i=0;i<num;i++){
                                table_cell = new PdfPCell(new Phrase(column[i]));
                                report_table.addCell(table_cell);
                            }
                            while (rs.next()){  
                                for(int i=1;i<=num;i++){
                                    String s = rs.getString(i);
                                    table_cell = new PdfPCell(new Phrase(s));
                                    report_table.addCell(table_cell);
                                }
                            }
                            doc.add(report_table);                       
                            doc.close(); 
                            JOptionPane.showMessageDialog(null, "Successfully Generated!");
                        }
                        if(getsearchtype.equals("Student Details")){
                            ResultSet rs = DBManager.getResultSet("SELECT * from student_details");
                            Document doc = new Document();
                            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                            doc.open();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int num = rsmd.getColumnCount();
                            PdfPTable report_table = new PdfPTable(num);
                            PdfPCell table_cell;
                            String column[] = {"Student ID","Student Name","Student Email","Student Contactnumber","Student DOB","Student Department","Student Year","Books Taken"};
                            for(int i=0;i<num;i++){
                                table_cell = new PdfPCell(new Phrase(column[i]));
                                report_table.addCell(table_cell);
                            }
                            while (rs.next()) { 
                                for(int i=1;i<=num;i++){
                                    String s = rs.getString(i);
                                    table_cell = new PdfPCell(new Phrase(s));
                                    report_table.addCell(table_cell);
                                }
                            }
                            doc.add(report_table);                       
                            doc.close(); 
                            JOptionPane.showMessageDialog(null, "Successfully Generated!");
                        }
                        if(getsearchtype.equals("Current Borrow Records")){
                            ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,exp_return_date from current_records natural join book_details natural join student_details");
                            Document doc = new Document();
                            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                            doc.open();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int num = rsmd.getColumnCount();
                            PdfPTable report_table = new PdfPTable(num);
                            PdfPCell table_cell;
                            String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Exp. Return Date"};
                            for(int i=0;i<num;i++){
                                table_cell = new PdfPCell(new Phrase(column[i]));
                                report_table.addCell(table_cell);
                            }
                            while (rs.next()) {   
                                for(int i=1;i<=num;i++){
                                    String s = rs.getString(i);
                                    table_cell = new PdfPCell(new Phrase(s));
                                    report_table.addCell(table_cell);
                                }
                            }
                            doc.add(report_table);                       
                            doc.close();
                            JOptionPane.showMessageDialog(null, "Successfully Generated!");
                        }
                        if(getsearchtype.equals("Past Borrow Records")){
                            ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,return_date,fine from past_records natural join book_details natural join student_details");
                            Document doc = new Document();
                            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                            doc.open();
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int num = rsmd.getColumnCount();
                            PdfPTable report_table = new PdfPTable(num);
                            PdfPCell table_cell;
                            String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Return Date","Fine(Rs)"};
                            for(int i=0;i<num;i++){
                                table_cell = new PdfPCell(new Phrase(column[i]));
                                report_table.addCell(table_cell);
                            }
                            while (rs.next()) {                
                                for(int i=1;i<=num;i++){
                                    String s = rs.getString(i);
                                    table_cell = new PdfPCell(new Phrase(s));
                                    report_table.addCell(table_cell);
                                }
                            }
                            doc.add(report_table);                       
                            doc.close();
                            JOptionPane.showMessageDialog(null, "Successfully Generated!");
                        }
                        if(getsearchtype.equals("Individual Borrower Record(Current)")){
                            String brid = JOptionPane.showInputDialog(null, "Enter Borrower ID");
                            if(brid==null || brid.equals("")){
                                JOptionPane.showMessageDialog(null, "Enter Valid ID! Go Again!");
                            }
                            else{
                                ResultSet rs1 = DBManager.getResultSet("SELECT * from current_records where st_id = '"+brid+"'");
                                if(rs1.next()){
                                    ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,exp_return_date from current_records natural join book_details natural join student_details where st_id = '"+brid+"'");
                                    Document doc = new Document();
                                    PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                                    doc.open();
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int num = rsmd.getColumnCount();
                                    PdfPTable report_table = new PdfPTable(num);
                                    PdfPCell table_cell;
                                    String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Exp. Return Date"};
                                    for(int i=0;i<num;i++){
                                        table_cell = new PdfPCell(new Phrase(column[i]));
                                        report_table.addCell(table_cell);
                                    }
                                    while (rs.next()) {                
                                        for(int i=1;i<=num;i++){
                                            String s = rs.getString(i);
                                            table_cell = new PdfPCell(new Phrase(s));
                                            report_table.addCell(table_cell);
                                        }
                                    }
                                    doc.add(report_table);                       
                                    doc.close();
                                    JOptionPane.showMessageDialog(null, "Successfully Generated!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No records found with this ID!");
                                }
                            }
                        }
                        if(getsearchtype.equals("Individual Borrower Record(Past)")){ 
                            String brid = JOptionPane.showInputDialog(null, "Enter Borrower ID");
                            if(brid==null || brid.equals("")){
                                JOptionPane.showMessageDialog(null, "Enter Valid ID! Go Again!");
                            }
                            else{
                                ResultSet rs1 = DBManager.getResultSet("SELECT * from past_records where st_id = '"+brid+"'");
                                if(rs1.next()){
                                    ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,return_date,fine from past_records natural join book_details natural join student_details where st_id = '"+brid+"'");
                                    Document doc = new Document();
                                    PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                                    doc.open();
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int num = rsmd.getColumnCount();
                                    PdfPTable report_table = new PdfPTable(num);
                                    PdfPCell table_cell;
                                    String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Return Date","Fine(Rs)"};
                                    for(int i=0;i<num;i++){
                                        table_cell = new PdfPCell(new Phrase(column[i]));
                                        report_table.addCell(table_cell);
                                    }
                                    while (rs.next()) {                
                                        for(int i=1;i<=num;i++){
                                            String s = rs.getString(i);
                                            table_cell = new PdfPCell(new Phrase(s));
                                            report_table.addCell(table_cell);
                                        }
                                    }
                                    doc.add(report_table);                       
                                    doc.close();
                                    JOptionPane.showMessageDialog(null, "Successfully Generated!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No records found with this ID!");
                                }
                            }
                        }
                        if(getsearchtype.equals("Particular Book Record(Current)")){
                            String bkid = JOptionPane.showInputDialog(null, "Enter Book ID");
                            if(bkid==null || bkid.equals("")){
                                JOptionPane.showMessageDialog(null, "Enter Valid ID! Go Again!");
                            }
                            else{
                                ResultSet rs1 = DBManager.getResultSet("SELECT * from current_records where Book_id = '"+bkid+"'");
                                if(rs1.next()){
                                    ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,exp_return_date from current_records natural join book_details natural join student_details where Book_id = '"+bkid+"'");
                                    Document doc = new Document();
                                    PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                                    doc.open();
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int num = rsmd.getColumnCount();
                                    PdfPTable report_table = new PdfPTable(num);
                                    PdfPCell table_cell;
                                    String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Exp. Return Date"};
                                    for(int i=0;i<num;i++){
                                        table_cell = new PdfPCell(new Phrase(column[i]));
                                        report_table.addCell(table_cell);
                                    }
                                    while (rs.next()) {                
                                        for(int i=1;i<=num;i++){
                                            String s = rs.getString(i);
                                            table_cell = new PdfPCell(new Phrase(s));
                                            report_table.addCell(table_cell);
                                        }
                                    }
                                    doc.add(report_table);                       
                                    doc.close();
                                    JOptionPane.showMessageDialog(null, "Successfully Generated!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No records found with this ID!");
                                }
                            }
                        }
                        if(getsearchtype.equals("Particular Book Record(Past)")){ 
                            String bkid = JOptionPane.showInputDialog(null, "Enter Book ID");
                            if(bkid==null || bkid.equals("")){
                                JOptionPane.showMessageDialog(null, "Enter Valid ID! Go Again!");
                            }
                            else{
                                ResultSet rs1 = DBManager.getResultSet("SELECT * from past_records where Book_id = '"+bkid+"'");
                                if(rs1.next()){
                                    ResultSet rs = DBManager.getResultSet("SELECT Book_id,Book_name,st_id,st_name,issue_date,return_date,fine from past_records natural join book_details natural join student_details where Book_id = '"+bkid+"'");
                                    Document doc = new Document();
                                    PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
                                    doc.open();
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int num = rsmd.getColumnCount();
                                    PdfPTable report_table = new PdfPTable(num);
                                    PdfPCell table_cell;
                                    String column[] = {"Book Id","Book Name","Student ID","Student Name","Issue Date","Return Date","Fine(Rs)"};
                                    for(int i=0;i<num;i++){
                                        table_cell = new PdfPCell(new Phrase(column[i]));
                                        report_table.addCell(table_cell);
                                    }
                                    while (rs.next()) {                
                                        for(int i=1;i<=num;i++){
                                            String s = rs.getString(i);
                                            table_cell = new PdfPCell(new Phrase(s));
                                            report_table.addCell(table_cell);
                                        }
                                    }
                                    doc.add(report_table);                       
                                    doc.close();
                                    JOptionPane.showMessageDialog(null, "Successfully Generated!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No records found with this ID!");
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
        add(background);
        setVisible(true);
    }
    
    public static void main(String args[]){
        GenerateReport obj = new GenerateReport();
    }
}
