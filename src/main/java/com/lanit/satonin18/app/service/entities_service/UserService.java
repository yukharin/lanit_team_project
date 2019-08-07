package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.User;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserService extends CrudService<User> {
    @Override
    void save(User organization) ;

    @Override
    void update(User organization) ;

    @Override
    User getById(int id) ;

    @Override
    List<User> list() ;

    void deleteById(int id) ;

    List<User> searchUserByLastName(String theSearchName) ;
}
