package com.lab3.service;

import com.lab3.converter.CarConverter;
import com.lab3.converter.UserConverter;
import com.lab3.dto.UserDTO;
import com.lab3.entity.Car;
import com.lab3.service.data.CarService;
import com.lab3.service.data.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserControllerService {
    private final UserService userService;
    private final CarService carService;

    private final UserConverter userConverter;
    private final CarConverter carConverter;

    public UserDTO update(UserDTO userDTO) {
        Car car = carService.save(carConverter.convertToEntity(userDTO.getCar()));
        userDTO.setCar(carConverter.convertToDTO(car));

        log.info("update userDTO {}", userDTO);

        return userConverter.convertToDto(userService.update(userConverter.convertToEntity(userDTO)));
    }
}
