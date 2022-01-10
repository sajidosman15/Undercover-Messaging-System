package Controller;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailApi {

    private static int code;

    public static int sendMail(String recepient) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String email = "undercovermessage@gmail.com";
            String pass = "August12*";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, pass);
                }
            });

            Message message = prepareMessage(session, email, recepient);
            Transport.send(message);
        } catch (Exception e) {
            return 0;
        }
        return code;
    }

    private static Message prepareMessage(Session session, String email, String recepient) {
        Random ran = new Random();
        code = ran.nextInt(899999) + 100000;
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Account Verification");
            message.setContent("<h1>Your activation code is</h1>\n<h3>" + code + "</h3>", "text/html");
            return message;
        } catch (MessagingException e) {

        }
        return null;
    }
}
