package com.lab3.controller;

import com.lab3.dto.CarDTO;
import com.lab3.service.CarControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    private final CarControllerService carService;

    @GetMapping(value = "/car/booking/{bookingId}")
    public List<CarDTO> getCarsByBooking(@Valid @PathVariable Long bookingId) {
        return carService.findCarByBookingId(bookingId);
    }
}
