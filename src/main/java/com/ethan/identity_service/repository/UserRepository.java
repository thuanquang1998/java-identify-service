package com.ethan.identity_service.repository;

import com.ethan.identity_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserName(String userName);
    Optional<UserEntity> findByUserName(String username);
}
