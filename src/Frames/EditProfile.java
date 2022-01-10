package Frames;

import Entity.User;
import Controller.EditProfileCode;
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

public class EditProfile extends JFrame implements ActionListener, MouseListener {

    private final Container container;
    private User user;
    private JPanel bodyPanel;
    private JLabel namelab, passwordlab, confirmpasslab;
    private JLabel banerlab, nameerr, passerr, conpasserr, changeerr;
    private Font banerlabfont, fieldfont, errorfont, buttonfont, labelfont, font1;
    private JTextField namefld;
    private JPasswordField passwordfld, confirmpassfld;
    private JButton editname, editpass, submit, back, confirm;

    public EditProfile(User user) {
        this.setVisible(true);
        this.setTitle("Edit Profile");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container = this.getContentPane();
        container.setBackground(new Color(0, 51, 51));
        container.setLayout(null);
        this.user = user;
        variableInitializer();
        body();
    }

    private void variableInitializer() {
        bodyPanel = new JPanel();
        banerlab = new JLabel();
        namelab = new JLabel();
        namefld = new JTextField();
        nameerr = new JLabel();
        passwordlab = new JLabel();
        passwordfld = new JPasswordField();
        passerr = new JLabel();
        confirmpasslab = new JLabel();
        confirmpassfld = new JPasswordField();
        conpasserr = new JLabel();
        changeerr = new JLabel();
        editname = new JButton("CHANGE");
        editpass = new JButton("CHANGE");
        submit = new JButton("SUBMIT");
        back = new JButton("BACK");
        confirm = new JButton("CONFIRM");

    }

    private void body() {

        banerlabfont = new Font("Arial", Font.BOLD, 30);
        fieldfont = new Font("Arial", Font.BOLD, 17);
        errorfont = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
        labelfont = new Font("Arial", Font.BOLD, 16);
        buttonfont = new Font("Arial", Font.BOLD, 20);
        font1 = new Font("Arial", Font.BOLD, 22);

        bodyPanel.setBounds(125, 60, 595, 500);
        bodyPanel.setLayout(null);
        bodyPanel.setBackground(Color.BLACK);
        container.add(bodyPanel);

        banerlab.setText("          EDIT YOUR INFORMATION");
        banerlab.setFont(banerlabfont);
        banerlab.setBounds(20, 40, 555, 50);
        banerlab.setOpaque(true);
        banerlab.setBackground(Color.ORANGE);
        banerlab.setForeground(Color.BLACK);
        bodyPanel.add(banerlab);

        namelab.setText("  Current Name");
        namelab.setBounds(20, 130, 180, 35);
        namelab.setFont(labelfont);
        namelab.setForeground(Color.WHITE);
        namelab.setOpaque(true);
        namelab.setBackground(Color.BLACK);
        bodyPanel.add(namelab);

        namefld.setBounds(200, 130, 245, 35);
        namefld.setText(user.getName());
        namefld.setEditable(false);
        namefld.setFont(fieldfont);
        bodyPanel.add(namefld);

        nameerr.setBounds(200, 168, 243, 18);
        nameerr.setForeground(Color.RED);
        nameerr.setFont(errorfont);
        bodyPanel.add(nameerr);

        editname.setBounds(455, 130, 120, 35);
        editname.setForeground(Color.BLACK);
        editname.setFont(buttonfont);
        bodyPanel.add(editname);
        editname.addActionListener(this);

        passwordlab.setText("  Current Password");
        passwordlab.setBounds(20, 210, 180, 35);
        passwordlab.setFont(labelfont);
        passwordlab.setForeground(Color.WHITE);
        passwordlab.setOpaque(true);
        passwordlab.setBackground(Color.BLACK);
        bodyPanel.add(passwordlab);

        passwordfld.setBounds(200, 210, 245, 35);
        passwordfld.setText(user.getPassword());
        passwordfld.setEditable(false);
        passwordfld.setFont(banerlabfont);
        bodyPanel.add(passwordfld);

        passerr.setBounds(200, 248, 243, 18);
        passerr.setForeground(Color.RED);
        passerr.setFont(errorfont);
        bodyPanel.add(passerr);

        editpass.setBounds(455, 210, 120, 35);
        editpass.setForeground(Color.BLACK);
        editpass.setFont(buttonfont);
        bodyPanel.add(editpass);
        editpass.addActionListener(this);

        changeerr.setBounds(150, 255, 300, 35);
        changeerr.setForeground(Color.RED);
        changeerr.setFont(fieldfont);
        bodyPanel.add(changeerr);

        submit.setBounds(325, 300, 150, 45);
        submit.setBackground(new Color(255, 0, 0));
        submit.setForeground(Color.BLACK);
        submit.setFont(font1);
        bodyPanel.add(submit);
        submit.addActionListener(this);
        submit.addMouseListener(this);

        back.setBackground(new Color(0, 153, 255));
        back.setForeground(Color.BLACK);
        back.setFont(font1);
        bodyPanel.add(back);
        back.addActionListener(this);
        back.addMouseListener(this);

        confirmpasslab.setText("  Confirm Your Password");
        confirmpasslab.setBounds(60, 310, 200, 35);
        confirmpasslab.setFont(labelfont);
        confirmpasslab.setForeground(Color.WHITE);
        confirmpasslab.setOpaque(true);
        confirmpasslab.setBackground(Color.BLACK);
        bodyPanel.add(confirmpasslab);

        confirmpassfld.setBounds(270, 310, 245, 35);
        confirmpassfld.setFont(banerlabfont);
        bodyPanel.add(confirmpassfld);

        conpasserr.setBounds(150, 355, 300, 35);
        conpasserr.setForeground(Color.RED);
        conpasserr.setFont(fieldfont);
        bodyPanel.add(conpasserr);

        confirm.setBounds(325, 400, 150, 45);
        confirm.setBackground(new Color(255, 0, 0));
        confirm.setForeground(Color.BLACK);
        confirm.setFont(font1);
        bodyPanel.add(confirm);
        confirm.addActionListener(this);
        confirm.addMouseListener(this);

        hideComponent();
    }

    private void hideComponent() {
        changeerr.setText("");
        conpasserr.setText(" ");
        confirmpassfld.setBackground(Color.WHITE);
        confirmpassfld.setText("");
        back.setBounds(115, 300, 150, 45);
        submit.setVisible(true);
        confirmpasslab.setVisible(false);
        confirmpassfld.setVisible(false);
        conpasserr.setVisible(false);
        confirm.setVisible(false);
    }

    private void showComponent() {
        back.setBounds(115, 400, 150, 45);
        submit.setVisible(false);
        confirmpasslab.setVisible(true);
        confirmpassfld.setVisible(true);
        conpasserr.setVisible(true);
        confirm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        EditProfileCode check = new EditProfileCode(user);
        if (ae.getSource() == editname) {
            hideComponent();
            if (editname.getText().equals("CHANGE")) {
                editname.setText("OK");
                namelab.setText("  Enter New Name");
                namefld.setEditable(true);
                namefld.setBackground(Color.WHITE);
            } else {
                check.checkName(namelab, editname, namefld, nameerr);
            }
        } else if (ae.getSource() == editpass) {
            hideComponent();
            if (editpass.getText().equals("CHANGE")) {
                editpass.setText("OK");
                passwordlab.setText("  Enter New Password");
                passwordfld.setEditable(true);
                passwordfld.setText("");
                passwordfld.setBackground(Color.WHITE);
            } else {
                check.checkPassword(passwordlab, editpass, passwordfld, passerr);
            }
        } else if (ae.getSource() == back) {
            MainMenu begin = new MainMenu(user);
            dispose();
        } else if (ae.getSource() == submit) {
            boolean flag = check.checkAll(changeerr, namelab, editname, namefld, nameerr, passwordlab, editpass, passwordfld, passerr);
            if (flag) {
                showComponent();
            }
        } else if (ae.getSource() == confirm) {
            boolean flag = check.modify(confirmpassfld, conpasserr, namefld.getText(), passwordfld.getText());
            if (flag) {
                MainMenu begin = new MainMenu(user);
                dispose();
            }
        }
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
        if (me.getSource() == submit) {
            submit.setBackground(Color.YELLOW);
        } else if (me.getSource() == back) {
            back.setBackground(Color.YELLOW);
        } else if (me.getSource() == confirm) {
            confirm.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submit) {
            submit.setBackground(new Color(255, 0, 0));
        } else if (me.getSource() == confirm) {
            confirm.setBackground(new Color(255, 0, 0));
        } else if (me.getSource() == back) {
            back.setBackground(new Color(0, 153, 255));
        }
    }
}
