package com.lanit.lkz_project.repositories.entitity_repositories;

import com.lanit.lkz_project.entities.jpa_entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
