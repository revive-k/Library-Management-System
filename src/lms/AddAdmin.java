package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;

public class AddAdmin extends JFrame{
    private JLabel id;
    private JLabel username;
    private JLabel name;
    private JLabel question;
    private JLabel answer;
    private JLabel password;
    private JLabel confirm;
    private JTextField tid;
    private JTextField tuser;
    private JTextField tname;
    private JComboBox tquest;
    private JTextField tans;
    private JPasswordField pass;
    private JPasswordField passconf;
    private JButton ok;
    private JButton back;
    private String inputemail;
    private String inputuser;
    private String inputname;
    private String inputquest;
    private String inputans;
    private String inputpass;
    private String inputconfpass;
    
    public AddAdmin(){
        //create items
        init();
        //Frame
        setTitle("Add User - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        add(username);
        add(name);
        add(id);
        add(question);
        add(answer);
        add(password);
        add(confirm);
        add(tuser);
        add(tname);
        add(tid);
        add(tquest);
        add(tans);
        add(pass);
        add(passconf);
        add(ok);
        add(back);
        //Event Handling
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                inputemail = tid.getText();
                inputuser = tuser.getText();
                inputname = tname.getText();
                inputans = tans.getText();
                inputquest =(String)tquest.getSelectedItem();
                inputpass = new String(pass.getPassword());
                inputconfpass = new String(passconf.getPassword());
                if(inputemail.equals("") || inputuser.equals("") || inputname.equals("") || inputans.equals("") || inputquest.equals("") || inputpass.equals("")){
                    JOptionPane.showMessageDialog(null, "Some fields are empty. Please fill them!", "Alert",JOptionPane.WARNING_MESSAGE);
                }
                else if(!inputpass.equals(inputconfpass)){
                    JOptionPane.showMessageDialog(null, "Password doesn't match!", "Alert",JOptionPane.WARNING_MESSAGE);
                }
                else if(!AddStudent.isValidEmail(inputemail)){
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Email!", "Alert",JOptionPane.WARNING_MESSAGE);
                }
                else if(event.getSource()==ok && inputpass.equals(inputconfpass)){
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from admin where Email_id = '"+inputemail+"'");
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "This email is already registered!", "Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Registered Successfully!");
                            Login.status = 3;
                            putInfo(inputuser,inputname,inputemail,inputquest,inputans,inputpass);
                            new Login();
                            dispose();
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please Enter valid Inputs", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==back){
                    new First();
                    dispose();
                }
            }
        });
        //visible
        add(background);
        setVisible(true);
    }
    
    public void init(){
        username = new JLabel("Username");
        username.setForeground(Color.LIGHT_GRAY);
        username.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        username.setBounds(369, 129, 106, 14);
        
        name = new JLabel("Name");
        name.setForeground(Color.LIGHT_GRAY);
        name.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        name.setBounds(369, 201, 66, 14);
        
        id = new JLabel("Email ID");
        id.setForeground(Color.LIGHT_GRAY);
        id.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        id.setBounds(369, 273, 66, 14);
        
        question = new JLabel("Your Security Question");
        question.setForeground(Color.LIGHT_GRAY);
        question.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        question.setBounds(369, 345, 180, 14);
        
        answer = new JLabel("Answer");
        answer.setForeground(Color.LIGHT_GRAY);
        answer.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        answer.setBounds(369, 417, 86, 14);
        
        password = new JLabel("Password");
        password.setForeground(Color.LIGHT_GRAY);
        password.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        password.setBounds(369, 489, 176, 14);
        
        confirm = new JLabel("Confirm Password");
        confirm.setForeground(Color.LIGHT_GRAY);
        confirm.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        confirm.setBounds(369, 561, 176, 14);
        
        tuser = new JTextField(20);
        tuser.setBounds(563, 127, 182, 27);
        
        tname = new JTextField(20);
        tname.setBounds(563, 199, 182, 27);
        
        tid = new JTextField(20);
        tid.setBounds(563, 271, 182, 27);
        
        String quest[] = {"Your school name?","Your favourite game?","Your favourite movie?","Your pet name?"};
        tquest = new JComboBox(quest);
        tquest.setBounds(563, 343, 182, 27);
        
        tans = new JTextField(20);
        tans.setBounds(563, 415, 182, 27);
        
        pass = new JPasswordField(20);
        pass.setBounds(563, 487, 182, 27);
        
        passconf = new JPasswordField(20);
        passconf.setBounds(563, 559, 182, 27);
        
        ok = new JButton("Add");
        ok.setBounds(563, 620, 90, 25);
        
        back = new JButton("Back");
        back.setBounds(655, 620, 90, 25);
    }
    
    public void putInfo(String username, String name, String email, String ques, String ans, String password){
        try{
            DBManager.getUpdate("INSERT INTO ADMIN VALUES('"+username+"','"+name+"','"+email+"','"+ques+"','"+ans+"','"+password+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        AddAdmin obj = new AddAdmin();
    }
}
