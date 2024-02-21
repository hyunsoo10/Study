package com.example.kotlin.repository;


import com.example.kotlin.domain.User;

public interface UserRepository {

    User findById(Long seq);
}
