package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStudent extends JPanel{
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
    private JButton button;
    private JLabel dob;
    private String getid;
    private String getname;
    private String getemail;
    private String getnumber;
    private String getdate;
    private String getdept;
    private String getyear;
            
    public AddStudent(){
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
        add(dob);
        add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==button){
                    getid = field1.getText();
                    getname = field2.getText();
                    getemail = field3.getText();
                    getnumber = field4.getText();
                    getdate = field5.getText();
                    getdept = field6.getText();
                    getyear = (String)field7.getSelectedItem();
                    if(isValidSTID(getid) && isValidContact(getnumber) && isValidEmail(getemail) && isValidDate(getdate)){
                        try{
                            ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getid+"'");
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null, "This Student ID already exists!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else if(getid.equals("") || getname.equals("") || getemail.equals("") || getnumber.equals("") || getdate.equals("") || getdept.equals("") || getyear.equals("")){
                                JOptionPane.showMessageDialog(null, "Please Enter Valid Inputs!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                String getbookcount = "0";
                                putstudentinfo(getid,getname,getemail,getnumber,getdate,getdept,getyear,getbookcount);
                                JOptionPane.showMessageDialog(null, "Added Successfully!");
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        if(!isValidSTID(getid))
                            JOptionPane.showMessageDialog(null, "Please enter valid Student ID. Format is any number between 0 to 99999999", "Alert",JOptionPane.WARNING_MESSAGE);
                        else if(!isValidContact(getnumber))
                            JOptionPane.showMessageDialog(null, "Please enter valid 10 digit Mobile Number", "Alert",JOptionPane.WARNING_MESSAGE);
                        else if(!isValidEmail(getemail))
                            JOptionPane.showMessageDialog(null, "Please enter valid Email", "Alert",JOptionPane.WARNING_MESSAGE);
                        else if(!isValidDate(getdate))
                            JOptionPane.showMessageDialog(null, "Please enter valid DOB", "Alert",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
    
    public static boolean isValidSTID(String s){
        try{
            int num = Integer.parseInt(s);
            if(num>0 && num<999999999){
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
    
    public static boolean isValidContact(String s){
        if(s.length()!=10)
            return false;
        else{
            if(s.charAt(0)=='7' || s.charAt(0)=='8' || s.charAt(0)=='9'){
                try{
                    long z = Long.parseLong(s);
                    return true;
                }
                catch(Exception e){
                    return false;
                }
            }
            else{
                return false;
            }
        }
    }
    
    public static boolean isValidEmail(String s){
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
    
    public static boolean isValidDate(String s){
        final String date_pattern = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
        Pattern pat = Pattern.compile(date_pattern);
        Matcher mat = pat.matcher(s);
        return mat.matches();
    }
    
    public void putstudentinfo(String gid, String gname, String gemail, String gnumber, String gdate, String gdept,String gyear,String gcount){
        try{
            DBManager.getUpdate("INSERT INTO student_details VALUES('"+gid+"','"+gname+"','"+gemail+"','"+gnumber+"','"+gdate+"','"+gdept+"','"+gyear+"','"+gcount+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        label1 = new JLabel("Student ID");
        label1.setBounds(65, 50, 100, 14);
        
        label2 = new JLabel("Name");
        label2.setBounds(65, 115, 100, 14);
        
        label3 = new JLabel("Email");
        label3.setBounds(65, 180, 100, 14);
        
        label4 = new JLabel("Contact Number");
        label4.setBounds(65, 245, 100, 14);
        
        label5 = new JLabel("DOB");
        label5.setBounds(600, 50, 100, 14);
        
        label6 = new JLabel("Department");
        label6.setBounds(600, 115, 100, 14);
        
        label7 = new JLabel("Year");
        label7.setBounds(600, 180, 100, 14);
        
        field1 = new JTextField();
        field1.setBounds(215, 48, 180, 20);
        
        field2 = new JTextField();
        field2.setBounds(215, 113, 180, 20);
        
        field3 = new JTextField();
        field3.setBounds(215, 178, 180, 20);
        
        field4 = new JTextField();
        field4.setBounds(215, 243, 180, 20);
        
        field5 = new JTextField();
        field5.setBounds(750, 48, 180, 20);
        
        field6 = new JTextField();
        field6.setBounds(750, 113, 180, 20);
        
        String year[] = {"1","2","3","4"};
        field7 = new JComboBox(year);
        field7.setBounds(750, 178, 180, 20);
        
        dob = new JLabel("(YYYY-MM-DD)");
        dob.setBounds(935, 48, 100, 14);
        
        button = new JButton("Add");
        button.setBounds(470, 330, 89, 23);
    }
}
