package io.study.security_basic.service;

import io.study.security_basic.Entity.User;
import io.study.security_basic.repository.UserRepository;
import io.study.security_basic.request.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(UserJoinDto user) {
        // 패스워드 인코드
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user.toEntity());
    }

    public User findById(String id) {
        return userRepository.findByUsername(id);
    }
}
