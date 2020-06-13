package com.lab3.service.data;

import com.lab3.entity.User;
import com.lab3.exception.UserNotFound;
import com.lab3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User update(User currentUser) {
        Optional<User> oldUser = userRepository.findByEmail(currentUser.getEmail());
        if (!oldUser.isPresent()) {
            throw new UserNotFound("User with email " + currentUser.getEmail() + " not found");
        }
        User user = oldUser.get();
        user.setCar(currentUser.getCar());
        log.info("update currentUser {}", currentUser);
        return user;
    }
}
