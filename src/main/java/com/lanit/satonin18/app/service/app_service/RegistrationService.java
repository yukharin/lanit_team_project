package com.lanit.satonin18.app.service.app_service;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.Authority;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.entity.enum_type.gener_value4field.Role;
import com.lanit.satonin18.app.objects.input.dto.valid.RegistrationDtoValid;
import com.lanit.satonin18.app.repository.authorization.AuthorityRepository;
import com.lanit.satonin18.app.service.entities_service.authorization.AuthorityService;
import com.lanit.satonin18.app.service.entities_service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("registrationService")
public class RegistrationService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegistrationDtoValid registrationDtoValid) {
        Organization org = organizationService.findById(registrationDtoValid.getOrganizationId());

        //TODO пока такое деление не имеет никакого смысла для программы
        //TODO при смене организации нужно потом поменять его роль
        String role = defineRole(org.isGovernment());

        User user = saveUser(registrationDtoValid, org);
        UserAccount account = saveAccount(registrationDtoValid, user);
        Authority authority = saveAuthority(account, role);
    }

    private User saveUser(RegistrationDtoValid registrationDtoValid, Organization org) {
        User user = new User();
        user.setFirstName(registrationDtoValid.getFirstName());
        user.setLastName(registrationDtoValid.getLastName());
        user.setOrganization(org);

        userService.save(user);
        return user;
    }

    private Authority saveAuthority(UserAccount account, String role) {
        Authority authority = new Authority();
        authority.setUserAccount(account);
        authority.setAuthority(role);

        List<Authority> list = new ArrayList<>();
        list.add(authority);
        account.setAuthorities(list);

        authorityService.save(authority);

        return authority;
    }

    private UserAccount saveAccount(RegistrationDtoValid registrationDtoValid, User user) {
        UserAccount account = new UserAccount();
        account.setId(user.getId());
        account.setUsername(registrationDtoValid.getUsername());
        account.setPassword(
                passwordEncoder.encode(
                        registrationDtoValid.getPassword()
                )
        );
        account.setEnabled(true);
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);

        userAccountService.save(account);

        return account;
    }

    private String defineRole(boolean isGovernment) {
        if (isGovernment) {
            return Role.USER_GOVERNMENT.toString();
        } else {
            return Role.USER_WORKER.toString();
        }
    }
}
