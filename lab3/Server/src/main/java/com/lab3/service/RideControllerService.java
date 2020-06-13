package com.lab3.service;

import com.lab3.converter.BookingConverter;
import com.lab3.converter.RideConverter;
import com.lab3.dto.BookingDTO;
import com.lab3.dto.RideDTO;
import com.lab3.entity.Booking;
import com.lab3.entity.Car;
import com.lab3.entity.RideStatus;
import com.lab3.exception.BookingNotFoundException;
import com.lab3.exception.CarNotFoundException;
import com.lab3.service.data.BookingService;
import com.lab3.service.data.CarService;
import com.lab3.service.data.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RideControllerService {
    private final RideService rideService;
    private final BookingService bookingService;
    private final CarService carService;

    private final RideConverter rideConverter;
    private final BookingConverter bookingConverter;

    @Transactional
    public RideDTO save(RideDTO rideDTO) {
        Optional<Booking> booking = bookingService.findBookingById(rideDTO.getBookingId());
        if (!booking.isPresent()) {
            throw new BookingNotFoundException("Booking with id " + rideDTO.getBookingId() + " not found");
        }
        booking.get().setRideStatus(RideStatus.WAITING);
        Optional<Car> car = carService.findCarById(rideDTO.getCarId());
        if (!car.isPresent()) {
            throw new CarNotFoundException("Car with id " + rideDTO.getCarId() + " not found");
        }

        log.info("save rideDTO {}", rideDTO);
        return rideConverter.convertToDTO(rideService.save(rideConverter.convertToEntity(rideDTO, booking.get(), car.get())));
    }

    public List<BookingDTO> getRideBookingsByCar(Long carId) {
        return bookingConverter.convertToListDTO(rideService.getRideBookingsByCar(carId));
    }
}
