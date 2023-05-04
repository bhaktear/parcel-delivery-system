package gui;
import db.DBConnection;
import javax.swing.*;
import java.awt.event.*;
import gui.*;

public class Home extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button1 = new JButton("Log in");
        JButton button2 = new JButton("Register");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginForm login = new LoginForm();
                login.setVisible(true);
                frame.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegForm reg = new RegForm();
                reg.setVisible(true);
                frame.dispose();
            }
        });

        JLabel background = new JLabel(new ImageIcon("img/Home_baimf.jpg"));
        background.setLayout(null);
        background.add(button1);
        background.add(button2);
        button1.setBounds(450, 30, 100, 30);
        button2.setBounds(600, 30, 100, 30);
        background.setSize(background.getPreferredSize());
        frame.setContentPane(background);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
