package com.lanit.lkz_project.service.jpa_entities_service;


import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.repositories.entitity_repositories.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addUser(@Valid @NonNull User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(@NonNull User user) {
        userRepository.saveAndFlush(user);
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
        return userRepository.findById(id).get();
    }

    @Transactional
    public List<User> users() {
        return userRepository.findAll();
    }


}
