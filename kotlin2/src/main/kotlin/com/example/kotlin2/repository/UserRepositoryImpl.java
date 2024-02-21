package com.example.kotlin2.repository;

import com.example.kotlin2.domain.exception.UserNotFoundException;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.domain.user.UserRepository;
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
                .orElseThrow(()->new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
    }
}
