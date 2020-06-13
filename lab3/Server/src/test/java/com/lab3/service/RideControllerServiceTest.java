package com.lab3.service;

import com.lab3.converter.BookingConverter;
import com.lab3.converter.RideConverter;
import com.lab3.dto.RideDTO;
import com.lab3.entity.Booking;
import com.lab3.entity.Car;
import com.lab3.entity.RideStatus;
import com.lab3.exception.BookingNotFoundException;
import com.lab3.exception.CarNotFoundException;
import com.lab3.service.data.BookingService;
import com.lab3.service.data.CarService;
import com.lab3.service.data.RideService;
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
public class RideControllerServiceTest {

    @InjectMocks
    private RideControllerService rideControllerService;

    @Mock
    private RideService rideService;
    @Mock
    private BookingService bookingService;
    @Mock
    private CarService carService;
    @Mock
    private RideConverter rideConverter;
    @Mock
    private BookingConverter bookingConverter;
    @Mock
    private Booking booking;
    @Mock
    private Car car;
    @Mock
    private RideDTO rideDTO;


    @Test
    public void saveTestCorrect(){
        when(bookingService.findBookingById(any())).thenReturn(Optional.of(booking));
        when(carService.findCarById(any())).thenReturn(Optional.of(car));
        when(rideConverter.convertToDTO(any())).thenReturn(rideDTO);

        assertEquals(rideControllerService.save(rideDTO), rideDTO);

        verify(booking).setRideStatus(RideStatus.WAITING);
        verify(rideService).save(any());

    }


    @Test
    public void saveTestThrowsBookingNotFoundException(){
        when(bookingService.findBookingById(any())).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class,()->rideControllerService.save(rideDTO));

        verify(booking, never()).setRideStatus(any());
        verify(rideService, never()).save(any());

    }

    @Test
    public void saveTestThrowsCarNotFoundException(){
        when(bookingService.findBookingById(any())).thenReturn(Optional.of(booking));
        when(carService.findCarById(any())).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, ()->rideControllerService.save(rideDTO));

        verify(booking).setRideStatus(RideStatus.WAITING);
        verify(rideService, never()).save(any());

    }





}