package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class PastBorrowerList extends JPanel{
    private JComboBox search;
    private JTextField tsearch;
    private JButton bsearch;
    private JTable table;
    private JScrollPane pane;
    private DefaultTableModel Model;
    private String getbrid;
    private String getbrname;
    private String getbookid;
    private String getbookname;
    private String getissuedate;
    private String getrdate;
    private String getfine;
    private String getsearchtype;
    private String getfield;
    
    public  PastBorrowerList(){
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
        if(getfield.equals("") && !getsearchtype.equals("All Data")){
            JOptionPane.showMessageDialog(null, "Please enter valid inputs!");
            return;
        }
        try{
            if(getsearchtype.equals("Borrower ID")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details natural join past_records natural join student_details where st_id = '"+getfield+"'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getbrid = rs.getString("st_id");
                        getbrname = rs.getString("st_name"); 
                        getbookid = rs.getString("Book_id");
                        getbookname = rs.getString("Book_name");
                        getissuedate = rs.getString("issue_date");
                        getrdate = rs.getString("return_date");
                        getfine = rs.getString("fine");
                        Object data[] = {getbrid,getbrname,getbookid,getbookname,getissuedate,getrdate,getfine};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No past borrower having this ID!");
                }
            }
            if(getsearchtype.equals("Book ID")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details natural join past_records natural join student_details where Book_id = '"+getfield+"'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getbrid = rs.getString("st_id");
                        getbrname = rs.getString("st_name"); 
                        getbookid = rs.getString("Book_id");
                        getbookname = rs.getString("Book_name");
                        getissuedate = rs.getString("issue_date");
                        getrdate = rs.getString("return_date");
                        getfine = rs.getString("fine");
                        Object data[] = {getbrid,getbrname,getbookid,getbookname,getissuedate,getrdate,getfine};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No past book issued with this ID!");
                }
            }
            if(getsearchtype.equals("Borrower Name")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details natural join past_records natural join student_details where st_name LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getbrid = rs.getString("st_id");
                        getbrname = rs.getString("st_name"); 
                        getbookid = rs.getString("Book_id");
                        getbookname = rs.getString("Book_name");
                        getissuedate = rs.getString("issue_date");
                        getrdate = rs.getString("return_date");
                        getfine = rs.getString("fine");
                        Object data[] = {getbrid,getbrname,getbookid,getbookname,getissuedate,getrdate,getfine};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No past borrower having this name!");
                }
            }
            if(getsearchtype.equals("Book Name")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details natural join past_records natural join student_details where Book_name LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getbrid = rs.getString("st_id");
                        getbrname = rs.getString("st_name"); 
                        getbookid = rs.getString("Book_id");
                        getbookname = rs.getString("Book_name");
                        getissuedate = rs.getString("issue_date");
                        getrdate = rs.getString("return_date");
                        getfine = rs.getString("fine");
                        Object data[] = {getbrid,getbrname,getbookid,getbookname,getissuedate,getrdate,getfine};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No past book issued with this name!");
                }
            }
            if(getsearchtype.equals("All Data")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details natural join past_records natural join student_details");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getbrid = rs.getString("st_id");
                        getbrname = rs.getString("st_name"); 
                        getbookid = rs.getString("Book_id");
                        getbookname = rs.getString("Book_name");
                        getissuedate = rs.getString("issue_date");
                        getrdate = rs.getString("return_date");
                        getfine = rs.getString("fine");
                        Object data[] = {getbrid,getbrname,getbookid,getbookname,getissuedate,getrdate,getfine};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No past list!");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        String names[] = {"Borrower ID","Book ID","Borrower Name","Book Name","All Data"};
        search = new JComboBox(names);
        search.setBounds(55, 134, 100, 25);
        
        tsearch = new JTextField();
        tsearch.setBounds(180, 134, 145, 25);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(120, 195, 89, 23);
        
        String column[] = {"Borrower ID","Borrower Name","Book ID","Book Name","Issue Date","Return Date","Fine(Rs.)"};
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
