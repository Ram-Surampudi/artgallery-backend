package com.example.demo.DTO;

import com.example.demo.Models.Art;
import com.example.demo.Models.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtDTO extends Art {
    private boolean presetInCart;
}
