package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class SearchBook extends JPanel{
    private DefaultTableModel Model;
    private JTable table;
    private JScrollPane pane;
    private JLabel title;
    private JLabel author;
    private JLabel or;
    private JTextField field1;
    private JTextField field2;
    private JButton button;
    private String gettitle;
    private String getauthor;
    
    public SearchBook(){
        init();
        add(title);
        add(author);
        add(or);
        add(field1);
        add(field2);
        add(button);
        add(pane);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Model.setRowCount(0);
                if(event.getSource()==button){
                    task();
                }
            }
        });
        field1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Model.setRowCount(0);
                task();
            }
        });
        field2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Model.setRowCount(0);
                task();
            }
        });
    }
    
    public void task(){
        gettitle = field1.getText();
        getauthor = field2.getText();
        if(gettitle.equals("") && getauthor.equals("")){ 
            JOptionPane.showMessageDialog(null, "Please enter valid inputs!","Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if(getauthor.equals("")){
            try{
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Book_name LIKE '%"+gettitle+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        String getid = rs.getString("Book_id");
                        String getname = rs.getString("Book_name");
                        String getauthor1 = rs.getString("Author_name");
                        String getcategory = rs.getString("Category");
                        String getprice = rs.getString("Price");
                        String getedition = rs.getString("Edition");
                        String getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor1,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books with this Book Name!");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(gettitle.equals("")){
            try{
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Author_name LIKE '%"+getauthor+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        String getid = rs.getString("Book_id");
                        String getname = rs.getString("Book_name");
                        String getauthor1 = rs.getString("Author_name");
                        String getcategory = rs.getString("Category");
                        String getprice = rs.getString("Price");
                        String getedition = rs.getString("Edition");
                        String getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor1,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books with this Author Name!");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                ResultSet rs = DBManager.getResultSet("SELECT * from book_details where Author_name LIKE '%"+getauthor+"%' and Book_name LIKE '%"+gettitle+"%'");
                if(rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        String getid = rs.getString("Book_id");
                        String getname = rs.getString("Book_name");
                        String getauthor1 = rs.getString("Author_name");
                        String getcategory = rs.getString("Category");
                        String getprice = rs.getString("Price");
                        String getedition = rs.getString("Edition");
                        String getavailable = rs.getString("Availability");
                        Object data[] = {getid,getname,getauthor1,getcategory,getprice,getedition,getavailable};
                        Model.addRow(data);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No books related to your search!");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void init(){
        setLayout(null);
        
        title = new JLabel("Title");
        title.setBounds(165, 25, 80, 14);
        
        author = new JLabel("Author Name");
        author.setBounds(650, 25, 80, 14);
        
        or = new JLabel("OR");
        or.setBounds(530, 25, 100, 14);
        
        field1 = new JTextField();
        field1.setBounds(240, 20, 200, 24);
        
        field2 = new JTextField();
        field2.setBounds(760, 20, 200, 24);
        
        button = new JButton("Search");
        button.setBounds(495, 65, 89, 20); 
        
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
        pane.setBounds(120, 115, 900, 280);
    }
} 
