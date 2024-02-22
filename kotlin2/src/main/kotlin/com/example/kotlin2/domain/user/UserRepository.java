package com.example.kotlin2.domain.user;


public interface UserRepository {

    User findById(Long seq);

    void save(User user);
}
