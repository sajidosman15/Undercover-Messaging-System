package Database;

import Entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginCheck implements ILinks {

    private User user;
    private String query;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public User getAccount(String username) {
        try {
            query = "SELECT * FROM `accounts` WHERE UserName='" + username + "';";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next() == true) {
                user = new User(rs.getInt("UserId"), rs.getString("Name"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("DeviceId"));
            }
            st.close();
            con.close();

        } catch (Exception ex) {

        }
        return user;
    }
}
