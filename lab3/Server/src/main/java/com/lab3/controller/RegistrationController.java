package com.lab3.controller;

import com.lab3.dto.UserDTO;
import com.lab3.service.RegistrationControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationControllerService registrationControllerService;

    @PostMapping(value = "/registration")
    public UserDTO registration(@Valid @RequestBody UserDTO userDTO) {
        return registrationControllerService.save(userDTO);
    }
}
