package com.lanit.lkz_project.service.jpa_entities_service;

import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.repositories.entitity_repositories.OrganizationRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public void addOrganization(@Valid @NonNull Organization organization) {
        organizationRepository.save(organization);
    }

    @Transactional
    public void updateOrganization(@NonNull Organization organization) {
        organizationRepository.save(organization);
    }

    @Transactional
    public void removeOrganization(@NonNull Organization organization) {
        organizationRepository.delete(organization);
    }

    @Transactional
    public void removeOrganization(long id) {
        organizationRepository.deleteById(id);
    }

    @Transactional
    public Organization getOrganization(long id) {
        return organizationRepository.findById(id).get();
    }

    @Transactional
    public List<Organization> organizations() {
        return organizationRepository.findAll();
    }

}
