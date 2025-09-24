import javax.swing.*;
import java.awt.*;


public class User {
    public static void main(String[] args) {
        
        JFrame f = new JFrame("Login");
        Container cp = f.getContentPane();
        cp.setLayout(null);

        JLabel l = new JLabel("USER : ");
        l.setBounds(50,10, 70,20);
        cp.add(l);
        JTextField t1 = new JTextField(20);
        t1.setBounds(100, 10, 150, 20);
        cp.add(t1);
        JLabel o = new JLabel("Password : ");
        o.setBounds(30, 40, 70, 20);
        cp.add(o);
        JTextField t2 = new JTextField(20);
        t2.setBounds(100, 40, 150, 20);
        cp.add(t2);
        JButton b1 = new JButton("Login");
        b1.setBounds(100, 70, 80, 30);
        cp.add(b1);
        f.setLocationRelativeTo(null);
        f.setSize(300, 150);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;