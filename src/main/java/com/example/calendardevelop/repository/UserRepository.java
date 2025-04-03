package com.example.calendardevelop.repository;

import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.INVALID_ID_PW));
    }



}
