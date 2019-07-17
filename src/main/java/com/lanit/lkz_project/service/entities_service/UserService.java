package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.UserDAO;
import com.lanit.lkz_project.entities.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void addUser(@NonNull User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(@NonNull User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void removeUser(@NonNull User user) {
        userDAO.removeUser(user);
    }

    @Transactional
    public void removeUser(long id) {
        userDAO.removeUser(id);
    }

    @Transactional
    public User getUser(long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public List<User> users() {
        return userDAO.users();
    }


}
