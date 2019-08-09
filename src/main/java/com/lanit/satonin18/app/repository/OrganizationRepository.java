package com.lanit.satonin18.app.repository;

import com.lanit.satonin18.app.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Integer>
{
    public List<Organization> findByNameIgnoreCaseContaining(String name);
}
