package com.example.calendardevelop.service;

import com.example.calendardevelop.config.PasswordEncoder;
import com.example.calendardevelop.dto.users.UserGetResponseDto;
import com.example.calendardevelop.dto.users.UserPostResponseDto;
import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import com.example.calendardevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserPostResponseDto signUp(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, email, encodedPassword);
        User savedUser = userRepository.save(user);
        return new UserPostResponseDto(savedUser.getUsername(), savedUser.getEmail());
    }

    public List<UserGetResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserGetResponseDto::toDto).toList();
    }

    public UserGetResponseDto findById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        return new UserGetResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailOrElseThrow(email);
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        String encodedPassword = passwordEncoder.encode(newPassword);

        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_ID_PW);
        }

//        if(!findUser.getPassword().equals(oldPassword)) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }

        findUser.updatePassword(newPassword);
    }

    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }

    public User login(String email, String password) {

        User user = findByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_ID_PW);
        }

        return user;
    }
}
