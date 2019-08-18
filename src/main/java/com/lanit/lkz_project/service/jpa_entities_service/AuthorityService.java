package com.lanit.lkz_project.service.jpa_entities_service;

import com.lanit.lkz_project.entities.jpa_entities.Authority;
import com.lanit.lkz_project.repositories.entitity_repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Transactional
    public Authority getAuthority(Long id) {
        return authorityRepository.findById(id).get();
    }

}
