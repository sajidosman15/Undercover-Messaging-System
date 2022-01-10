package Frames;

import Entity.User;
import Entity.FriendList;
import Controller.MessageCode;
import Database.MessageDatabase;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MessageFrame extends JFrame implements MouseListener, ActionListener, Runnable {

    private final Container container;
    private final FriendList chat;
    private final User user;
    private JPanel toppanel, bottompanel, sidepanel, centerpanel, midpanel;
    private JButton back, refresh, send, decode, hide;
    private Font btnfont, textfont, labfont;
    private Border bor, bor2, bor3;
    private JTextArea chatbox;
    private JScrollPane scroll;
    private JLabel namelab;
    private String relativeemail;
    private CardLayout card;
    private int chatsize;
    private GridBagConstraints bag;
    private boolean runthread = true, hidden;

    public MessageFrame(FriendList chat, User user) {
        this.user = user;
        this.chat = chat;
        this.setTitle(chat.getRelativename());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.DARK_GRAY);
        Initialize();
        VariableInitializer();
        Body();
    }

    private void Initialize() {
        MessageDatabase obj = new MessageDatabase();
        relativeemail = obj.getRelativeMail(chat, user);
    }

    private void VariableInitializer() {
        bor2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        bor3 = BorderFactory.createBevelBorder(0, Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE);
        bor = BorderFactory.createCompoundBorder(bor3, bor2);
        btnfont = new Font("Arial", Font.BOLD, 22);
        textfont = new Font("Arial", Font.BOLD, 17);
        labfont = new Font("Arial", Font.BOLD, 30);
        card = new CardLayout();
        toppanel = new JPanel();
        sidepanel = new JPanel();
        centerpanel = new JPanel();
        bottompanel = new JPanel();
        chatbox = new JTextArea();
        namelab = new JLabel(chat.getRelativename().toUpperCase());
        back = new JButton(" BACK ");
        refresh = new JButton(" REFRESH ");
        send = new JButton(" SEND ");
        decode = new JButton(" DECODE ");
        hide = new JButton(" HIDE ");
        midpanel = new JPanel(new GridBagLayout());
        bag = new GridBagConstraints();
    }

    private void Body() {

        toppanel.setBounds(10, 15, 825, 50);
        toppanel.setBackground(Color.ORANGE);
        toppanel.setBorder(bor);
        container.add(toppanel);

        namelab.setBackground(Color.ORANGE);
        namelab.setForeground(Color.BLACK);
        namelab.setFont(labfont);
        namelab.setOpaque(true);
        toppanel.add(namelab);

        sidepanel.setBounds(10, 75, 180, 535);
        sidepanel.setBackground(Color.BLACK);
        sidepanel.setBorder(bor);
        sidepanel.setLayout(null);
        container.add(sidepanel);

        centerpanel.setBounds(200, 75, 635, 435);
        centerpanel.setLayout(card);
        centerpanel.setBorder(bor);
        centerpanel.setBackground(Color.BLACK);
        container.add(centerpanel);

        bottompanel.setBounds(200, 510, 635, 100);
        bottompanel.setBackground(Color.BLACK);
        bottompanel.setLayout(null);
        bottompanel.setBorder(bor);
        container.add(bottompanel);

        back.setBounds(5, 15, 170, 35);
        back.setBackground(new Color(255, 204, 204));
        back.setForeground(Color.BLACK);
        back.setFont(btnfont);
        back.setBorder(bor);
        sidepanel.add(back);
        back.addMouseListener(this);
        back.addActionListener(this);

        refresh.setBounds(5, 60, 170, 35);
        refresh.setBackground(new Color(153, 255, 153));
        refresh.setForeground(Color.BLACK);
        refresh.setFont(btnfont);
        refresh.setBorder(bor);
        sidepanel.add(refresh);
        refresh.addMouseListener(this);
        refresh.addActionListener(this);

        decode.setBounds(5, 105, 170, 35);
        decode.setBackground(new Color(255, 102, 153));
        decode.setForeground(Color.BLACK);
        decode.setFont(btnfont);
        decode.setBorder(bor);
        sidepanel.add(decode);
        decode.addMouseListener(this);
        decode.addActionListener(this);

        hide.setBounds(5, 150, 170, 35);
        hide.setBackground(new Color(0, 255, 204));
        hide.setForeground(Color.BLACK);
        hide.setFont(btnfont);
        hide.setBorder(bor);
        sidepanel.add(hide);
        hide.addMouseListener(this);
        hide.addActionListener(this);

        chatbox.setBackground(Color.WHITE);
        chatbox.setLineWrap(true);
        chatbox.setWrapStyleWord(true);
        chatbox.setFont(textfont);
        scroll = new JScrollPane(chatbox);
        scroll.setBounds(5, 5, 515, 90);
        bottompanel.add(scroll);

        send.setBounds(525, 10, 100, 80);
        send.setBackground(new Color(255, 102, 153));
        send.setForeground(Color.BLACK);
        send.setFont(btnfont);
        send.setBorder(bor);
        bottompanel.add(send);
        send.addMouseListener(this);
        send.addActionListener(this);

        bag.insets = new Insets(5, 10, 5, 10);
        bag.anchor = GridBagConstraints.LINE_START;
        midpanel.setBackground(Color.BLACK);
        scroll = new JScrollPane(midpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerpanel.add(scroll, "first");

        loadCodes();
        hidden = true;

        Thread thread = new Thread(this);
        thread.start();
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
        if (me.getSource() == back) {
            back.setBounds(2, 12, 176, 41);
            back.setBackground(Color.RED);
        } else if (me.getSource() == refresh) {
            refresh.setBounds(2, 57, 176, 41);
            refresh.setBackground(Color.RED);
        } else if (me.getSource() == decode) {
            decode.setBounds(2, 102, 176, 41);
            decode.setBackground(Color.RED);
        } else if (me.getSource() == hide) {
            hide.setBounds(2, 147, 176, 41);
            hide.setBackground(Color.RED);
        } else if (me.getSource() == send) {
            send.setBounds(522, 7, 106, 86);
            send.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == back) {
            back.setBounds(5, 15, 170, 35);
            back.setBackground(new Color(255, 204, 204));
        } else if (me.getSource() == refresh) {
            refresh.setBounds(5, 60, 170, 35);
            refresh.setBackground(new Color(153, 255, 153));
        } else if (me.getSource() == decode) {
            decode.setBounds(5, 105, 170, 35);
            decode.setBackground(new Color(255, 102, 153));
        } else if (me.getSource() == hide) {
            hide.setBounds(5, 150, 170, 35);
            hide.setBackground(new Color(0, 255, 204));
        } else if (me.getSource() == send) {
            send.setBounds(525, 10, 100, 80);
            send.setBackground(new Color(255, 102, 153));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            runthread = false;
            MainMenu begin = new MainMenu(user);
            dispose();
        } else if (ae.getSource() == refresh) {
            runthread = false;
            MessageFrame begin = new MessageFrame(chat, user);
            dispose();
        } else if (ae.getSource() == hide) {
            hidden = true;
            loadCodes();
        } else if (ae.getSource() == decode && hidden == true) {
            MessageCode obj = new MessageCode();
            boolean flag = obj.MatchPassword(user.getPassword());
            if (flag == true) {
                hidden = false;
                loadChats();
            }
        } else if (ae.getSource() == send) {
            MessageCode obj = new MessageCode();
            boolean flag = obj.sendMessage(chatbox.getText(), relativeemail, chat, user.getUserId());
            if (flag) {
                chatbox.setText("");
                if (hidden == true) {
                    loadCodes();
                } else {
                    loadChats();
                }
            }
        }
    }

    private void loadChats() {
        MessageCode show = new MessageCode();
        show.loadChats(user, chat, relativeemail, textfont, bag, midpanel);
        chatsize = show.getChatsize();
        card.show(centerpanel, "first");

    }

    private void loadCodes() {
        MessageCode show = new MessageCode();
        show.loadCodes(user, chat, relativeemail, textfont, bag, midpanel);
        chatsize = show.getChatsize();
        card.show(centerpanel, "first");

    }

    @Override
    public void run() {
        while (runthread == true) {
            MessageDatabase obj = new MessageDatabase();
            int count = obj.CheckNewMessage(chat.getTablename());
            if (count > chatsize && hidden == false) {
                loadChats();
            }
            if (count > chatsize && hidden == true) {
                loadCodes();
            }
        }
    }

}
