package ua.nure.antoniuk.util;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final Logger LOGGER = Logger.getLogger(EmailSender.class);
    private final String username = "authobase.summarytask4@gmail.com";
    private final String password = "123qwerty456";
    private Properties props;

    public EmailSender() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    private Session getSession() {
        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void sendMessage(User user, String header, String body) {
        new Thread(() -> {
            try {
                Message message = new MimeMessage(getSession());
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                message.setSubject(header);
                message.setText(body);
                LOGGER.trace(user + header);
                Transport.send(message);
            } catch (MessagingException e) {

                throw new RuntimeException(e);
            }
        }).start();
    }
}
