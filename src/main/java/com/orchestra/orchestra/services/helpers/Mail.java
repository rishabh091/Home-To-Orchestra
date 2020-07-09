package com.orchestra.orchestra.services.helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class Mail extends Thread{

    private String user_email;
    private String subject;
    private String content;

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void run() {
        try {
            send();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //please create a system variable of email and password
                return new PasswordAuthentication(System.getProperty("email"), System.getProperty("password"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(System.getProperty("email"), false));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user_email));
        message.setSubject(subject);
        message.setContent(content, "text/html");
        message.setSentDate(new Date());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}
