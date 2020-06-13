package com.lab3.repository;

import com.lab3.entity.Booking;
import com.lab3.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserEmail(String email);

    List<Booking> findByRideStatus(RideStatus status);

    Optional<Booking> findById(Long bookingId);
}
