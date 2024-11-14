package com.ethan.identity_service.service;

import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.request.UserResponse;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.entity.UserEntity;
import com.ethan.identity_service.exception.AppException;
import com.ethan.identity_service.exception.ErrorCode;
import com.ethan.identity_service.mapper.UserMapper;
import com.ethan.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserEntity createUser(UserCreationRequest request) {

        if(userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        UserEntity user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    };

    public UserResponse getUser(String id) {
        return userMapper.toUserReponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserReponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
