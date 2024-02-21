package com.example.kotlin.service;

import com.example.kotlin.domain.User;
import com.example.kotlin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    private UserRepository userRepository;

//    @Autowired
//    public UserService() {
//        this.userRepository = userRepository;
//    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserBySeq(Long seq) {
        System.out.println("seq = " + seq);
        return userRepository.findById(seq);
    }
}
