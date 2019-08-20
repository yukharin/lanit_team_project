package com.lanit.satonin18.app.service.app_service;

import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.Authority;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.objects.input.dto.valid.RegistrationDtoValid;
import com.lanit.satonin18.app.repository.authorization.AuthorityRepository;
import com.lanit.satonin18.app.service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegistrationDtoValid registrationDtoValid) {
        User user = saveUser(registrationDtoValid);
        UserAccount account = saveAccount(registrationDtoValid, user);
        Authority authority = saveAuthority(account);
    }

    @Transactional
    public Authority saveAuthority(UserAccount account) {
        Authority authority = new Authority();
        authority.setUserAccount(account);
        authority.setAuthority("ROLE_USER"); //defineRole(Organization organization)

        List<Authority> list = new ArrayList<>();
        list.add(authority);
        account.setAuthorities(list);

        authorityRepository.save(authority);

        return authority;
    }

    @Transactional
    public UserAccount saveAccount(RegistrationDtoValid registrationDtoValid, User user) {
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

    @Transactional
    public User saveUser(RegistrationDtoValid registrationDtoValid) {
        User user = new User();
        user.setFirstName(registrationDtoValid.getFirstName());
        user.setLastName(registrationDtoValid.getLastName());
        user.setOrganization(organizationService.findById(registrationDtoValid.getOrganizationId()));

        userService.save(user);
        return user;
    }

//    private Authority defineRole(Organization organization) {
//        if (organization.isGovernment()) {
//            return authorityService.getAuthority(2L);
//        } else {
//            return authorityService.getAuthority(1L);
//        }
//    }
}
