package com.lanit.lkz_project.service.entities_service;


import com.lanit.lkz_project.dao.entities_dao.UserRepository;
import com.lanit.lkz_project.entities.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addUser(@NonNull User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(@NonNull User user) {
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(@NonNull User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void removeUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    public List<User> users() {
        return userRepository.findAll();
    }


}
