package com.example.kotlin2.service;

import com.example.kotlin2.domain.user.Role;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.domain.user.UserRepository;
import com.example.kotlin2.presentation.dto.ApiResponse;
import com.example.kotlin2.presentation.dto.user.LoginRequestDto;
import com.example.kotlin2.presentation.dto.user.UserDto;
import com.example.kotlin2.presentation.dto.user.UserResponseDto;
import com.example.kotlin2.presentation.dto.user.UserSignDto;
import com.example.kotlin2.security.jwt.JwtTokenDto;
import com.example.kotlin2.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;




    @Autowired
    public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserResponseDto getUserBySeq(Long seq) {
        User user = userRepository.findBySeq(seq);
        return UserResponseDto.toDto(user);
    }
    public UserResponseDto getUserById(String userId) {
        User user = userRepository.findByUserId(userId);
        return UserResponseDto.toDto(user);
    }


    public ApiResponse<?> signUp(UserSignDto userSignDto) {
        User newUser = new User(
                userSignDto.getUserId(),
                userSignDto.getName(),
                userSignDto.getPassword(),
                userSignDto.getEmail(),
                Role.USER,
                userSignDto.getAge()
        );
        userRepository.save(newUser);
        validateDuplicateUser(newUser);
        UserResponseDto userResponseDto = UserResponseDto.toDto(newUser);
        return new ApiResponse<>(userResponseDto, "회원가입 성공");
    }

    public ApiResponse<?> idDuplicateCheck(String userId) {
        boolean duplicate = userRepository.isExistedById(userId);
        String msg = "사용 가능한 아이디입니다.";
        if(duplicate) {
            msg = "이미 존재하는 아이디입니다.";
        }
        return new ApiResponse<>(null, msg);
    }

    public boolean validatePassword(UserDto userDto) {
        String userId = userDto.getUserId();
        String password = userDto.getPassword();
        User user = userRepository.findByUserId(userId);
        return user.isSamePassword(password);
    }

    private void validateDuplicateUser(User user) {
        System.out.println("user = " + user);
        List<User> users = userRepository.findAll();
        for (User u : users) {
            System.out.println("user = " + u);
        }
        boolean duplicate = userRepository.isExistedById(user.getUserId());
        if(duplicate) throw new IllegalStateException("이미 존재하는 회원입니다");
    }

}
