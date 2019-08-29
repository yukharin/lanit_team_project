//package com.lanit.satonin18.app.service.app_service;
//
//import com.lanit.satonin18.app.entity.Organization;
//import com.lanit.satonin18.app.entity.User;
//import com.lanit.satonin18.app.entity.authorization.Authority;
//import com.lanit.satonin18.app.entity.authorization.UserAccount;
//import com.lanit.satonin18.app.entity.enum_type.gener_value4field.Role;
//import com.lanit.satonin18.app.objects.input.dto.valid.RegistrationDto;
//import com.lanit.satonin18.app.service.entities_service.authorization.AuthorityService;
//import com.lanit.satonin18.app.service.entities_service.authorization.UserAccountService;
//import com.lanit.satonin18.app.service.entities_service.ActionService;
//import com.lanit.satonin18.app.service.entities_service.NotificationService;
//import com.lanit.satonin18.app.service.entities_service.OrganizationService;
//import com.lanit.satonin18.app.service.entities_service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("registrationService")
//public class RegistrationService {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserAccountService userAccountService;
//    @Autowired
//    private NotificationService notificationService;
//    @Autowired
//    private ActionService actionService;
//    @Autowired
//    private OrganizationService organizationService;
//
//    @Autowired
//    private AuthorityService authorityService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void register(RegistrationDto registrationDto) {
//        Organization org = organizationService.findById(registrationDto.getOrganizationId());
//
//        //TODO пока такое деление не имеет никакого смысла для программы
//        //TODO при смене организации нужно потом поменять его роль
//        String role = defineRole(org.isGovernment());
//
//        User user = saveUser(registrationDto, org);
//        UserAccount account = saveAccount(registrationDto, user);
//        Authority authority = saveAuthority(account, role);
//    }
//
//    private User saveUser(RegistrationDto registrationDto, Organization org) {
//        User user = new User();
//        //can be persist()
//        user.setFirstName(registrationDto.getFirstName());
//        user.setLastName(registrationDto.getLastName());
//        user.setOrganization(org);
//
//        userService.save(user); //can be save(inside update)
//        return user;
//    }
//
//    private UserAccount saveAccount(RegistrationDto registrationDto, User user) {
//        UserAccount account = new UserAccount();
//        //can be persist()
//        account.setId(user.getId());
//        account.setUsername(registrationDto.getUsername());
//        account.setPassword(
//                passwordEncoder.encode(
//                        registrationDto.getPassword()
//                )
//        );
//        account.setEnabled(true);
//        account.setAccountNonExpired(true);
//        account.setAccountNonLocked(true);
//        account.setCredentialsNonExpired(true);
//
//        userAccountService.save(account);//can be save(inside update) account and
//
//        return account;
//    }
//
//    private Authority saveAuthority(UserAccount account, String role) {
//        Authority authority = new Authority();
//        //can be persist()
//        authority.setUserAccount(account);
//        authority.setAuthority(role);
//
//        List<Authority> list = new ArrayList<>();
//        list.add(authority);
//        account.setAuthorities(list);
//
//        authorityService.save(authority);//can be save(inside update)
//
//        return authority;
//    }
//
//    private String defineRole(boolean isGovernment) {
//        if (isGovernment) {
//            return Role.USER_GOVERNMENT.toString();
//        } else {
//            return Role.USER_WORKER.toString();
//        }
//    }
//}
