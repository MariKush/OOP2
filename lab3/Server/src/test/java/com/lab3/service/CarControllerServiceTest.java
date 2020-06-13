package com.lab3.service;

import com.lab3.converter.CarConverter;
import com.lab3.dto.CarDTO;
import com.lab3.entity.Booking;
import com.lab3.exception.BookingNotFoundException;
import com.lab3.service.data.BookingService;
import com.lab3.service.data.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerServiceTest {

    @InjectMocks
    private CarControllerService carControllerService;

    @Mock
    private CarService carService;
    @Mock
    private BookingService bookingService;
    @Mock
    private CarConverter carConverter;
    @Mock
    private Booking booking;

    @Test
    public void saveBookingTestCorrect(){
        Optional<Booking> bookingOptional = Optional.of(booking);
        List<CarDTO> carDTOS = new ArrayList<>();

        when(bookingService.findBookingById(any())).thenReturn(bookingOptional);
        when(carConverter.convertToListDTO(any())).thenReturn(carDTOS);

        assertEquals(carControllerService.findCarByBookingId(0L),carDTOS);
    }

    @Test
    public void saveBookingTestException(){
        when(bookingService.findBookingById(any())).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, ()->carControllerService.findCarByBookingId(0L));

        verify(carService, never()).findCarByBooking(any());
    }


}