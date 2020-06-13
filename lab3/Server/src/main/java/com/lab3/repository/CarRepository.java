package com.lab3.repository;

import com.lab3.entity.Car;
import com.lab3.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByCarClassGreaterThanEqualAndSeatsNumberGreaterThanEqualAndServiceable(CarClass carClass, Integer seats, Boolean serviceable);

    Optional<Car> findCarById(Long id);
}
