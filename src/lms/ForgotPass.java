package lms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.event.*;

public class ForgotPass extends JFrame{
    private JLabel email;
    private JLabel question;
    private JLabel answer;
    private JLabel password;
    private JTextField temail;
    private JComboBox tquest;
    private JTextField tans;
    private JPasswordField pass;
    private JButton ok;
    private JButton back;
    private String getemail;
    private String getquest;
    private String getanswer;
    private String getpass;
    
    public ForgotPass(){
        //creating items
        init();
        //Frame
        setTitle("Forgot Password - Library Management System");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        JLabel background=new JLabel(new ImageIcon("C:\\LMS\\img6.jpg"));
        background.setBounds(0,0,this.getWidth(), this.getHeight());
        //add items
        add(email);
        add(question);
        add(answer);
        add(password);
        add(temail);
        add(tquest);
        add(tans);
        add(pass);
        add(ok);
        add(back);
        //Event Handling
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==ok){
                    getemail = temail.getText();
                    getquest = (String)tquest.getSelectedItem();
                    getanswer = tans.getText();
                    getpass = new String(pass.getPassword());
                    try{
                        ResultSet rs = DBManager.getResultSet("SELECT * from admin where Email_id = '"+getemail+"'");
                        if(rs.next()){
                            rs = DBManager.getResultSet("SELECT * from admin where Security_ques = '"+getquest+"' and Email_id = '"+getemail+"'");
                            if(rs.next()){
                                rs = DBManager.getResultSet("SELECT * from admin where Answer = '"+getanswer+"'and Email_id = '"+getemail+"'");
                                if(rs.next()){
                                    if(!getpass.equals("")){
                                        DBManager.getUpdate("UPDATE admin SET Password = '"+getpass+"' where Email_id = '"+getemail+"'");
                                        JOptionPane.showMessageDialog(null, "Your Password is successfully updated!");
                                        Login.status = 2;
                                        new Login();
                                        dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Password cannot be blank!","Alert",JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Your answer doesn't match!","Alert",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Your security question doesn't match!","Alert",JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your email doesn't match!","Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(event.getSource()==back){
                    new Login();
                    dispose();
                }
            }
        });
        //visible
        add(background);
        setVisible(true);
    }
    
    public void init(){
        email = new JLabel("Email ID");
        email.setForeground(Color.LIGHT_GRAY);
        email.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        email.setBounds(369, 173, 86, 14);
        
        question = new JLabel("Your Security Question");
        question.setForeground(Color.LIGHT_GRAY);
        question.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        question.setBounds(369, 245, 180, 14);
        
        answer = new JLabel("Answer");
        answer.setForeground(Color.LIGHT_GRAY);
        answer.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        answer.setBounds(369, 317, 86, 14);
        
        password = new JLabel("New Password");
        password.setForeground(Color.LIGHT_GRAY);
        password.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        password.setBounds(369, 389, 156, 14);
        
        temail = new JTextField(20);
        temail.setBounds(563, 167, 182, 27);
        
        String quest[] = {"Your school name?","Your favourite game?","Your favourite movie?","Your pet name?"};
        tquest = new JComboBox(quest);
        tquest.setBounds(563, 236, 182, 27);
        
        tans = new JTextField(20);
        tans.setBounds(563, 305, 182, 27);
        
        pass = new JPasswordField(20);
        pass.setBounds(563, 374, 182, 27);
        
        ok = new JButton("Enter");
        ok.setBounds(563, 450, 90, 20);
        
        back = new JButton("Back");
        back.setBounds(655, 450, 90, 20);
    }
    
    public static void main(String[] args){
        ForgotPass obj = new ForgotPass();
    }
}
