package Frames;

import Entity.User;
import Controller.LoginCode;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame implements MouseListener, ActionListener {

    private JLabel unamelab, passlab, errlab, banerlab;
    private JPanel panel;
    private JTextField unamefld;
    private JPasswordField passfld;
    private Font passfont, labfont, fldfont, errfont, btnfont;
    private JButton submitbtn, backbtn;
    private final Container container;

    public LogIn() {
        this.setVisible(true);
        this.setTitle("Login Your Account");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container = this.getContentPane();
        container.setBackground(new java.awt.Color(0, 51, 51));
        container.setLayout(null);
        variableInitializer();
        Body();
    }

    private void variableInitializer() {
        passfont = new Font("Arial", Font.BOLD, 30);
        fldfont = new Font("Arial", Font.BOLD, 17);
        errfont = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
        btnfont = new Font("Arial", Font.BOLD, 25);
        labfont = new Font("Arial", Font.BOLD, 15);
        panel = new JPanel();
        banerlab = new JLabel();
        unamelab = new JLabel();
        passlab = new JLabel();
        unamefld = new JTextField();
        passfld = new JPasswordField();
        errlab = new JLabel();
        submitbtn = new JButton();
        backbtn = new JButton();
    }

    private void Body() {

        panel.setBackground(Color.BLACK);
        panel.setBounds(200, 100, 450, 435);
        panel.setLayout(null);
        container.add(panel);

        banerlab.setFont(passfont);
        banerlab.setForeground(Color.BLACK);
        banerlab.setOpaque(true);
        banerlab.setBackground(Color.ORANGE);
        panel.add(banerlab);
        banerlab.setText("   LOG IN YOUR ACCOUNT");
        banerlab.setBounds(20, 40, 410, 50);

        unamelab.setText("  Enter Your Username");
        unamelab.setBounds(20, 135, 165, 35);
        unamelab.setFont(labfont);
        unamelab.setForeground(Color.WHITE);
        unamelab.setOpaque(true);
        unamelab.setBackground(Color.BLACK);
        panel.add(unamelab);

        unamefld.setBounds(185, 135, 245, 35);
        unamefld.setFont(fldfont);
        panel.add(unamefld);

        passlab.setText("  Enter Your Password");
        passlab.setBounds(20, 210, 165, 35);
        passlab.setFont(labfont);
        passlab.setForeground(Color.WHITE);
        passlab.setOpaque(true);
        passlab.setBackground(Color.BLACK);
        panel.add(passlab);

        passfld.setBounds(185, 210, 245, 35);
        passfld.setFont(passfont);
        panel.add(passfld);

        errlab.setText(" USERNAME OR PASSWORD WAS INCORRECT");
        errlab.setForeground(Color.RED);
        errlab.setBackground(Color.BLACK);
        errlab.setOpaque(true);
        errlab.setFont(errfont);
        errlab.setBounds(50, 280, 348, 30);
        errlab.setVisible(false);
        panel.add(errlab);

        submitbtn.setText("SUBMIT");
        submitbtn.setBounds(250, 335, 150, 45);
        submitbtn.setBackground(new Color(255, 0, 0));
        submitbtn.setForeground(Color.BLACK);
        submitbtn.setFont(btnfont);
        panel.add(submitbtn);
        submitbtn.addMouseListener(this);
        submitbtn.addActionListener(this);

        backbtn.setText("BACK");
        backbtn.setBounds(50, 335, 150, 45);
        backbtn.setBackground(new Color(0, 153, 255));
        backbtn.setForeground(Color.BLACK);
        backbtn.setFont(btnfont);
        panel.add(backbtn);
        backbtn.addMouseListener(this);
        backbtn.addActionListener(this);
        
        unamefld.setText("sajid");
        passfld.setText("1111111");

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == submitbtn) {
            submitbtn.setBackground(Color.GREEN);
        } else if (me.getSource() == backbtn) {
            backbtn.setBackground(Color.GREEN);
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == submitbtn) {
            submitbtn.setBackground(Color.YELLOW);
            submitbtn.setBounds(246, 331, 158, 53);
        } else if (me.getSource() == backbtn) {
            backbtn.setBackground(Color.YELLOW);
            backbtn.setBounds(46, 331, 158, 53);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submitbtn) {
            submitbtn.setBackground(new Color(255, 0, 0));
            submitbtn.setBounds(250, 335, 150, 45);
        } else if (me.getSource() == backbtn) {
            backbtn.setBackground(new Color(0, 153, 255));
            backbtn.setBounds(50, 335, 150, 45);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == backbtn) {
            Main back = new Main();
            dispose();
        } else if (ae.getSource() == submitbtn) {
            LoginCode code = new LoginCode();
            boolean flag = code.checkAndVerify(unamefld, passfld, errlab);

            if (flag) {
                User user = code.getUser();
                MainMenu begin = new MainMenu(user);
                dispose();
            }
        }
    }

}
