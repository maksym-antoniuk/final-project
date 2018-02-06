package ua.nure.antoniuk.util;

import ua.nure.antoniuk.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Util {

    public static Role getRole(String role) {
        switch (role) {
            case Constants.ADMIN_ROLE:
                return Role.ADMIN;
            case Constants.DRIVER_ROLE:
                return Role.DRIVER;
             default:
                return Role.MANAGER;
        }
    }

    public static Calendar getCalendarByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static boolean isMatch(String regex, String field) {
        return Pattern.compile(regex).matcher(field).find();
    }

    public static int typeRole(HttpServletRequest request) {
        if (Role.MANAGER.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.MANAGER;
        }
        if (Role.DRIVER.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.DRIVER;
        }
        if (Role.ADMIN.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.ADMIN;
        }
        return 0;
    }

    private Util() {
        throw new UnsupportedOperationException("You can`t create an instance of this class");
    }

    public static Bodywork getBodywork(String bodywork) {
        switch (bodywork) {
            case Constants.TANK_TYPE_BODYWORK:
                return Bodywork.TANK;
            case Constants.BULK_TYPE_BODYWORK:
                return Bodywork.BULK;
            case Constants.CAR_TYPE_BODYWORK:
                return Bodywork.CAR;
            case Constants.ANIMAL_TYPE_BODYWORK:
                return Bodywork.ANIMAL;
            default:
                return Bodywork.CONTAINER;
        }
    }

    public static void sendPassword(User user) {
        new Thread(() -> {
            final String username = "authobase.summarytask4@gmail.com";
            final String password = "123qwerty456";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                message.setSubject("Registration in PERENOSCHIK company");
                message.setText("Hi "+user.getName() + " ,"+System.lineSeparator()+
                        "Welcome to our company " + System.lineSeparator() +
                        "Now you can authorize in our system by your email and password"+System.lineSeparator()+
                        user.getPassword());

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void sendCancel(User user) {
        new Thread(() -> {
            final String username = "authobase.summarytask4@gmail.com";
            final String password = "123qwerty456";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                message.setSubject("PERENOSCHIK company");
                message.setText("Hi "+user.getName() + " ,"+System.lineSeparator()+
                        "Unfortunately you don't suit for us, GOOD LUCK");

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static String getAcceptUserRegistration(User user) {
        return "Hi "+user.getName() + " ,"+System.lineSeparator()+
                "Welcome to our company " + System.lineSeparator() +
                "Now you can authorize in our system by your email and password"+System.lineSeparator()+
                user.getPassword();
    }

    public static String getCancelUserRegistration(User user) {
        return "Hi "+user.getName() + " ,"+System.lineSeparator()+
                "Unfortunately you don't suit for us, GOOD LUCK";
    }

    public static String acceptToJourney() {
        return "Hi, you has been accepted to journey, please START IT NOW";
    }

    public static String getHeaderEmail() {
        return "PERENOSCHIK company";
    }
}
