package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDatabase implements ILinks {

    private String query;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public boolean CheckInternet() {
        try {
            query = "SELECT COUNT(*) AS Total FROM accounts;";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            st.close();
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean CheckConnection() {
        try {
            query = "SELECT COUNT(*) AS Total FROM accounts;";
            con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
            st = con.createStatement();
            rs = st.executeQuery(query);
            st.close();
            con.close();
            return true;
        } catch (Exception ex) {
            try {
                query = "CREATE DATABASE messages;";
                con = DriverManager.getConnection(DatabaseURL, DUsername, DPassword);
                st = con.createStatement();
                int effect = st.executeUpdate(query);
                query = "CREATE DATABASE undercover;";
                effect = st.executeUpdate(query);
                st.close();
                con.close();
                con = DriverManager.getConnection(AccountsURL, DUsername, DPassword);
                st = con.createStatement();
                query = "CREATE TABLE accounts (UserId int,Name varchar(50),UserName varchar(50),Email varchar(50),"
                        + "Password varchar(20),DeviceId varchar(50),PRIMARY KEY(UserId));";
                effect = st.executeUpdate(query);
                query = "CREATE TABLE relation (Id int AUTO_INCREMENT,Admin int,Relative int,TableName varchar(40),AdminState int,"
                        + "RelativeState int,PRIMARY KEY(Id),FOREIGN KEY (Admin) REFERENCES accounts(UserId),"
                        + "FOREIGN KEY (Relative) REFERENCES accounts(UserId));";
                effect = st.executeUpdate(query);
                st.close();
                con.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
