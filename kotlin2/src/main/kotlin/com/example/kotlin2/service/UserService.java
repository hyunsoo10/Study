package com.example.kotlin2.service;

import com.example.kotlin2.domain.user.Role;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.domain.user.UserRepository;
import com.example.kotlin2.presentation.dto.user.UserSignDto;
import com.example.kotlin2.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {



    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private  BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User getUserBySeq(Long seq) {
        System.out.println("seq = " + seq);
        return userRepository.findById(seq);
    }


    public void signUp(UserSignDto userSignDto) {
        User newUser = new User(
                userSignDto.getUserId(),
                userSignDto.getName(),
                userSignDto.getPassword(),
                userSignDto.getEmail(),
                Role.USER,
                userSignDto.getAge()
        );

        userRepository.save(newUser);
    }
}
