package com.lab3.service.data;

import com.lab3.entity.Booking;
import com.lab3.entity.Ride;
import com.lab3.entity.RideStatus;
import com.lab3.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideService {
    private final RideRepository rideRepository;

    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Booking> getRideBookingsByCar(Long carId) {
        return rideRepository.findRidesByCarIdAndBookingRideStatus(carId, RideStatus.WAITING).stream().map(Ride::getBooking).collect(Collectors.toList());
    }
}
