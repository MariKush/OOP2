package com.lab3.service.data;

import com.lab3.entity.Booking;
import com.lab3.entity.RideStatus;
import com.lab3.exception.BookingNotFoundException;
import com.lab3.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService  bookingService;

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private Booking booking;

    private final RideStatus rideStatus=RideStatus.WAITING;

    @Test
    public void updateBookingTestCorrectly(){
        when(bookingRepository.findById(any())).thenReturn(Optional.of(booking));

        assertEquals(bookingService.updateBooking(0L, rideStatus), booking);

        verify(booking).setRideStatus(rideStatus);
    }

    @Test
    public void updateBookingTestException(){
        when(bookingRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, ()->bookingService.updateBooking(0L, rideStatus));

        verify(booking, never()).setRideStatus(rideStatus);
    }

}