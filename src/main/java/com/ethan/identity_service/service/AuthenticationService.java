package com.ethan.identity_service.service;

import com.ethan.identity_service.dto.request.AuthenticationRequest;
import com.ethan.identity_service.dto.response.AuthenticationResponse;
import com.ethan.identity_service.exception.AppException;
import com.ethan.identity_service.exception.ErrorCode;
import com.ethan.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    public boolean authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    };

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        var user = userRepository.findByUserName(request.getUserName())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//        if(!authenticated){
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//    };
}
