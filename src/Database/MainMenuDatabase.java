package Database;

import Entity.User;
import Entity.FriendList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class MainMenuDatabase implements ILinks {

    private String query;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public int getUserid(String email) {
        int userid;
        try {
            query = "SELECT UserId FROM `accounts` WHERE Email='" + email + "';";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next() == true) {
                userid = rs.getInt("UserId");
                st.close();
                con.close();
                return userid;
            } else {
                JOptionPane.showMessageDialog(null, "No Account Found Of This Email", "Warning", 2);
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return 0;
    }

    public boolean getTableName(String tablename) {
        try {
            query = "SELECT Id FROM `Relation` WHERE Tablename='" + tablename + "';";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next() == true) {
                return true;
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    public void updateDatabase(int userid, int frienduserid, String tablename) {
        int change;
        try {
            query = "INSERT INTO `Relation`(`Admin`, `Relative`, `TableName`, `AdminState`, `RelativeState`) "
                    + "VALUES (" + userid + "," + frienduserid + ",'" + tablename + "',0,0);";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            change = st.executeUpdate(query);
            st.close();
            con.close();

            query = "CREATE TABLE " + tablename + " (Number int AUTO_INCREMENT,Sender int,message Text,PRIMARY KEY(Number));";
            con = DriverManager.getConnection(MessageURL, DUsername, DPassword);
            st = con.createStatement();
            change = st.executeUpdate(query);
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public LinkedList getChats(User user) {

        LinkedList<FriendList> chats = new LinkedList<>();

        try {
            query = "SELECT a.Name,r.* FROM accounts AS a INNER JOIN Relation AS r "
                    + "ON a.UserId=r.Admin OR a.UserId=r.Relative "
                    + "WHERE a.UserId NOT IN(" + user.getUserId() + ") AND " + user.getUserId() + " IN (r.Admin,r.Relative) ORDER BY r.Id DESC;";

            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                FriendList info = new FriendList(rs.getString("Name"), rs.getString("TableName"), rs.getInt("Id"), rs.getInt("Admin"), rs.getInt("Relative"), rs.getInt("AdminState"), rs.getInt("RelativeState"));

                if (info.getAdminid() == user.getUserId()) {
                    if (info.getAdminstate() == 1) {
                        chats.addFirst(info);
                    } else {
                        chats.addLast(info);
                    }
                } else {
                    if (info.getRelativestate() == 1) {
                        chats.addFirst(info);
                    } else {
                        chats.addLast(info);
                    }
                }
            }

            st.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return chats;
    }
}
