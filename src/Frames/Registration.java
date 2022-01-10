package Frames;

import Entity.User;
import Controller.MailApi;
import Controller.RegistrationCode;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registration extends JFrame implements MouseListener, ActionListener {

    private JLabel namelab, unamelab, emaillab, passlab, conpasslab, banerlab, codelab;
    private JLabel nameerr, unameerr, emailerr, passerr, conpasserr, msgsent;
    private JTextField namefld, unamefld, emailfld, codefld;
    private JPasswordField passfld, conpassfld;
    private final Container container;
    private JPanel panel;
    private Font passfont, labfont, fldfont, errfont, btnfont;
    private JButton submitbtn, backbtn, confirmbtn;
    private int code;
    private User user;

    public Registration() {
        this.setVisible(true);
        this.setTitle("Create Account");
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
        namelab = new JLabel();
        unamelab = new JLabel();
        emaillab = new JLabel();
        passlab = new JLabel();
        conpasslab = new JLabel();
        codelab = new JLabel();
        namefld = new JTextField();
        unamefld = new JTextField();
        emailfld = new JTextField();
        codefld = new JTextField();
        passfld = new JPasswordField();
        conpassfld = new JPasswordField();
        nameerr = new JLabel();
        unameerr = new JLabel();
        emailerr = new JLabel();
        passerr = new JLabel();
        conpasserr = new JLabel();
        msgsent = new JLabel();
        submitbtn = new JButton();
        backbtn = new JButton();
        confirmbtn = new JButton();
    }

    private void Body() {

        panel.setBackground(Color.BLACK);
        panel.setBounds(200, 25, 450, 560);
        panel.setLayout(null);
        container.add(panel);

        banerlab.setText("  CREATE YOUR ACCOUNT");
        banerlab.setBounds(20, 25, 410, 50);
        banerlab.setFont(passfont);
        banerlab.setForeground(Color.BLACK);
        banerlab.setOpaque(true);
        banerlab.setBackground(Color.ORANGE);
        panel.add(banerlab);

        namelab.setText("  Enter Your Name");
        namelab.setBounds(20, 100, 165, 35);
        namelab.setFont(labfont);
        namelab.setForeground(Color.WHITE);
        namelab.setOpaque(true);
        namelab.setBackground(Color.BLACK);
        panel.add(namelab);

        namefld.setBounds(185, 100, 245, 35);
        namefld.setFont(fldfont);
        panel.add(namefld);

        nameerr.setBounds(187, 138, 243, 18);
        nameerr.setForeground(Color.RED);
        nameerr.setFont(errfont);
        panel.add(nameerr);

        unamelab.setText("  Enter Your Username");
        unamelab.setBounds(20, 160, 165, 35);
        unamelab.setFont(labfont);
        unamelab.setForeground(Color.WHITE);
        unamelab.setOpaque(true);
        unamelab.setBackground(Color.BLACK);
        panel.add(unamelab);

        unamefld.setBounds(185, 160, 245, 35);
        unamefld.setFont(fldfont);
        panel.add(unamefld);

        unameerr.setBounds(187, 198, 243, 18);
        unameerr.setForeground(Color.RED);
        unameerr.setFont(errfont);
        panel.add(unameerr);

        emaillab.setText("  Enter Your Email");
        emaillab.setBounds(20, 220, 165, 35);
        emaillab.setFont(labfont);
        emaillab.setForeground(Color.WHITE);
        emaillab.setOpaque(true);
        emaillab.setBackground(Color.BLACK);
        panel.add(emaillab);

        emailfld.setBounds(185, 220, 245, 35);
        emailfld.setFont(fldfont);
        panel.add(emailfld);

        emailerr.setBounds(187, 258, 243, 18);
        emailerr.setForeground(Color.RED);
        emailerr.setFont(errfont);
        panel.add(emailerr);

        passlab.setText("  Enter Your Password");
        passlab.setBounds(20, 280, 165, 35);
        passlab.setFont(labfont);
        passlab.setForeground(Color.WHITE);
        passlab.setOpaque(true);
        passlab.setBackground(Color.BLACK);
        panel.add(passlab);

        passfld.setBounds(185, 280, 245, 35);
        passfld.setFont(passfont);
        panel.add(passfld);

        passerr.setBounds(187, 318, 243, 18);
        passerr.setForeground(Color.RED);
        passerr.setFont(errfont);
        panel.add(passerr);

        conpasslab.setText("  Confirm Password");
        conpasslab.setBounds(20, 340, 165, 35);
        conpasslab.setFont(labfont);
        conpasslab.setForeground(Color.WHITE);
        conpasslab.setOpaque(true);
        conpasslab.setBackground(Color.BLACK);
        panel.add(conpasslab);

        conpassfld.setBounds(185, 340, 245, 35);
        conpassfld.setFont(passfont);
        panel.add(conpassfld);

        conpasserr.setBounds(187, 378, 243, 18);
        conpasserr.setForeground(Color.RED);
        conpasserr.setFont(errfont);
        panel.add(conpasserr);

        msgsent.setBounds(42, 390, 410, 35);
        msgsent.setFont(labfont);
        msgsent.setForeground(Color.RED);
        msgsent.setOpaque(true);
        msgsent.setBackground(Color.BLACK);
        msgsent.setVisible(false);
        panel.add(msgsent);

        codelab.setText("   Enter Your Code");
        codelab.setBounds(20, 432, 165, 35);
        codelab.setFont(labfont);
        codelab.setForeground(Color.WHITE);
        codelab.setOpaque(true);
        codelab.setBackground(Color.BLACK);
        codelab.setVisible(false);
        panel.add(codelab);

        codefld.setBounds(185, 432, 245, 35);
        codefld.setVisible(false);
        codefld.setFont(fldfont);
        panel.add(codefld);

        submitbtn.setText("SUBMIT");
        submitbtn.setBackground(new Color(255, 0, 0));
        submitbtn.setForeground(Color.BLACK);
        submitbtn.setFont(btnfont);
        panel.add(submitbtn);
        submitbtn.setBounds(250, 440, 150, 45);
        submitbtn.addMouseListener(this);
        submitbtn.addActionListener(this);

        backbtn.setText("BACK");
        backbtn.setBackground(new Color(0, 153, 255));
        backbtn.setForeground(Color.BLACK);
        backbtn.setFont(btnfont);
        panel.add(backbtn);
        backbtn.setBounds(50, 440, 150, 45);
        backbtn.addMouseListener(this);
        backbtn.addActionListener(this);

        confirmbtn.setText("CONFIRM");
        confirmbtn.setBackground(new Color(255, 0, 0));
        confirmbtn.setForeground(Color.BLACK);
        confirmbtn.setFont(btnfont);
        panel.add(confirmbtn);
        confirmbtn.setBounds(250, 495, 150, 45);
        confirmbtn.setVisible(false);
        confirmbtn.addMouseListener(this);
        confirmbtn.addActionListener(this);
    }

    private void displayHiddencom() {
        namefld.setEditable(false);
        unamefld.setEditable(false);
        emailfld.setEditable(false);
        passfld.setEditable(false);
        conpassfld.setEditable(false);
        submitbtn.setVisible(false);
        confirmbtn.setVisible(true);
        codefld.setVisible(true);
        codelab.setVisible(true);
        msgsent.setVisible(true);
        msgsent.setText("A CONFIRMATION CODE IS SENT TO YOUR EMAIL");
        backbtn.setBounds(50, 495, 150, 45);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
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
        } else if (me.getSource() == confirmbtn) {
            confirmbtn.setBackground(Color.YELLOW);
        } else if (me.getSource() == backbtn) {
            backbtn.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submitbtn) {
            submitbtn.setBackground(new Color(255, 0, 0));
        } else if (me.getSource() == confirmbtn) {
            confirmbtn.setBackground(new Color(255, 0, 0));
        } else if (me.getSource() == backbtn) {
            backbtn.setBackground(new Color(0, 153, 255));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submitbtn) {
            RegistrationCode check = new RegistrationCode(namefld, unamefld, emailfld, passfld, conpassfld);
            user = check.checkAll(namefld, unamefld, emailfld, passfld, conpassfld, nameerr, unameerr, emailerr, passerr, conpasserr);
            if (user != null) {
                try {
                    code = MailApi.sendMail(user.getEmail());
                    if (code == 0) {
                        msgsent.setText("CHECK YOUR EMAIL OR INTERNET CONNECTION");
                        msgsent.setVisible(true);
                    } else {
                        displayHiddencom();
                    }
                } catch (Exception e) {
                    msgsent.setText("CHECK YOUR EMAIL OR INTERNET CONNECTION");
                    msgsent.setVisible(true);
                }
            }
            
        } else if (ae.getSource() == backbtn) {
            
            Main exit = new Main();
            dispose();
            
        } else if (ae.getSource() == confirmbtn) {
            boolean flag = RegistrationCode.CheckCode(code, codefld, msgsent, user);
            if (flag) {
                JOptionPane.showMessageDialog(null, "Account Created Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                Main exit = new Main();
                dispose();
            }
        }
    }

}
