package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.jpa_entities.Authority;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.jpa_entities_service.AuthorityService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationPageService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User registerUser(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        Organization organization = organizationService.getOrganization(user.getOrganization().getId());
        Set<Authority> authorities = new HashSet<>();
        authorities.add(defineRole(organization));
        user.setAuthorities(authorities);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return user;
    }

    private Authority defineRole(Organization organization) {
        if (organization.isGovernment()) {
            return authorityService.getAuthority(2L);
        } else {
            return authorityService.getAuthority(1L);
        }
    }
}


