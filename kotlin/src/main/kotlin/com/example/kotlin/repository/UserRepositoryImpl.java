package com.example.kotlin.repository;

import com.example.kotlin.domain.User;
import com.example.kotlin.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class UserRepositoryImpl implements UserRepository {

    static List<User> userList = new CopyOnWriteArrayList<>();

    @PostConstruct
    void initUser() {
        User user1 = new User(1L,"현수", 30);
        User user2 = new User(2L,"재영", 29);
        User user3 = new User(3L,"선철", 21);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        System.out.println("UserRepositoryImpl.initUser");
        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }


    @Override
    public User findById(Long seq) {
        return userList.stream()
                .filter(user -> user.getSeq().equals(seq))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
