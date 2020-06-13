package com.lab3.converter;

import com.lab3.dto.RideDTO;
import com.lab3.entity.Booking;
import com.lab3.entity.Car;
import com.lab3.entity.Ride;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RideConverter {
    public RideDTO convertToDTO(Ride ride) {
        RideDTO rideDTO = new RideDTO();
        rideDTO.setId(ride.getId());
        rideDTO.setBookingId(ride.getBooking().getId());
        rideDTO.setCarId(ride.getCar().getId());
        rideDTO.setPrice(ride.getPrice());

        return rideDTO;
    }

    public Ride convertToEntity(RideDTO rideDTO, Booking booking, Car car) {
        Ride ride = new Ride();
        ride.setId(rideDTO.getId());
        ride.setBooking(booking);
        ride.setCar(car);
        ride.setPrice(rideDTO.getPrice());

        return ride;
    }

    public List<RideDTO> convertToListDTO(List<Ride> rides) {
        return rides.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}