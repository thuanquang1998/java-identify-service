package com.ethan.identity_service.mapper;

import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.response.UserResponse;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUser(UserCreationRequest request);

    UserResponse toUserReponse(UserEntity user);

    void updateUser(@MappingTarget UserEntity user, UserUpdateRequest request);
}
