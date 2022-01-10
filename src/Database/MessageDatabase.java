package Database;

import Entity.User;
import Entity.FriendList;
import Controller.DecodeMessage;
import Entity.Message;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class MessageDatabase implements ILinks {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String relativeemail;
    private String query;

    public String getRelativeMail(FriendList chat, User user) {

        try {
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();

            if (chat.getAdminid() == user.getUserId()) {
                query = "SELECT Email FROM accounts WHERE UserId=" + chat.getRelativeid() + ";";
                rs = st.executeQuery(query);
                rs.next();
                relativeemail = rs.getString("Email");
                query = "UPDATE Relation SET AdminState=0 WHERE Id=" + chat.getTableid() + ";";
                int effect = st.executeUpdate(query);

            } else {
                query = "SELECT Email FROM accounts WHERE UserId=" + chat.getAdminid() + ";";
                ResultSet rs = st.executeQuery(query);
                rs.next();
                relativeemail = rs.getString("Email");
                query = "UPDATE Relation SET RelativeState=0 WHERE Id=" + chat.getTableid() + ";";
                int effect = st.executeUpdate(query);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {

        }
        return relativeemail;
    }

    public boolean updateMessage(String message, FriendList chats, int UserId) {

        try {
            con = DriverManager.getConnection(MessageURL, DUsername, DPassword);
            st = con.createStatement();
            query = "INSERT INTO " + chats.getTablename() + " (`Sender`, `message`) VALUES "
                    + "('" + UserId + "','" + message + "');";
            int effect = st.executeUpdate(query);
            st.close();
            con.close();

            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            if (chats.getAdminid() == UserId) {
                query = "UPDATE Relation SET RelativeState=1 WHERE Id=" + chats.getTableid() + ";";
                effect = st.executeUpdate(query);
            } else {
                query = "UPDATE Relation SET AdminState=1 WHERE Id=" + chats.getTableid() + ";";
                effect = st.executeUpdate(query);
            }
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);

        }
        return false;
    }

    public LinkedList getAllMessages(String tablename) {
        DecodeMessage decode = new DecodeMessage();
        LinkedList<Message> chats = new LinkedList<>();
        try {
            con = DriverManager.getConnection(MessageURL, DUsername, DPassword);
            st = con.createStatement();
            query = "SELECT Sender,message FROM " + tablename + ";";
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                try {
                    Message obj = new Message(rs.getInt("Sender"), rs.getString("message"));
                    obj.setMessage(decode.getMessage(obj.getMessage()));
                    chats.add(obj);
                } catch (Exception ex) {
                    Message obj = new Message(rs.getInt("Sender"), "");
                    chats.add(obj);
                }
            }
            st.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return chats;
    }

    public int CheckNewMessage(String tablename) {
        int count = 0;
        try {
            query = "SELECT Sender,message FROM " + tablename + ";";
            con = DriverManager.getConnection(MessageURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                count++;
            }
            st.close();
            con.close();
        } catch (Exception ex) {

        }
        return count;
    }
}
