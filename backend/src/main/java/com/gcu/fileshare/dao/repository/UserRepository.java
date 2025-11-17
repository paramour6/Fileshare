package com.gcu.fileshare.dao.repository;

import com.gcu.fileshare.dao.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * User repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmailAddress(String emailAddress);
}