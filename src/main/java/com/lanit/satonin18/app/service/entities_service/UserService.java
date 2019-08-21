package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.service.CrudService;

import java.util.List;

public interface UserService
        extends CrudService<User> {

    @Override
    void save(User organization) ;

    @Override
    User findById(int id) ;

    @Override
    List<User> findAll() ;

    void deleteById(int id) ;

    List<User> findByLastNameIgnoreCaseContaining(String theSearchName) ;
}
