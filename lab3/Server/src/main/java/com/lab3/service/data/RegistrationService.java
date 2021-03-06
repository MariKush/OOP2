package com.lab3.service.data;

import com.lab3.entity.User;
import com.lab3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    @Transactional
    public User save(User currentUser) {
        log.info("save currentUser: {}", currentUser);
        return userRepository.findByEmail(currentUser.getEmail()).orElseGet(() -> userRepository.save(currentUser));
    }
}
