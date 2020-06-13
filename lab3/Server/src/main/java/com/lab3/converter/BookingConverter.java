package com.lab3.converter;

import com.lab3.dto.BookingDTO;
import com.lab3.entity.Booking;
import com.lab3.entity.CarClass;
import com.lab3.entity.RideStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingConverter {
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDepart(booking.getDepart());
        bookingDTO.setDestination(booking.getDestination());
        bookingDTO.setMinClass(booking.getMinClass().toString());
        bookingDTO.setMinSeats(booking.getMinSeats());
        bookingDTO.setStatus(booking.getRideStatus().toString());

        return bookingDTO;
    }

    public Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setDepart(bookingDTO.getDepart());
        booking.setDestination(bookingDTO.getDestination());
        booking.setMinClass(CarClass.valueOf(bookingDTO.getMinClass()));
        booking.setMinSeats(bookingDTO.getMinSeats());
        booking.setRideStatus(RideStatus.valueOf(bookingDTO.getStatus()));

        return booking;
    }

    public List<BookingDTO> convertToListDTO(List<Booking> bookings) {
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
