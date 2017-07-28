package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager{
    
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    static int rows;
    static{
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://LocalHost:3306/lms","root","password");
           }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static Connection getConnection(){
        return con;
    }
      static Statement getStatement()throws SQLException{
        stmt=getConnection().createStatement();
        return stmt;
    }
    
    static ResultSet getResultSet(String sql) throws SQLException{
        rs=getStatement().executeQuery(sql);
        return rs;
    }
    
    static int getUpdate(String sql) throws SQLException{
        rows=getStatement().executeUpdate(sql);
        return rows;
    }
    
    static void closeConnection(Connection con) throws SQLException{
        con.close();
    }
}