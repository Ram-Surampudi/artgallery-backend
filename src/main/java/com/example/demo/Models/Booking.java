package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@EqualsAndHashCode(exclude = {"user", "art"})
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "booking_id", updatable = false, nullable = false)
    private UUID id; // Primary key

    @Column(name = "booking_date")
    private LocalDate date;

    @Column(name = "street")
    private String street;

    @Column(name = "town")
    private String town;

    @Column(name = "pincode")
    private Integer pincode;

    @Column(name = "door_no")
    private String doorNo;

    @Column(name = "state")
    private String state;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "delevied")
    private Boolean delevied;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "cancel")
    private Boolean cancel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;

    @OneToOne
    @JoinColumn(name = "art_id", nullable = false)
    @ToString.Exclude
    private Art art;
}

