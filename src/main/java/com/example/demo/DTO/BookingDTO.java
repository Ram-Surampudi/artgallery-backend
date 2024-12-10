package com.example.demo.DTO;

import com.example.demo.Models.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO extends Booking {
    private UUID art_id;
    private UUID user_id;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "art_id=" + art_id +
                ", user_id=" + user_id +
                ", id=" + getId() +
                ", date=" + getDate() +
                ", street='" + getStreet() + '\'' +
                ", town='" + getTown() + '\'' +
                ", pincode=" + getPincode() +
                ", doorNo='" + getDoorNo() + '\'' +
                ", state='" + getState() + '\'' +
                ", cost=" + getCost() +
                ", delevied=" + getDelevied() +
                ", deliveryDate=" + getDeliveryDate() +
                ", cancel=" + getCancel() +
                ", art=" + getArt() +
                ", user=" + getUser() +
                '}';
    }
}
