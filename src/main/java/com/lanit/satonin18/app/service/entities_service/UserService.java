package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.User;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserService extends CrudService<User> {
    @Override
    void save(User organization) ;

    @Override
    User findById(int id) ;

    @Override
    List<User> findAll() ;

    void deleteById(int id) ;

    List<User> findByLastNameIgnoreCaseContaining(String theSearchName) ;
}
