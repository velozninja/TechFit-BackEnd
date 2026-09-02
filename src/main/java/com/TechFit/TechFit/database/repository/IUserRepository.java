package com.TechFit.TechFit.database.repository;

import com.TechFit.TechFit.database.model.RolesEntity;
import com.TechFit.TechFit.database.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByRolesAndEmail(RolesEntity role, String email);
    Optional<UserEntity> findBysharableTag(String tag);
    List<UserEntity> findByPersonal_sharableTag(String tag);
}
