package gui;

import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame {
    public Home() {
        JButton button1 = new JButton("Log in");
        JButton button2 = new JButton("Register");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginForm login = new LoginForm();
                login.setVisible(true);
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegForm reg = new RegForm();
                reg.setVisible(true);
                dispose();
            }
        });

        JLabel background = new JLabel(new ImageIcon("img/Home_baimf.jpg"));
        background.setLayout(null);
        background.add(button1);
        background.add(button2);
        button1.setBounds(450, 30, 100, 30);
        button2.setBounds(600, 30, 100, 30);
        background.setSize(background.getPreferredSize());
        setContentPane(background);
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
