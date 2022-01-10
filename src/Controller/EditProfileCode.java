package Controller;

import Entity.User;
import Database.RegistrationCheck;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EditProfileCode {

    private String name, pass;
    private char password[], conpassword[];
    private boolean error1, error3;
    private User user;

    public EditProfileCode(User user) {
        this.user = user;
    }

    public boolean checkAll(JLabel changeerr, JLabel namelab, JButton editname, JTextField namefld, JLabel nameerr, JLabel passwordlab, JButton editpass, JPasswordField passwordfld, JLabel passerr) {
        checkName(namelab, editname, namefld, nameerr);
        checkPassword(passwordlab, editpass, passwordfld, passerr);

        if ((user.getName().equals(name)) && user.getPassword().equals(pass)) {
            changeerr.setText("YOU HAVE NOT MADE ANY CHANGE");
        } else if (error1 == true && error3 == true) {
            changeerr.setText("");
            return true;
        }
        return false;
    }

    public void checkName(JLabel namelab, JButton editname, JTextField namefld, JLabel nameerr) {
        name = namefld.getText();
        name = name.trim();
        if (name.isEmpty()) {
            nameerr.setText("Name can not be empty.");
            namefld.setBackground(Color.RED);
            error1 = false;
        } else if (name.length() > 30) {
            nameerr.setText("Maximum length is 30.");
            namefld.setBackground(Color.RED);
            error1 = false;
        } else {
            if (name.equals(user.getName())) {
                namelab.setText("  Current Name");
            } else {
                namelab.setText("  Updated Name");
            }
            nameerr.setText(" ");
            editname.setText("CHANGE");
            namefld.setEditable(false);
            namefld.setBackground(new Color(238, 238, 238));
            error1 = true;
        }
    }

    public void checkPassword(JLabel passwordlab, JButton editpass, JPasswordField passwordfld, JLabel passerr) {
        pass = "";
        password = new char[22];
        password = passwordfld.getPassword();
        for (int i = 0; i < password.length; i++) {
            pass = pass + password[i];
        }
        pass = pass.trim();
        if (pass.isEmpty()) {
            passerr.setText("Password can not be empty.");
            passwordfld.setBackground(Color.RED);
            error3 = false;
        } else if (pass.length() < 7) {
            passerr.setText("Password is too short.");
            passwordfld.setBackground(Color.RED);
            error3 = false;
        } else if (pass.length() > 20) {
            passerr.setText("Maximum length is 20.");
            passwordfld.setBackground(Color.RED);
            error3 = false;
        } else {
            if (pass.equals(user.getPassword())) {
                passwordlab.setText("  Current Password");
            } else {
                passwordlab.setText("  Updated Password");
            }
            passerr.setText(" ");
            editpass.setText("CHANGE");
            passwordfld.setEditable(false);
            passwordfld.setBackground(new Color(238, 238, 238));
            error3 = true;
        }
    }

    public boolean modify(JPasswordField confirmpassfld, JLabel conpasserr, String name, String pass) {
        String confirmPass = "";
        conpassword = confirmpassfld.getPassword();
        for (int i = 0; i < conpassword.length; i++) {
            confirmPass = confirmPass + conpassword[i];
        }
        confirmPass = confirmPass.trim();

        if (!user.getPassword().equals(confirmPass)) {
            conpasserr.setText("YOUR PASSWORD IS INCORRECT");
            confirmpassfld.setBackground(Color.RED);
            return false;
        } else {
            conpasserr.setText(" ");
            confirmpassfld.setBackground(Color.WHITE);
            RegistrationCheck check = new RegistrationCheck();
            boolean flag = check.editProfile(user.getUserId(), name, pass);
            if (flag == true) {
                JOptionPane.showMessageDialog(null, "Profile is successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                user.editAccount(name, pass);
            } else {
                JOptionPane.showMessageDialog(null, "No Internet Connection", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;

    }

}
