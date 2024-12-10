package com.example.demo.Emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String toEmail, String subject, String body) {
        try {
            // Create a MimeMessage object
            MimeMessage message = javaMailSender.createMimeMessage();

            // Use MimeMessageHelper to configure the message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("onlineartgalleryproject@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true); // Set 'true' to indicate HTML content

            System.out.println("ttyyii");
            javaMailSender.send(message);
            System.out.println("HTML email sent to " + toEmail);
        } catch (MessagingException e) {
            System.out.println("failed");
            e.printStackTrace();
            System.out.println(e);
            throw new RuntimeException("Failed to send email");
        }
    }
}
