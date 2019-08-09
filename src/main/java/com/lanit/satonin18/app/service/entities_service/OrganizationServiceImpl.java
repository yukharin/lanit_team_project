package com.lanit.satonin18.app.service.entities_service;

//import org.apache.log4j.Logger;
import com.lanit.satonin18.app.repository.OrganizationRepository;
import com.lanit.satonin18.app.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationRepository.delete(organization);
    }

    @Override
    public Organization findById(int id) {
        return organizationRepository.findById(id).get();
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public List<Organization> findByNameIgnoreCaseContaining(String theSearchName) {
        return organizationRepository.findByNameIgnoreCaseContaining(theSearchName);
    }
}