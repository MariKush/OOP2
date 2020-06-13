package com.lab3.service.data;

import com.lab3.entity.Booking;
import com.lab3.entity.Car;
import com.lab3.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findCarByBooking(Booking booking) {
        return carRepository.findCarsByCarClassGreaterThanEqualAndSeatsNumberGreaterThanEqualAndServiceable(booking.getMinClass(), booking.getMinSeats(), true);
    }

    public Optional<Car> findCarById(Long carId){
        return carRepository.findCarById(carId);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

}
