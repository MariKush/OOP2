package com.lab3.service;

import com.lab3.converter.UserConverter;
import com.lab3.dto.UserDTO;
import com.lab3.entity.User;
import com.lab3.service.data.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final UserConverter userConverter;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserDTO save(UserDTO userDTO) {
        User currentUser = userConverter.convertToEntity(userDTO);
        UserDTO savedUserDto = userConverter.convertToDto(registrationService.save(currentUser));
        applicationEventPublisher.publishEvent(savedUserDto);
        log.info("save userDTO {}", userDTO);
        return savedUserDto;
    }
}
