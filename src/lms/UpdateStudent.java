package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import static lms.AddStudent.isValidContact;
import static lms.AddStudent.isValidDate;
import static lms.AddStudent.isValidEmail;
import static lms.AddStudent.isValidSTID;

public class UpdateStudent extends JPanel{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    private JComboBox field7;
    private JButton update;
    private JButton delete;
    private JLabel lsearch;
    private JTextField tsearch;
    private JButton bsearch;
    private JLabel dob;
    private String getid;
    private String getname;
    private String getemail;
    private String getnumber;
    private String getdate;
    private String getdept;
    private String getyear;
            
    public UpdateStudent(){
        init();
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
        add(update);
        add(delete);
        add(dob);
        add(lsearch);
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
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==update){
                    getid = field1.getText();
                    getname = field2.getText();
                    getemail = field3.getText();
                    getnumber = field4.getText();
                    getdate = field5.getText();
                    getdept = field6.getText();
                    getyear = (String)field7.getSelectedItem();
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
                        if(getid.equals("") || getname.equals("") || getemail.equals("") || getnumber.equals("") || getdate.equals("") || getdept.equals("") || getyear.equals("")){
                            if(getid.equals(""))
                                JOptionPane.showMessageDialog(null, "Please Enter Student ID!", "Alert",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else if((!isValidSTID(getid)) || (!isValidContact(getnumber)) || (!isValidEmail(getemail)) || (!isValidDate(getdate))){
                            if(!isValidSTID(getid))
                                JOptionPane.showMessageDialog(null, "Please enter valid Student ID. Format is any number between 0 to 99999999", "Alert",JOptionPane.WARNING_MESSAGE);
                            else if(!isValidContact(getnumber))
                                JOptionPane.showMessageDialog(null, "Please enter valid 10 digit Mobile Number", "Alert",JOptionPane.WARNING_MESSAGE);
                            else if(!isValidEmail(getemail))
                                JOptionPane.showMessageDialog(null, "Please enter valid Email", "Alert",JOptionPane.WARNING_MESSAGE);
                            else if(!isValidDate(getdate))
                                JOptionPane.showMessageDialog(null, "Please enter valid DOB", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else if(rs.next()){
                            DBManager.getUpdate("UPDATE student_details SET st_name = '"+getname+"',st_email = '"+getemail+"',st_contactnumber = '"+getnumber+"',st_DOB = '"+getdate+"',st_dept = '"+getdept+"',st_year = '"+getyear+"' where st_id = '"+getid+"'");
                            JOptionPane.showMessageDialog(null, "Successfully Updated!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Student ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==delete){
                    getid = field1.getText();
                    getname = field2.getText();
                    getemail = field3.getText();
                    getnumber = field4.getText();
                    getdate = field5.getText();
                    getdept = field6.getText();
                    getyear = (String)field7.getSelectedItem();
                    try{
                        if(getid.equals("") || getname.equals("") || getemail.equals("") || getnumber.equals("") || getdate.equals("") || getdept.equals("") || getyear.equals("")){
                            if(getid.equals(""))
                                JOptionPane.showMessageDialog(null, "Please Enter Student ID!", "Alert",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            ResultSet rs = DBManager.getResultSet("SELECT * from current_records where st_id = '"+getid+"'");
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null, "This student is currently a borrower. You can't delete!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
                                if(rs.next()){
                                    DBManager.getUpdate("DELETE from student_details where st_id = '"+getid+"'");
                                    JOptionPane.showMessageDialog(null, "Successfully Deleted!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "This Student ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
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
            ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
            if(rs.next()){
                field1.setText(rs.getString("st_id"));
                field1.setEditable(false);
                field2.setText(rs.getString("st_name"));
                field3.setText(rs.getString("st_email"));
                field4.setText(rs.getString("st_contactnumber"));
                field5.setText(rs.getString("st_DOB"));
                field6.setText(rs.getString("st_dept"));
                field7.setSelectedItem(rs.getString("st_year"));
            }
            else if(getid.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter valid inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                refresh();
            }
            else{
                JOptionPane.showMessageDialog(null, "This Student ID does not exist!", "Alert",JOptionPane.WARNING_MESSAGE);
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
        field4.setText("");
        field5.setText("");
        field6.setText("");
        field7.setSelectedItem("1"); 
    }
    
    public void init(){
        setLayout(null);
        
        lsearch = new JLabel("Student ID");
        lsearch.setBounds(320, 30, 100, 14);
        
        tsearch = new JTextField();
        tsearch.setBounds(420, 30, 160, 20);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(600, 30, 80, 20);
        
        label1 = new JLabel("Student ID");
        label1.setBounds(65, 100, 100, 14);
        
        label2 = new JLabel("Name");
        label2.setBounds(65, 165, 100, 14);
        
        label3 = new JLabel("Email");
        label3.setBounds(65, 230, 100, 14);
        
        label4 = new JLabel("Contact Number");
        label4.setBounds(65, 295, 100, 14);
        
        label5 = new JLabel("DOB");
        label5.setBounds(600, 100, 100, 14);
        
        label6 = new JLabel("Department");
        label6.setBounds(600, 165, 100, 14);
        
        label7 = new JLabel("Year");
        label7.setBounds(600, 230, 100, 14);
        
        field1 = new JTextField();
        field1.setBounds(215, 98, 180, 20);
        field1.setEditable(false);
        
        field2 = new JTextField();
        field2.setBounds(215, 163, 180, 20);
        
        field3 = new JTextField();
        field3.setBounds(215, 228, 180, 20);
        
        field4 = new JTextField();
        field4.setBounds(215, 293, 180, 20);
        
        field5 = new JTextField();
        field5.setBounds(750, 98, 180, 20);
        
        field6 = new JTextField();
        field6.setBounds(750, 163, 180, 20);
        
        String year[] = {"1","2","3","4"};
        field7 = new JComboBox(year);
        field7.setBounds(750, 228, 180, 20);
        
        dob = new JLabel("(YYYY-MM-DD)");
        dob.setBounds(935, 98, 180, 20);
        
        update = new JButton("Update");
        update.setBounds(375, 358, 89, 23);
        
        delete = new JButton("Delete");
        delete.setBounds(575, 358, 89, 23);
    }
}
