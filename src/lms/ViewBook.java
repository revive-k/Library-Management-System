package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class ViewBook extends JPanel{
    private JComboBox search;
    private JTextField tsearch;
    private JButton bsearch;
    private JTable table;
    private JScrollPane pane;
    private String getsearchtype;
    private String getfield;
    private String getid;
    private String getname;
    private String getauthor;
    private String getcategory;
    private String getprice;
    private String getedition;
    private String getavailable;
    private DefaultTableModel Model;
    
    public ViewBook(){
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
            if(getsearchtype.equals("Book ID")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_id = '"+getfield+"'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("Book_id");
                        getname = rs.getString("Book_name");
                        getauthor = rs.getString("Author_name");
                        getcategory = rs.getString("Category");
                        getprice = rs.getString("Price");
                        getedition = rs.getString("Edition");
                        getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books with this Book ID!");
                }
            }
            if(getsearchtype.equals("Book Name")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_name LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("Book_id");
                        getname = rs.getString("Book_name");
                        getauthor = rs.getString("Author_name");
                        getcategory = rs.getString("Category");
                        getprice = rs.getString("Price");
                        getedition = rs.getString("Edition");
                        getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books with this Book Name!");
                }
            }
            if(getsearchtype.equals("Author Name")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Author_name LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("Book_id");
                        getname = rs.getString("Book_name");
                        getauthor = rs.getString("Author_name");
                        getcategory = rs.getString("Category");
                        getprice = rs.getString("Price");
                        getedition = rs.getString("Edition");
                        getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books with this Author Name!");
                }
            }
            if(getsearchtype.equals("Category")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Category LIKE '%"+getfield+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("Book_id");
                        getname = rs.getString("Book_name");
                        getauthor = rs.getString("Author_name");
                        getcategory = rs.getString("Category");
                        getprice = rs.getString("Price");
                        getedition = rs.getString("Edition");
                        getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books in this category!");
                }
            }
            if(getsearchtype.equals("All")){
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        getid = rs.getString("Book_id");
                        getname = rs.getString("Book_name");
                        getauthor = rs.getString("Author_name");
                        getcategory = rs.getString("Category");
                        getprice = rs.getString("Price");
                        getedition = rs.getString("Edition");
                        getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books in library!");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init(){
        setLayout(null);
        
        String names[] = {"Book ID","Book Name","Author Name","Category","All"};
        search = new JComboBox(names);
        search.setBounds(55, 134, 100, 25);
        
        tsearch = new JTextField();
        tsearch.setBounds(180, 134, 145, 25);
        
        bsearch = new JButton("Search");
        bsearch.setBounds(120, 195, 89, 23);
        
        String column[] = {"Book ID","Book Name","Author Name","Category","Price","Edition","Availability"};
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
