package com.example.kotlin2.service.security;

import com.example.kotlin2.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@RequiredArgsConstructor
public class GameUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public GameUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        com.example.kotlin2.domain.user.User user = userRepository.findByUserId(userId);
        return createUserDetails(user);
    }

    public UserDetails createUserDetails(com.example.kotlin2.domain.user.User user) {
        return User.builder()
            .username(user.getUserId())
            .password(user.getPassword())
            .roles(user.getRole().toString())
            .build();
    }
}
