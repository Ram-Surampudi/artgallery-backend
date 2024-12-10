package com.example.demo.Handlers.CommadHandlers.User;

import com.example.demo.Emails.EmailService;
import com.example.demo.Handlers.CommadHandlers.CommandHandler;
import com.example.demo.Models.Art;
import com.example.demo.Models.Booking;
import com.example.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SentEmail  {

    @Autowired
    private EmailService emailService;

    public void sendEmailOrderCanceled(User user, Art art, Booking booking){
        emailService.sendEmail(user.getEmail(), "Your Art Booking Has Been Cancelled", String.format("""
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Cancellation</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }
            .email-container {
                max-width: 600px;
                margin: 20px auto;
                padding: 20px;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .header {
                text-align: center;
                padding: 10px 0;
                background-color: #f44336;
                color: #ffffff;
                border-radius: 8px 8px 0 0;
            }
            .header h1 {
                margin: 0;
                font-size: 24px;
            }
            .content {
                padding: 20px;
                color: #333333;
            }
            .content h2 {
                color: #f44336;
                margin-bottom: 10px;
            }
            .button {
                display: inline-block;
                margin: 20px 0;
                padding: 10px 20px;
                background-color: #f44336;
                color: #ffffff;
                text-decoration: none;
                border-radius: 4px;
                font-weight: bold;
            }
            .footer {
                text-align: center;
                padding: 10px;
                color: #777777;
                font-size: 12px;
                background-color: #f1f1f1;
                border-radius: 0 0 8px 8px;
            }
            .art-image {
                margin : auto;
                max-width: 400px;
                height: auto;
                border-radius: 8px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="email-container">
            <div class="header">
                <h1>Order Cancelled</h1>
            </div>
            <div class="content">
                <h2>Hi, %s,</h2>
                <p>you are requested for Cancellation of art piece titled <strong>%s</strong> has been cancelled.</p>
                <p><strong>Your Art Piece:</strong></p>
                <img src="%s" alt="Art Image" class="art-image">
                <p>Here are the details of your cancelled booking:</p>
                <ul>
                    <li><strong>Booking ID:</strong> %s</li>
                    <li><strong>Booked Date:</strong> %s</li>
                    <li><strong>Cancellation Date:</strong> %s</li>
                    <li><strong>Cost:</strong> $%s</li>
                </ul>
                <p>Your art piece was scheduled for delivery to:</p>
                <p>
                    %s, %s,<br>
                    %s, %s, %s
                </p>
                <p>If you have any questions or need further assistance, feel free to <a href="mailto:support@yourwebsite.com">contact us</a>.</p>
                <p>We apologize for any inconvenience caused.</p>
            </div>
            <div class="footer">
                <p>&copy; 2024 Your Art Gallery. All Rights Reserved.</p>
            </div>
        </div>
    </body>
    </html>
    """,
                        user.getUsername(),
                        art.getName(),
                        art.getUrl(),
                        booking.getId(),
                        booking.getDate(),
                        LocalDate.now(),
                        booking.getCost(),
                        booking.getDoorNo(),
                        booking.getStreet(),
                        booking.getTown(),
                        booking.getState(),
                        booking.getPincode()
                )
        );

    }

    public void sendEmailOrderPlaced(User user, Art art, Booking booking){
        emailService.sendEmail(user.getEmail(), "Successfully Placed Order",String.format("""
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking Confirmation</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }
            .email-container {
                max-width: 600px;
                margin: 20px auto;
                padding: 20px;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .header {
                text-align: center;
                padding: 10px 0;
                background-color: #4caf50;
                color: #ffffff;
                border-radius: 8px 8px 0 0;
            }
            .header h1 {
                margin: 0;
                font-size: 24px;
            }
            .content {
                padding: 20px;
                color: #333333;
            }
            .content h2 {
                color: #4caf50;
                margin-bottom: 10px;
            }
            .button {
                display: inline-block;
                margin: 20px 0;
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                text-decoration: none;
                border-radius: 4px;
                font-weight: bold;
            }
            .footer {
                text-align: center;
                padding: 10px;
                color: #777777;
                font-size: 12px;
                background-color: #f1f1f1;
                border-radius: 0 0 8px 8px;
            }
            .art-image {
                margin : auto;
                max-width: 400px;
                height: auto;
                border-radius: 8px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="email-container">
            <div class="header">
                <h1>Order Placed</h1>
            </div>
            <div class="content">
                <h2>Hi, %s!</h2>
                <p>Thank you for booking the art piece titled <strong>%s</strong>.</p>
                <p><strong>Your Art Piece:</strong></p>
                <img src="%s" alt="Art Image" class="art-image">
                <p>Here are your booking details:</p>
                <ul>
                    <li><strong>Booking ID:</strong> %s</li>
                    <li><strong>Booked Date:</strong> %s</li>
                    <li><strong>Delivery Date:</strong> %s</li>
                    <li><strong>Cost:</strong> $%s</li>
                </ul>
                <p>Your art will be delivered to:</p>
                <p>
                    %s, %s,<br>
                    %s, %s, %s
                </p>
                <p>If you have any questions, feel free to <a href="mailto:support@yourwebsite.com">contact us</a>.</p>
            </div>
            <div class="footer">
                <p>&copy; 2024 Your Art Gallery. All Rights Reserved.</p>
            </div>
        </div>
    </body>
    </html>
    """,
                        user.getUsername(),
                        art.getName(),
                        art.getUrl(),
                        booking.getId(),
                        booking.getDate(),
                        booking.getDeliveryDate(),
                        booking.getCost(),
                        booking.getDoorNo(),
                        booking.getStreet(),
                        booking.getTown(),
                        booking.getState(),
                        booking.getPincode(),
                        booking.getId()
                )

        );
    }
}
