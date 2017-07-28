package lms;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;

public class Login extends JFrame{
    private JLabel username;
    private JLabel pass;
    private JTextField name;
    private JPasswordField password;
    private JButton login;
    private JButton back;
    private JLabel forgot;
    private String inputuser;
    private String inputpass;
    static int status = 0;
    
    public Login(){
        //creating items
        init();
        //Frame
        setTitle("Log In - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("images/img6.jpg")));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        add(username);
        add(pass);
        add(name);
        add(password);
        add(login);
        add(back);
        add(forgot);
        //Event Handling
        EventHandler handler = new EventHandler();
        login.addActionListener(handler);
        back.addActionListener(handler);
        name.addActionListener(handler);
        password.addActionListener(handler); 
        
        forgot.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getSource()==forgot){
                    new ForgotPass();
                    dispose();
                }
            }
        });
        //visible
        add(background);
        setVisible(true);
    }
    
    public void init(){
        username = new JLabel("Email ID");
        username.setForeground(Color.LIGHT_GRAY);
        username.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        username.setBounds(399, 256, 86, 14);
        
        pass = new JLabel("Password");
        pass.setForeground(Color.LIGHT_GRAY);
        pass.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        pass.setBounds(399, 333, 86, 14);
        
        name = new JTextField();
        name.setToolTipText("Enter Your Email ID");
        name.setBounds(566, 253, 191, 27);
        
        password=new JPasswordField();
        password.setToolTipText("Enter Your Password");
        password.setBounds(566, 320, 191, 27);
        
        login = new JButton("Login");
        login.setToolTipText("Click here to Login");
        login.setBounds(566, 395, 89, 23);
        
        back = new JButton("Back");
        back.setToolTipText("Click here to go back");
        back.setBounds(668, 395, 89, 23);
        
        forgot = new JLabel("Forgot Password?");
        forgot.setToolTipText("Click here to change your password");
        forgot.setForeground(Color.PINK);
        forgot.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        forgot.setBounds(600, 450, 129, 14);
    }
    
    public class EventHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            inputuser = name.getText();
            inputpass = new String(password.getPassword());
            if(event.getSource()==login){
                getInfo(inputuser,inputpass);
            }
            else if(event.getSource()==back){
                switch(status){
                    case 1: new First();
                    dispose();
                    break;
                    case 2: new ForgotPass();
                    dispose();
                    break;
                    case 3: new AddAdmin();
                    dispose();
                    break;
                }
            }
            else
                getInfo(inputuser,inputpass);
        }
    }
    
    public void getInfo(String id, String pass){
        try{
            String query = "SELECT * from admin where Email_id = '"+id+"' and Password = '"+pass+"'";
            ResultSet rs = DBManager.getResultSet(query);
            if(rs.next()){
                new Home();
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Wrong Credentials", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Login obj = new Login();
    }
}
