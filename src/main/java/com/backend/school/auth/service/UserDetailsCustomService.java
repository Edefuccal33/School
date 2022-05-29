package com.backend.school.auth.service;

import com.backend.school.auth.dto.UserDTO;
import com.backend.school.auth.entity.UserEntity;
import com.backend.school.auth.exception.UsernameExistsException;
import com.backend.school.auth.repository.UserRepository;
// import com.backend.school.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // EmailServiceImpl emailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }

        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList())
                .build();
        //User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }
    public boolean verifyUsernameExists(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            throw new UsernameExistsException("Username already exists");
        }
        return userEntity == null;
    }

    public boolean save(UserDTO userDTO) {
        if (verifyUsernameExists(userDTO.getUsername()));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity = userRepository.save(userEntity);
        // if (userEntity != null) {
        //     emailService.sendWelcomeEmailTo(userEntity.getUsername());
        // }
        return userEntity != null;
    }
}
