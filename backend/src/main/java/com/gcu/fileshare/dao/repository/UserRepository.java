package com.gcu.fileshare.dao.repository;

import com.gcu.fileshare.dao.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserEntity findByUsername(String username);

    UserEntity findByEmailAddress(String emailAddress);
}