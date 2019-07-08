package com.lanit.lkz_project.authorization;

import com.lanit.lkz_project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceAuthorization {

    @Autowired
    UserDaoAuthorization userDaoAuthorization;

    @Transactional
    public User authorize(String login, String password) {
        return userDaoAuthorization.authorizeUser(login, password);
    }

}
