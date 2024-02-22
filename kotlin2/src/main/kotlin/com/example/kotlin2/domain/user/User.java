package com.example.kotlin2.domain.user;

import lombok.Getter;

@Getter
public class User {

    Long seq;
    String userId;
    String name;
    String password;
    String email;
    Role role;
    int age;

    public Long getSeq() {
        return seq;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public User(String userId, String name, String password, String email, Role role, int age) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.age = age;
    }

    private User(Long seq, String userId, String name, String password, String email, Role role, int age) {
        this.seq = seq;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.age = age;
    }

    public User(long seq, User user) {
        this.seq = seq;
        this.userId = user.getUserId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.age = user.getAge();
    }

    public static User createUser(Long seq, String userId, String name, String password, String email, Role role, int age) {
        return new User(seq, userId, name, password, email, role, age);
    }
}
