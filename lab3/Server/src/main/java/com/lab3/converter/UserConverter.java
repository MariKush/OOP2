package com.lab3.converter;

import com.lab3.dto.UserDTO;
import com.lab3.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final CarConverter carConverter;

    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        if (user.getCar() != null) {
            userDTO.setCar(carConverter.convertToDTO(user.getCar()));
        } else {
            userDTO.setCar(null);
        }

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        if (userDTO.getCar() != null) {
            user.setCar(carConverter.convertToEntity(userDTO.getCar()));
        } else {
            user.setCar(null);
        }
        return user;
    }
}
