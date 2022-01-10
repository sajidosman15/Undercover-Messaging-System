package Frames;

import Entity.User;
import Entity.FriendList;
import Controller.MainMenuCode;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainMenu extends JFrame implements MouseListener, ActionListener {

    private final Container container;
    private JPanel panel, leftpanel, toppanel, rightpanel, bottom, middlepanel, chatpanel;
    private JLabel settingslab, searchlab, block3, block4, block1, emptylab1, emptylab2;
    private JTextField searchfld;
    private BorderLayout border;
    private Font btnfont, labfont, topfont, emptyfont;
    private JButton editprofile, creategroup, invitefriends, addfriends, logout, refresh, search, listen, chat[];
    private GridLayout grid, grid3;
    private final User user;
    private Border bor, bor1, bor2, bor3;
    private CardLayout card;
    private JScrollPane scroll;
    private int chatsize;
    private LinkedList<FriendList> chats = new LinkedList<>();

    public MainMenu(User user) {
        this.user = user;
        this.setVisible(true);
        this.setTitle("Undercover Message");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        container = this.getContentPane();
        container.setLayout(null);
        VariableInitializer();
        Body();
    }

    private void VariableInitializer() {
        card = new CardLayout();
        border = new BorderLayout();
        grid = new GridLayout(13, 1);
        btnfont = new Font("Arial", Font.BOLD, 18);
        labfont = new Font("Arial", Font.BOLD + Font.HANGING_BASELINE, 33);
        topfont = new Font("Arial", Font.BOLD, 25);
        emptyfont = new Font("Arial", Font.BOLD, 22);
        bor1 = BorderFactory.createLineBorder(Color.WHITE, 4);
        bor2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        bor3 = BorderFactory.createBevelBorder(0, Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE);
        bor = BorderFactory.createCompoundBorder(bor3, bor2);
        panel = new JPanel();
        toppanel = new JPanel();
        leftpanel = new JPanel();
        middlepanel = new JPanel();
        rightpanel = new JPanel();
        bottom = new JPanel();
        block1 = new JLabel("s");
        block3 = new JLabel("s");
        block4 = new JLabel("s");
        emptylab1 = new JLabel();
        emptylab2 = new JLabel();
        searchlab = new JLabel("SEARCH BY NAME ");
        settingslab = new JLabel(" SETTINGS");
        searchfld = new JTextField();
        search = new JButton(" SEARCH ");
        editprofile = new JButton("EDIT PROFILE");
        creategroup = new JButton("  CREATE GROUP  ");
        addfriends = new JButton(" ADD NEW FRIENDS ");
        invitefriends = new JButton("INVITE FRIENDS");
        refresh = new JButton("REFRESH");
        logout = new JButton("LOG OUT");
    }

    private void Body() {

        grid.setVgap(10);
        border.setVgap(5);
        border.setHgap(5);

        panel.setBounds(0, 0, 850, 650);
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(border);
        container.add(panel);
        
        //panel.addMouseMotionListener(this);

        rightpanel.setBackground(Color.BLACK);
        panel.add(rightpanel, BorderLayout.EAST);
        block3.setOpaque(true);
        block3.setBackground(Color.BLACK);
        block3.setForeground(Color.BLACK);
        rightpanel.add(block3);

        bottom.setBackground(Color.BLACK);
        panel.add(bottom, BorderLayout.SOUTH);
        block4.setFont(labfont);
        block4.setOpaque(true);
        block4.setBackground(Color.BLACK);
        block4.setForeground(Color.BLACK);
        bottom.add(block4);

        toppanel.setBackground(Color.BLACK);
        toppanel.setBorder(bor);
        panel.add(toppanel, BorderLayout.NORTH);

        searchlab.setFont(topfont);
        searchlab.setOpaque(true);
        searchlab.setBackground(Color.BLACK);
        searchlab.setForeground(Color.WHITE);
        toppanel.add(searchlab);

        searchfld.setText("Write Name Here");
        searchfld.setFont(topfont);
        //searchfld.addKeyListener(this);
        toppanel.add(searchfld);

        block1.setOpaque(true);
        block1.setBackground(Color.BLACK);
        block1.setForeground(Color.BLACK);
        toppanel.add(block1);

        search.setBackground(new Color(130, 240, 150));
        search.setForeground(Color.BLACK);
        search.setFont(topfont);
        search.setBorder(bor);
        search.addMouseListener(this);
        //search.addActionListener(this);
        toppanel.add(search);

        leftpanel.setLayout(grid);
        leftpanel.setBorder(bor);
        leftpanel.setBackground(Color.BLACK);
        panel.add(leftpanel, BorderLayout.WEST);

        settingslab.setOpaque(true);
        settingslab.setFont(labfont);
        settingslab.setForeground(Color.ORANGE);
        settingslab.setBackground(Color.BLACK);
        leftpanel.add(settingslab);

        editprofile.setBackground(new Color(153, 153, 255));
        editprofile.setForeground(Color.BLACK);
        editprofile.setFont(btnfont);
        editprofile.addMouseListener(this);
        editprofile.addActionListener(this);
        editprofile.setBorder(bor);
        leftpanel.add(editprofile);

        creategroup.setBackground(new Color(255, 102, 153));
        creategroup.setForeground(Color.BLACK);
        creategroup.setFont(btnfont);
        creategroup.setBorder(bor);
        creategroup.addMouseListener(this);
        //creategroup.addActionListener(this);
        leftpanel.add(creategroup);

        addfriends.setBackground(new Color(0, 255, 204));
        addfriends.setForeground(Color.BLACK);
        addfriends.setFont(btnfont);
        addfriends.setBorder(bor);
        addfriends.addMouseListener(this);
        addfriends.addActionListener(this);
        leftpanel.add(addfriends);

        invitefriends.setBackground(new Color(240, 168, 112));
        invitefriends.setForeground(Color.BLACK);
        invitefriends.setFont(btnfont);
        invitefriends.setBorder(bor);
        invitefriends.addMouseListener(this);
        //invitefriends.addActionListener(this);
        leftpanel.add(invitefriends);

        refresh.setBackground(new Color(153, 255, 153));
        refresh.setForeground(Color.BLACK);
        refresh.setFont(btnfont);
        refresh.setBorder(bor);
        refresh.addMouseListener(this);
        //refresh.addActionListener(this);
        leftpanel.add(refresh);

        logout.setBackground(new Color(255, 204, 204));
        logout.setForeground(Color.BLACK);
        logout.setFont(btnfont);
        logout.setBorder(bor);
        logout.addActionListener(this);
        logout.addMouseListener(this);
        leftpanel.add(logout);

        middlepanel.setLayout(card);
        middlepanel.setBorder(bor);
        middlepanel.setBackground(Color.ORANGE);
        panel.add(middlepanel, BorderLayout.CENTER);

        loadChats();

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
        if (me.getSource() == editprofile) {
            editprofile.setBackground(Color.RED);

        } else if (me.getSource() == creategroup) {
            creategroup.setBackground(Color.RED);

        } else if (me.getSource() == addfriends) {
            addfriends.setBackground(Color.RED);

        } else if (me.getSource() == invitefriends) {
            invitefriends.setBackground(Color.RED);

        } else if (me.getSource() == refresh) {
            refresh.setBackground(Color.RED);

        } else if (me.getSource() == logout) {
            logout.setBackground(Color.RED);

        } else if (me.getSource() == search) {
            search.setBackground(Color.RED);
        } else {
            listen = (JButton) me.getSource();
            listen.setBackground(Color.RED);
        }

    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == editprofile) {
            editprofile.setBackground(new Color(153, 153, 255));
        } else if (me.getSource() == creategroup) {
            creategroup.setBackground(new Color(255, 102, 153));

        } else if (me.getSource() == addfriends) {
            addfriends.setBackground(new Color(0, 255, 204));

        } else if (me.getSource() == invitefriends) {
            invitefriends.setBackground(new Color(240, 168, 112));

        } else if (me.getSource() == refresh) {
            refresh.setBackground(new Color(153, 255, 153));

        } else if (me.getSource() == logout) {
            logout.setBackground(new Color(255, 204, 204));

        } else if (me.getSource() == search) {
            search.setBackground(new Color(130, 240, 150));
        } else {
            listen = (JButton) me.getSource();
            listen.setBackground(Color.BLACK);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addfriends) {
            MainMenuCode obj = new MainMenuCode();
            obj.addFriends(user);
            loadChats();
        } else if (ae.getSource() == logout) {
            LogIn begin = new LogIn();
            dispose();
        }else if (ae.getSource() == editprofile) {
            EditProfile begin = new EditProfile(user);
            dispose();
        } else {
            listen = (JButton) ae.getSource();
            int index = Integer.parseInt(listen.getToolTipText());
            if (chats.get(index).getTablename().contains("tab")) {
                MessageFrame begin = new MessageFrame(chats.get(index), user);
                dispose();
            }
        }
    }

    private void loadChats() {
        MainMenuCode obj = new MainMenuCode();
        chat = obj.loadChats(user, chatpanel, middlepanel, scroll, grid3, chatsize);

        if (chat != null) {
            this.chats = obj.getChats();
            for (int i = 0; i < chat.length; i++) {
                chat[i].setBorder(bor1);
                chat[i].setBackground(Color.BLACK);
                chat[i].setToolTipText("" + i);
                chat[i].addActionListener(this);
                chat[i].addMouseListener(this);
            }
            card.show(middlepanel, "first");
        } else {
            chatpanel = obj.getChatPanel();
            emptylab1.setText("           YOU HAVE NOT ADDRED ANY FRIEND YET");
            emptylab1.setForeground(Color.RED);
            emptylab1.setFont(emptyfont);
            chatpanel.add(emptylab1);
            emptylab2.setText(" PLEASE ADD A FRIEND FIRST TO START CONVERSION");
            emptylab2.setForeground(Color.RED);
            emptylab2.setFont(emptyfont);
            chatpanel.add(emptylab2);
        }
    }

}
