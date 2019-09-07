package com.lanit.lkz_project.repositories.entitity_repositories;

import com.lanit.lkz_project.entities.jpa_entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
