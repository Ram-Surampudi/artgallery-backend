package com.example.demo.Handlers.CommadHandlers.User;

import com.example.demo.Emails.EmailService;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Exceptions.SimpleResponse;
import com.example.demo.Exceptions.User.EmailExists;
import com.example.demo.Exceptions.User.IncorrectOTP;
import com.example.demo.Handlers.CommadHandlers.CommandHandler;
import com.example.demo.Models.User;
import com.example.demo.Models.Verification;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerifyUser implements CommandHandler<Verification, SimpleResponse> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<SimpleResponse> execute(Verification verification) {

        User user1 = userRepository.findByEmail(verification.getEmail());

        if (user1 != null && !user1.getId().equals(verification.getId()))
            throw new EmailExists();

        User user = userRepository.findById(verification.getId()).get();

        if (!verification.getOtp().isEmpty()){
            if (user.getOtp().toString().equals(verification.getOtp().toString())) {
                System.out.println("user =" + user.getOtp());
                System.out.println("verfi=" + verification.getOtp());
                user.setVerified(true);
                userRepository.save(user);
                return ResponseEntity.ok(new SimpleResponse("verified"));
            }
            throw new IncorrectOTP();
    }

//        user.setEmail(verification.getEmail());

        try {
            String otp = generateOtp();
            user.setEmail(verification.getEmail());
            user.setOtp(otp);
            System.out.println("7");
            userRepository.save(user);
            System.out.println("0");
            sendEmail(verification.getEmail(), otp);
            System.out.println("1");
            return ResponseEntity.ok(new SimpleResponse("otp sent"));
        }
        catch (Exception e ){
            System.out.println("10");
            throw new CustomExceptions(HttpStatus.GATEWAY_TIMEOUT, new SimpleResponse("unstable wifi connection"));
        }
    }

    private String generateOtp(){
        Random random = new Random();
        int otpvalue = random.nextInt(9000000) + 1000000;
        return  String.valueOf(otpvalue);
    }

    private void sendEmail(String email , String otp){
        String subject = "OTP VERIFICATION";
        String body = String.format("<html><body><p>THE VERFICATION OTP IS :<strong>%s</strong></><body></html>", otp);
        System.out.println("working with email");
        emailService.sendEmail(email, subject, body);
        System.out.println("completed");
    }
}
