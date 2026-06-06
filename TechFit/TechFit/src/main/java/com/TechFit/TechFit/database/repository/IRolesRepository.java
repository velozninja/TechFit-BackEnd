package com.TechFit.TechFit.database.repository;

import com.TechFit.TechFit.database.model.RolesEntity;
import com.TechFit.TechFit.database.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity  findByName(String name);
}
