package Database;

import Entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationCheck implements ILinks {

    private String query;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public boolean checkUname(String uname) {

        try {
            query = "SELECT UserId FROM accounts WHERE UserName='" + uname + "';";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next() == true) {
                return false;
            }
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean checkEmail(String email) {
        try {
            query = "SELECT UserId FROM accounts WHERE Email='" + email + "';";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next() == true) {
                return false;
            }
            st.close();
            con.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public int getUserId() {
        try {
            query = "SELECT COUNT(*) AS Total FROM accounts;";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            int userid = rs.getInt("Total");
            userid++;
            st.close();
            con.close();
            return userid;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public boolean updateAccounts(User user) {
        try {
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            query = "INSERT INTO `accounts`(`UserId`, `Name`, `UserName`, `Email`, `Password`, `DeviceId`) VALUES\n"
                    + "(" + user.getUserId() + ",'" + user.getName() + "','" + user.getUsername() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getSavedDeviceId() + "');";
            int effect = st.executeUpdate(query);
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean editProfile(int userid, String name, String password) {
        try {
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            query = "UPDATE accounts SET Name='" + name + "',Password='" + password + "' WHERE UserId=" + userid + ";";
            int effect = st.executeUpdate(query);
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
