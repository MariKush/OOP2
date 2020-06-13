package com.lab3.controller;

import com.lab3.dto.BookingDTO;
import com.lab3.dto.RideDTO;
import com.lab3.service.RideControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RideController {
    private final RideControllerService rideService;

    @PostMapping(value = "/ride")
    public RideDTO saveRide(@Valid @RequestBody RideDTO rideDTO) {
        return rideService.save(rideDTO);
    }

    @GetMapping(value = "/ride/booking/{carId}")
    public List<BookingDTO> getRideBookingsByCar(@PathVariable Long carId) {
        return rideService.getRideBookingsByCar(carId);
    }
}
