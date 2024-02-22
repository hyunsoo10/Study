package com.example.kotlin2.domain.user;


import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User findBySeq(Long seq);

    User findByUserId(String userId);

    boolean isExistedById(String userId);

    void save(User user);

}
