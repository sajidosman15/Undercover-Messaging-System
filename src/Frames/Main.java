package Frames;

import Database.CheckDatabase;
import java.awt.CardLayout;
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

public class Main extends JFrame implements ActionListener, MouseListener {

    private final Container container;
    private JPanel panel, panel1, panel2, panel3;
    private JButton login, signin;
    private JLabel welcome;
    private CardLayout card;
    private Font font1, font2;

    public Main() {
        this.setVisible(true);
        this.setTitle("Undercover Message");
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
        card = new CardLayout();
        font1 = new Font("Arial", Font.BOLD, 22);
        font2 = new Font("Arial", Font.BOLD + Font.HANGING_BASELINE, 30);
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        welcome = new JLabel("            WELCOME");
        login = new JButton("LOG IN");
        signin = new JButton("CREATE ACCOUNT");
    }

    private void Body() {

        panel.setBounds(175, 125, 500, 400);
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        container.add(panel);

        panel1.setBounds(125, 175, 250, 50);
        panel1.setLayout(card);
        panel.add(panel1);

        panel2.setBounds(125, 250, 250, 50);
        panel2.setLayout(card);
        panel.add(panel2);

        panel3.setBounds(75, 65, 350, 50);
        panel3.setLayout(card);
        panel.add(panel3);

        welcome.setOpaque(true);
        welcome.setFont(font2);
        welcome.setBackground(Color.ORANGE);
        welcome.setForeground(Color.BLACK);
        panel3.add(welcome);

        login.setFont(font1);
        login.setBackground(new Color(255, 0, 0));
        login.setForeground(Color.BLACK);
        panel1.add(login);
        login.addMouseListener(this);
        login.addActionListener(this);

        signin.setBackground(new Color(0, 153, 255));
        signin.setForeground(Color.BLACK);
        signin.setFont(font1);
        panel2.add(signin);
        signin.addActionListener(this);
        signin.addMouseListener(this);

        card.first(panel1);
        card.first(panel2);
        card.first(panel3);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CheckDatabase database = new CheckDatabase();
        boolean flag = database.CheckConnection();

        if (flag) {
            if (ae.getSource() == signin) {
                Registration begin = new Registration();
                dispose();
            } else if (ae.getSource() == login) {
                LogIn begin = new LogIn();
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
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
        if (me.getSource() == login) {
            login.setBackground(Color.YELLOW);
        } else if (me.getSource() == signin) {
            signin.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == login) {
            login.setBackground(new Color(255, 0, 0));
        } else if (me.getSource() == signin) {
            signin.setBackground(new Color(0, 153, 255));
        }
    }

    public static void main(String[] args) {
        Main Begin = new Main();
    }

}
