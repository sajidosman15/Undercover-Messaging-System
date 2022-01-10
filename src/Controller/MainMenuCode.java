package Controller;

import Entity.FriendList;
import Entity.User;
import Database.MainMenuDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainMenuCode {

    private JButton chat[];
    private Font chatfont, chatfontbold;
    private JPanel chatpanel;
    private LinkedList<FriendList> chats = new LinkedList<>();

    public void addFriends(User user) {

        String friendemail, tablename;;
        int frienduserid;
        try {
            friendemail = JOptionPane.showInputDialog(null, "Enter Friend Email", "Add New Friends", JOptionPane.INFORMATION_MESSAGE);

            if (!friendemail.isEmpty()) {
                if (!user.getEmail().equalsIgnoreCase(friendemail)) {

                    MainMenuDatabase data = new MainMenuDatabase();
                    frienduserid = data.getUserid(friendemail);

                    if (frienduserid != 0) {

                        if (user.getUserId() < frienduserid) {
                            tablename = "tab" + user.getUserId() + frienduserid;
                        } else {
                            tablename = "tab" + frienduserid + user.getUserId();
                        }

                        boolean flag = data.getTableName(tablename);

                        if (flag) {

                            JOptionPane.showMessageDialog(null, "This Friend is Added Previously", "Warning", 2);

                        } else {

                            data.updateDatabase(user.getUserId(), frienduserid, tablename);
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "You Can Not Add Your Own Account", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }
        } catch (Exception e) {

        }

    }

    public JButton[] loadChats(User user, JPanel chatpanel, JPanel middlepanel, JScrollPane scroll, GridLayout grid3, int chatsize) {
        
        chatfont = new Font("Arial", Font.PLAIN, 25);
        chatfontbold = new Font("Arial", Font.BOLD, 30);

        MainMenuDatabase obj = new MainMenuDatabase();
        chats = obj.getChats(user);

        chatpanel = new JPanel();
        chatpanel.setBackground(Color.ORANGE);
        scroll = new JScrollPane(chatpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middlepanel.add(scroll, "first");

        if (!chats.isEmpty()) {
            if (chats.size() < 10) {
                grid3 = new GridLayout(10, 1);
            } else {
                grid3 = new GridLayout(chats.size(), 1);
            }
            grid3.setVgap(3);
            chatpanel.setLayout(grid3);

            chat = new JButton[chats.size()];
            chatsize = chats.size();
            for (int i = 0; i < chats.size(); i++) {
                chat[i] = new JButton(chats.get(i).getRelativename().toUpperCase());

                if (chats.get(i).getAdminid() == user.getUserId()) {
                    if (chats.get(i).getAdminstate() == 1) {
                        chat[i].setFont(chatfontbold);
                        chat[i].setForeground(Color.GREEN);
                    } else {
                        chat[i].setFont(chatfont);
                        chat[i].setForeground(Color.WHITE);
                    }
                } else {
                    if (chats.get(i).getRelativestate() == 1) {
                        chat[i].setFont(chatfontbold);
                        chat[i].setForeground(Color.GREEN);
                    } else {
                        chat[i].setFont(chatfont);
                        chat[i].setForeground(Color.WHITE);
                    }
                }
                chatpanel.add(chat[i]);

            }

        } else {
            this.chatpanel=chatpanel;
            grid3 = new GridLayout(10, 1);
            chatpanel.setLayout(grid3);

        }

        return chat;

    }
    
    public JPanel getChatPanel(){
        return chatpanel;
    }

    public LinkedList<FriendList> getChats() {
        return chats;
    }

}
