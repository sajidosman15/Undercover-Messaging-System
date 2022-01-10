package Controller;

import Entity.User;
import Database.CheckDatabase;
import Database.RegistrationCheck;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationCode {

    private String name, username, email, pass, deviceid;
    private char password[], conpassword[];
    private boolean error1, error2, error3, error4, error5, error6, nointernet;
    private int userid;
    private User user;

    public RegistrationCode(JTextField name, JTextField username, JTextField email, JPasswordField password, JPasswordField conpassword) {
        this.name = name.getText();
        this.username = username.getText();
        this.email = email.getText();
        this.password = new char[22];
        this.password = password.getPassword();
        this.conpassword = new char[22];
        this.conpassword = conpassword.getPassword();
    }

    public User checkAll(JTextField namefld, JTextField usernamefld, JTextField emailfld, JPasswordField passwordfld, JPasswordField confirmpassfld, JLabel nameerr, JLabel unameerr, JLabel emailerr, JLabel passerr, JLabel conpasserr) {
        nointernet = false;
        checkName(namefld, nameerr);
        checkUsername(usernamefld, unameerr);
        checkEmail(emailfld, emailerr);
        checkPassword(passwordfld, passerr);
        checkConfirmPass(confirmpassfld, conpasserr);
        checkDeviceId();
        checkUserId();
        verify();
        return user;
    }

    private void checkName(JTextField namefld, JLabel nameerr) {
        name = name.trim();
        if (name.isEmpty()) {
            nameerr.setText("Name can not be empty.");
            namefld.setBackground(Color.RED);
            error1 = false;
        } else if (name.length() > 30) {
            nameerr.setText("Maximum length is 30.");
            namefld.setBackground(Color.RED);
            error1 = false;
        } else if (name.contains("'")) {
            nameerr.setText("Special character is not allowed.");
            namefld.setBackground(Color.RED);
            error1 = false;
        } else {
            nameerr.setText(" ");
            namefld.setBackground(Color.WHITE);
            error1 = true;
        }
    }

    private void checkUsername(JTextField usernamefld, JLabel unameerr) {
        username = username.trim();
        if (username.isEmpty()) {
            unameerr.setText("Username can not be empty.");
            usernamefld.setBackground(Color.RED);
            error2 = false;
        } else if (username.contains(" ")) {
            unameerr.setText("Space is not allowed.");
            usernamefld.setBackground(Color.RED);
            error2 = false;
        } else if (username.contains("'")) {
            unameerr.setText("Special character is not allowed.");
            usernamefld.setBackground(Color.RED);
            error2 = false;
        } else if (username.length() > 20) {
            unameerr.setText("Maximum length is 20.");
            usernamefld.setBackground(Color.RED);
            error2 = false;
        } else {
            RegistrationCheck check = new RegistrationCheck();
            boolean flag = check.checkUname(username);
            if (flag == false) {
                CheckDatabase connection = new CheckDatabase();
                boolean internet = connection.CheckInternet();
                error2 = false;
                if (internet == true) {
                    unameerr.setText("Username is already taken.");
                    usernamefld.setBackground(Color.RED);
                } else {
                    nointernet = true;
                }
            } else {
                unameerr.setText(" ");
                usernamefld.setBackground(Color.WHITE);
                error2 = true;
            }
        }
    }

    private void checkEmail(JTextField emailfld, JLabel emailerr) {
        email = email.trim();
        if (email.contains(" ")) {
            emailerr.setText("Invalid email.");
            emailfld.setBackground(Color.RED);
            error3 = false;
        } else if (email.isEmpty()) {
            emailerr.setText("Email can not be empty.");
            emailfld.setBackground(Color.RED);
            error3 = false;
        } else if (email.length() > 40) {
            emailerr.setText("Invalid email.");
            emailfld.setBackground(Color.RED);
            error3 = false;
        } else if (email.contains("'")) {
            emailerr.setText("Special character is not allowed.");
            emailfld.setBackground(Color.RED);
            error3 = false;
        } else if (!email.contains("@") || !email.contains(".")) {
            emailerr.setText("Invalid email.");
            emailfld.setBackground(Color.RED);
            error3 = false;
        } else {
            RegistrationCheck check = new RegistrationCheck();
            boolean flag = check.checkEmail(email);
            if (flag == false) {
                CheckDatabase connection = new CheckDatabase();
                boolean internet = connection.CheckInternet();
                error3 = false;
                if (internet == true) {
                    emailerr.setText("Email is already used.");
                    emailfld.setBackground(Color.RED);
                } else {
                    nointernet = true;
                }
            } else {
                emailerr.setText(" ");
                emailfld.setBackground(Color.WHITE);
                error3 = true;
            }
        }
    }

    private void checkPassword(JPasswordField passwordfld, JLabel passerr) {
        pass = "";
        for (int i = 0; i < password.length; i++) {
            pass = pass + password[i];
        }
        pass = pass.trim();
        if (pass.isEmpty()) {
            passerr.setText("Password can not be empty.");
            passwordfld.setBackground(Color.RED);
            error4 = false;
        } else if (pass.length() < 7) {
            passerr.setText("Password is too short.");
            passwordfld.setBackground(Color.RED);
            error4 = false;
        } else if (pass.length() > 20) {
            passerr.setText("Maximum length is 20.");
            passwordfld.setBackground(Color.RED);
            error4 = false;
        } else if (pass.contains("'")) {
            passerr.setText("Special character is not allowed.");
            passwordfld.setBackground(Color.RED);
            error4 = false;
        } else {
            passerr.setText(" ");
            passwordfld.setBackground(Color.WHITE);
            error4 = true;
        }
    }

    private void checkConfirmPass(JPasswordField confirmpassfld, JLabel conpasserr) {
        String confirmPass = "";
        for (int i = 0; i < conpassword.length; i++) {
            confirmPass = confirmPass + conpassword[i];
        }
        confirmPass = confirmPass.trim();
        if (confirmPass.isEmpty()) {
            conpasserr.setText("Password can not be empty.");
            confirmpassfld.setBackground(Color.RED);
            error5 = false;
        } else if (!pass.equals(confirmPass)) {
            conpasserr.setText("Password does not match.");
            confirmpassfld.setBackground(Color.RED);
            error5 = false;
        } else {
            conpasserr.setText(" ");
            confirmpassfld.setBackground(Color.WHITE);
            error5 = true;
        }
    }

    private void checkDeviceId() {
        deviceid = User.getDeviceId();
        if (deviceid.equals("1")) {
            JOptionPane.showMessageDialog(null, "Device is not supported", "Warning", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    private void checkUserId() {
        RegistrationCheck check = new RegistrationCheck();
        userid = check.getUserId();
        error6 = true;
        if (userid == 0) {
            error6 = false;
        }
    }

    private void verify() {
        if (nointernet == false) {
            if (error1 == true && error2 == true && error3 == true && error4 == true && error5 == true && error6 == true) {
                user = new User(userid, name, username, email, pass, deviceid);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static boolean CheckCode(int code, JTextField codefld, JLabel msgsent, User user) {
        String maincode = code + "";
        if (maincode.equals(codefld.getText())) {
            RegistrationCheck check = new RegistrationCheck();
            boolean flag = check.updateAccounts(user);
            codefld.setBackground(Color.WHITE);
            msgsent.setText(" ");
            if (flag == true) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            codefld.setBackground(Color.RED);
            msgsent.setText("                      CODE DOES NOT MATCH");
        }
        return false;
    }

}
