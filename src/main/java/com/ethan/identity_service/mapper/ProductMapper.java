package com.ethan.identity_service.mapper;

import com.ethan.identity_service.dto.request.UserCreationRequest;
import com.ethan.identity_service.dto.request.UserUpdateRequest;
import com.ethan.identity_service.dto.request.product.ProductCreateRequest;
import com.ethan.identity_service.dto.response.UserResponse;
import com.ethan.identity_service.entity.ProductEntity;
import com.ethan.identity_service.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toProduct(ProductCreateRequest request);
}
