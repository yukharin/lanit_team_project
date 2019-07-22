package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.entities_dao.OrganizationRepository;
import com.lanit.lkz_project.entities.Organization;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public void addOrganization(@NonNull Organization organization) {
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
        return organizationRepository.getOne(id);
    }

    @Transactional
    public List<Organization> organizations() {
        return organizationRepository.findAll();
    }

    @Transactional
    public List<Organization> nonGovernmentOrganizations() {
        return organizationRepository.findAll();
    }

    @PostConstruct
    private void p() {
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
