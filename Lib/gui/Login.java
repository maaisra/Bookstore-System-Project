import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener,KeyListener{
    public static void main(String[] args) {
    new Login();
    }
    static JFrame up;
    Container cp ;
    JLabel username , password, us ;
    JTextField t1 ,t2 ;
    JButton b1,b2 ;
    public Login(){
        Initial();
        setComponent();
        Finally();
    }
    public void Initial(){
        setTitle("Login");
        cp = this.getContentPane();
        cp.setLayout(null);
    }
    public void setComponent(){
        username = new JLabel("Username : ");
        password = new JLabel("Password : ");
        us = new JLabel("username must not be special.");
        t1 = new JTextField(20);
        t2 = new JPasswordField(20);
        b1 = new JButton("Confirm");
        b2 = new JButton("Undo");  
        t1.setText("");
        t2.setText("");

        t1.addKeyListener(this);
        t2.addKeyListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        username.setBounds(60, 150, 110, 25);
        username.setFont(new Font("Helvetica",Font.BOLD,18));
        us.setBounds(170, 160, 200, 25);
        us.setFont(new Font("Helvetica",Font.ITALIC,18));

        password.setBounds(60, 220, 110, 25);
        password.setFont(new Font("Helvetica",Font.BOLD,18));

        t1.setBounds(170, 150, 200, 25);
        t1.setFont(new Font("Roboto",Font.PLAIN,14));

        t2.setBounds(170, 220, 200, 25);
        t2.setFont(new Font("Roboto",Font.PLAIN,14));

        b1.setBounds(150, 280, 80, 30);
        b1.setBackground(new Color(255, 0, 0));

        b2.setBounds(270, 280, 80, 30);
        b2.setBackground(new Color(255, 0, 0));
        
        cp.add(username); cp.add(t1);
        cp.add(password); cp.add(t2);
        cp.add(b1) ; cp.add(b2);
    }
    public void Finally(){
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == b1){
            String user = t1.getText();
            String pass = t2.getText();
            Boolean speical = true;
            Boolean userNpass = true;
            Boolean passchk = true;
            for(Character ch : user.toCharArray()){
                if(!Character.isAlphabetic(ch)){
                    speical = false;
                }
            }

            if (user.isEmpty() || pass.isEmpty()) {
            userNpass = false;
            showMessage("Get Username and Password Please.");
            }

            if(pass.length() < 8) {
                passchk = false;
                showMessage("Password not correct.");
            }

            if(speical==false){
            showMessage("You can't use speical symbol in username.");
            }
            else if(speical == true && userNpass == true && passchk == true)
            showMessage("Login successful!");
            
            }else if(e.getSource() == b2){
            t1.setText(""); 
            t2.setText("");
            }
    }
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}