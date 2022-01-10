package Controller;

import Entity.Message;
import Entity.FriendList;
import Entity.User;
import Database.MessageDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MessageCode {

    private LinkedList<Message> chats = new LinkedList<>();
    private int chatsize;
    private JTextArea area;
    private String[] splitmessage = new String[10];
    private Border bor;

    public boolean sendMessage(String message, String relativeemail, FriendList chats, int UserId) {
        boolean flag = false;
        CreateMessage create = new CreateMessage();
        if (!message.isEmpty()) {
            if (message.contains("'")) {
                message = message.replace("'", " ");
            }
            message = relativeemail + message;
            message = create.getMessage(message);
            MessageDatabase obj = new MessageDatabase();
            flag = obj.updateMessage(message, chats, UserId);
        }
        return flag;
    }

    public void loadChats(User user, FriendList chat, String relativeemail, Font textfont, GridBagConstraints bag, JPanel midpanel) {
        midpanel.removeAll();
        MessageDatabase message = new MessageDatabase();
        chats = message.getAllMessages(chat.getTablename());
        chatsize = chats.size();

        for (int i = 0; i < chatsize; i++) {
            area = new JTextArea();
            area.setLineWrap(true);
            area.setFont(textfont);
            area.setWrapStyleWord(true);
            area.setEditable(false);

            if (chats.get(i).getSender() == user.getUserId()) {
                if (chats.get(i).getMessage().contains(relativeemail)) {
                    splitmessage = chats.get(i).getMessage().split(relativeemail);
                    area.setText(splitmessage[1]);
                } else {
                    area.setText("This message is not belongs to you.");
                }
                area.setBackground(Color.BLUE);
                bor = BorderFactory.createLineBorder(Color.BLUE, 7);
                area.setBorder(bor);
                area.setForeground(Color.BLACK);
                area.setFont(textfont);
                area.setSize(280, 280);
                bag.gridx = 1;
                bag.gridy = i;
                midpanel.add(area, bag);
            } else {
                if (chats.get(i).getMessage().contains(user.getEmail())) {
                    splitmessage = chats.get(i).getMessage().split(user.getEmail());
                    area.setText(splitmessage[1]);
                } else {
                    area.setText("This message is not belongs to you.");
                }
                area.setFont(textfont);
                area.setSize(280, 280);
                bag.gridx = 0;
                bag.gridy = i;
                midpanel.add(area, bag);
                bor = BorderFactory.createLineBorder(Color.GREEN, 7);
                area.setBorder(bor);
                area.setBackground(Color.GREEN);
                area.setForeground(Color.BLACK);
            }

        }
    }

    public void loadCodes(User user, FriendList chat, String relativeemail, Font textfont, GridBagConstraints bag, JPanel midpanel) {
        midpanel.removeAll();
        MessageDatabase message = new MessageDatabase();
        chats = message.getAllMessages(chat.getTablename());
        chatsize = chats.size();

        for (int i = 0; i < chatsize; i++) {
            area = new JTextArea();
            area.setLineWrap(true);
            area.setFont(textfont);
            area.setWrapStyleWord(true);
            area.setEditable(false);

            String codemessage = "Message is hidden";

            if (chats.get(i).getSender() == user.getUserId()) {
                if (chats.get(i).getMessage().contains(relativeemail)) {
                    splitmessage = chats.get(i).getMessage().split(relativeemail);
                    area.setText(codemessage);
                } else {
                    area.setText(codemessage);
                }
                area.setBackground(Color.BLUE);
                bor = BorderFactory.createLineBorder(Color.BLUE, 7);
                area.setBorder(bor);
                area.setForeground(Color.BLACK);
                area.setFont(textfont);
                area.setSize(280, 280);
                bag.gridx = 1;
                bag.gridy = i;
                midpanel.add(area, bag);
            } else {
                if (chats.get(i).getMessage().contains(user.getEmail())) {
                    splitmessage = chats.get(i).getMessage().split(user.getEmail());
                    area.setText(codemessage);
                } else {
                    area.setText(codemessage);
                }
                area.setFont(textfont);
                area.setSize(280, 280);
                bag.gridx = 0;
                bag.gridy = i;
                midpanel.add(area, bag);
                bor = BorderFactory.createLineBorder(Color.GREEN, 7);
                area.setBorder(bor);
                area.setBackground(Color.GREEN);
                area.setForeground(Color.BLACK);
            }

        }
    }

    public int getChatsize() {
        return chatsize;
    }

    public boolean MatchPassword(String password) {

        try {
            String pass = JOptionPane.showInputDialog(null, "Enter Your Password");
            if (pass.equals(password)) {
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Password does not match", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
        return false;
    }

}
