package com.example.demo.Controllers;

import com.example.demo.DTO.ArtDTO;
import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.CartDTO;
import com.example.demo.Emails.EmailService;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Exceptions.SimpleResponse;
import com.example.demo.Exceptions.User.UsernameNotFound;
import com.example.demo.Handlers.CommadHandlers.User.SentEmail;
import com.example.demo.Models.*;
import com.example.demo.Repositories.ArtRepository;
import com.example.demo.Repositories.BookingRepository;
import com.example.demo.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired private SentEmail sentEmail;
    @Autowired private UserRepository userRepository;
    @Autowired private ArtRepository artRepository;
    @Autowired private BookingRepository bookingRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<Booking>> getAllBookedIems(@PathVariable UUID id){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new CustomExceptions(HttpStatus.BAD_REQUEST, new SimpleResponse("user found"));
        System.out.println(user.get().getBookings());
        return ResponseEntity.ok(user.get().getBookings());
    }


    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addToBooking(@RequestBody BookingDTO bookingDTO) {
        System.out.println(bookingDTO);

        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser_id());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFound();
        }

        User user = userOptional.get();

        Optional<Art> artOptional = artRepository.findById(bookingDTO.getArt_id());
        if (artOptional.isEmpty()) {
            throw new CustomExceptions(HttpStatus.BAD_REQUEST, new SimpleResponse("Art not available"));
        }
        Art art = artOptional.get();
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID()); // Generate unique ID
        booking.setDate(LocalDate.now());
        booking.setStreet(bookingDTO.getStreet());
        booking.setTown(bookingDTO.getTown());
        booking.setPincode(bookingDTO.getPincode());
        booking.setDoorNo(bookingDTO.getDoorNo());
        booking.setState(bookingDTO.getState());
        booking.setCost(art.getCost());
        booking.setDelevied(false);
        booking.setDeliveryDate(LocalDate.now().plusDays(new Random().nextInt(5) + 1)); // Random delivery date within 5 days
        booking.setCancel(false);
        booking.setUser(user);
        booking.setArt(art);

        art.setSoldOutArt(true);
        artRepository.save(art);
        sentEmail.sendEmailOrderPlaced(user,art,booking);
        bookingRepository.save(booking);

        return ResponseEntity.ok("Booking added successfully!");
    }


    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> deleteBookingItem(@PathVariable UUID id) {
        System.out.println("log the data");
        Booking booking = bookingRepository.findById(id).get();
        Art art = booking.getArt();
        art.setSoldOutArt(false);
        artRepository.save(art);
        sentEmail.sendEmailOrderCanceled(booking.getUser(),art,booking);
        bookingRepository.deleteById(id);
        return ResponseEntity.ok("SucessFully deleted");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Booking>> getAllBooking(){
        return ResponseEntity.ok(bookingRepository.findAll());
    }

}
