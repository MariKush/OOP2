package com.lab3.service;

import com.lab3.converter.CarConverter;
import com.lab3.dto.CarDTO;
import com.lab3.entity.Booking;
import com.lab3.exception.BookingNotFoundException;
import com.lab3.service.data.BookingService;
import com.lab3.service.data.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarControllerService {
    private final CarService carService;
    private final BookingService bookingService;

    private final CarConverter carConverter;

    public List<CarDTO> findCarByBookingId(Long bookingId) {
        Optional<Booking> booking = bookingService.findBookingById(bookingId);
        if (!booking.isPresent()) {
            throw new BookingNotFoundException("Booking with id " + bookingId + " not found");
        }
        return carConverter.convertToListDTO(carService.findCarByBooking(booking.get()));
    }

}
