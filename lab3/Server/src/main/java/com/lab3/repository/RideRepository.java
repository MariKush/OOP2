package com.lab3.repository;

import com.lab3.entity.Ride;
import com.lab3.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findRidesByCarIdAndBookingRideStatus(Long carId, RideStatus rideStatus);
}
