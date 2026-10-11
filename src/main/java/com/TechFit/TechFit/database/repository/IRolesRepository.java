package com.TechFit.TechFit.database.repository;

import com.TechFit.TechFit.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByName(String name);
}
