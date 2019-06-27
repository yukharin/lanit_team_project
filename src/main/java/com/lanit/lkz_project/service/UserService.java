package com.lanit.lkz_project.service;

import com.lanit.lkz_project.dao.UserDAO;
import com.lanit.lkz_project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    @Transactional
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }

    @Transactional
    public User getUser(int id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public List<User> users() {
        return userDAO.users();
    }


}
