package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class ViewStudent extends JPanel{
    private JComboBox search;
    private JTextField tsearch;
    private JButton bsearch;
    private JTable table;
    private JScrollPane pane;
    private DefaultTableModel Model;
    private String getsearchtype;
    private String getfield;
    private String getid;
    private String getname;
    private String getemail;
    private String getnumber;
    private String getdate;
    private String getdept;
    private String getyear;
    
    public ViewStudent(){
        init();
        add(search);
        add(tsearch);
        add(bsearch);
        add(pane);
        bsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Model.setRowCount(0);
                if(event.getSource()==bsearch){
                    task();
                }
            }
        });
        tsearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Model.setRowCount(0);
                task();
            }
        });
    }
    
    public void task(){
        getsearchtype = (String)search.getSelectedItem();
        getfield = tsearch.getText();
        if(getfield.equals("") && !getsearchtype.equals("All")){
            JOptionPane.showMessageDialog(null, "Please enter valid inputs!","Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            if(getsearchtype.equals("Student ID")){
                ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_id = '"+getfield+"'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("st_id");
                        getname = rs.getString("st_name");
                        getemail = rs.getString("st_email");
                        getnumber = rs.getString("st_contactnumber");
                        getdate = rs.getString("st_DOB");
                        getdept = rs.getString("st_dept");
                        getyear = rs.getString("st_year");
                        Object data[] = {getid,getname,getemail,getnumber,getdate,getdept,getyear};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No student having this ID!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(getsearchtype.equals("Name")){
                ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_name LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("st_id");
                        getname = rs.getString("st_name");
                        getemail = rs.getString("st_email");
                        getnumber = rs.getString("st_contactnumber");
                        getdate = rs.getString("st_DOB");
                        getdept = rs.getString("st_dept");
                        getyear = rs.getString("st_year");
                        Object data[] = {getid,getname,getemail,getnumber,getdate,getdept,getyear};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Student having this Name!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(getsearchtype.equals("Email")){
                ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_email LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("st_id");
                        getname = rs.getString("st_name");
                        getemail = rs.getString("st_email");
                        getnumber = rs.getString("st_contactnumber");
                        getdate = rs.getString("st_DOB");
                        getdept = rs.getString("st_dept");
                        getyear = rs.getString("st_year");
                        Object data[] = {getid,getname,getemail,getnumber,getdate,getdept,getyear};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Student having this email!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(getsearchtype.equals("Department")){
                ResultSet rs = DBManager.getResultSet("SELECT * from student_details where st_dept LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("st_id");
                        getname = rs.getString("st_name");
                        getemail = rs.getString("st_email");
                        getnumber = rs.getString("st_contactnumber");
                        getdate = rs.getString("st_DOB");
                        getdept = rs.getString("st_dept");
                        getyear = rs.getString("st_year");
                        Object data[] = {getid,getname,getemail,getnumber,getdate,getdept,getyear};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Student having this Department!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(getsearchtype.equals("All")){
                ResultSet rs = DBManager.getResultSet("SELECT * from student_details");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("st_id");
                        getname = rs.getString("st_name");
                        getemail = rs.getString("st_email");
                        getnumber = rs.getString("st_contactnumber");
                        getdate = rs.getString("st_DOB");
                        getdept = rs.getString("st_dept");
                        getyear = rs.getString("st_year");
                        Object data[] = {getid,getname,getemail,getnumber,getdate,getdept,getyear};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Student registered!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        String names[] = {"Student ID","Name","Email","Department","All"};
        search = new JComboBox(names);
        search.setBounds(55, 134, 100, 25);
        
        tsearch = new JTextField();
        tsearch.setBounds(180, 134, 145, 25);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(120, 195, 89, 23);
        
        String column[] = {"Student ID","Name","Email","Contact no.","DOB","Department","Year"};
        Model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Model.setColumnIdentifiers(column);
        table = new JTable();
        table.setModel(Model);
        pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(350, 25, 670, 380);
    }
}  
