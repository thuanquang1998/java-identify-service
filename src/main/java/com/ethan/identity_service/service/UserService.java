package com.ethan.identity_service.service;

import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.entity.UserEntity;
import com.ethan.identity_service.exception.AppException;
import com.ethan.identity_service.exception.ErrorCode;
import com.ethan.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserCreationRequest request) {
        UserEntity user = new UserEntity();

        if(userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    };

    public UserEntity getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity updateUser(String id, UserUpdateRequest request) {
        UserEntity user = getUser(id);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        UserEntity user = getUser(id);
        userRepository.delete(user);
    }
}
