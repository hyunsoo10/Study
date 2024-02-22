package com.example.kotlin.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

    Long seq;
    String name;
    int age = 99;

    public Long getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(Long seq, String name, int age) {
        this.seq = seq;
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
