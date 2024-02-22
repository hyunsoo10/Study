package com.example.kotlin2.repository;

import com.example.kotlin2.domain.exception.UserNotFoundException;
import com.example.kotlin2.domain.user.Role;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {

    static List<User> userList = new CopyOnWriteArrayList<>();
    static AtomicLong seq = new AtomicLong(1L);

    @PostConstruct
    void initUser() {
        User user1 = User.createUser(seq.getAndAdd(1L),"hscho","조현수", "1234", "hyunsoo@naver.com", Role.ADMIN,30);
        User user2 = User.createUser(seq.getAndAdd(2L),"jyyoo","유재영", "1234", "hyunsoo@naver.com", Role.ADMIN,30);
        User user3 = User.createUser(seq.getAndAdd(3L),"sclee","이선철", "1234", "hyunsoo@naver.com", Role.ADMIN,30);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        System.out.println("UserRepositoryImpl.initUser");
        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }


    @Override
    public void save(User user) {
        User newUser = new User(seq.getAndAdd(1L), user);
        userList.add(newUser);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public User findBySeq(Long seq) {
        return userList
                .stream()
                .filter(user -> user.getSeq().equals(seq))
                .findFirst()
                .orElseThrow(()->new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    @Override
    public User findByUserId(String userId) {
        return userList
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    @Override
    public boolean isExistedById(String userId) {
        return userList
                .stream()
                .anyMatch(user -> user.getUserId().equals(userId));
    }
}
