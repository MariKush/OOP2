package com.lab3.service;

import com.lab3.converter.BookingConverter;
import com.lab3.dto.BookingDTO;
import com.lab3.entity.Booking;
import com.lab3.entity.RideStatus;
import com.lab3.entity.User;
import com.lab3.exception.UserNotFoundException;
import com.lab3.service.data.BookingService;
import com.lab3.service.data.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingControllerService {
    private final BookingConverter bookingConverter;
    private final BookingService bookingService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void save(BookingDTO bookingDTO) {
        Optional<User> user = userService.findUserByEmail(bookingDTO.getUserEmail());
        Booking currentBooking = bookingConverter.convertToEntity(bookingDTO);

        if (!user.isPresent()) {
            throw new UserNotFoundException("User with email " + bookingDTO.getUserEmail() + " not found");
        }

        currentBooking.setUser(user.get());

        BookingDTO savedUserDto = bookingConverter.convertToDTO(bookingService.save(currentBooking));
        applicationEventPublisher.publishEvent(savedUserDto);

        log.info("save bookingDTO {}", bookingDTO);
    }

    public List<BookingDTO> findBookingsByUser(String userEmail) {
        return bookingConverter.convertToListDTO(bookingService.findUserBookings(userEmail));
    }

    public List<BookingDTO> findBookingsByStatus(String bookingStatus) {
        return bookingConverter.convertToListDTO(bookingService.findBookingByStatus(RideStatus.valueOf(bookingStatus)));
    }

    public BookingDTO updateBookingStatus(Long bookingId, String status) {
        log.info("update Booking Status to {}", status);
        return bookingConverter.convertToDTO(this.bookingService.updateBooking(bookingId, RideStatus.valueOf(status)));
    }
}
