package Controller;

import Entity.User;
import Database.CheckDatabase;
import Database.LoginCheck;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginCode {

    private String username;
    private String password;
    private boolean flag;
    private User user;

    public User getUser() {
        return user;
    }

    public boolean checkAndVerify(JTextField unamefld, JPasswordField passfld, JLabel errlab) {
        username = unamefld.getText();
        password = passfld.getText();

        if (password.isEmpty() || username.isEmpty()) {
            if (password.isEmpty() && username.isEmpty()) {
                unamefld.setBackground(Color.RED);
                passfld.setBackground(Color.RED);
                errlab.setVisible(true);
                errlab.setText("                   FIELD CAN NOT BE EMPTY");
                return false;
            } else if (username.isEmpty()) {
                unamefld.setBackground(Color.RED);
                errlab.setVisible(true);
                errlab.setText("              USERNAME CAN NOT BE EMPTY");
                return false;
            } else {
                passfld.setBackground(Color.RED);
                errlab.setVisible(true);
                errlab.setText("              PASSWORD CAN NOT BE EMPTY");
                return false;
            }
        } else {
            flag = Verify(unamefld, passfld, errlab);
        }

        return flag;
    }

    private boolean Verify(JTextField unamefld, JPasswordField passfld, JLabel errlab) {
        errlab.setVisible(false);
        errlab.setText(" USERNAME OR PASSWORD WAS INCORRECT");
        unamefld.setBackground(Color.WHITE);
        passfld.setBackground(Color.WHITE);
        LoginCheck obj = new LoginCheck();
        user = obj.getAccount(username);

        if (user != null) {
            if (password.equals(user.getPassword())) {
                String deviceId = user.getDeviceId();
                errlab.setVisible(false);
                if (deviceId.equals(user.getSavedDeviceId())) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Device Is Locked", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                unamefld.setBackground(Color.RED);
                passfld.setBackground(Color.RED);
                errlab.setVisible(true);
            }
        } else {
            CheckDatabase net = new CheckDatabase();
            boolean check = net.CheckInternet();

            if (check == true) {
                unamefld.setBackground(Color.RED);
                passfld.setBackground(Color.RED);
                errlab.setVisible(true);
            } else {
                if (username.contains("'")) {
                    unamefld.setBackground(Color.RED);
                    passfld.setBackground(Color.RED);
                    errlab.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }

        }
        return false;

    }

}
