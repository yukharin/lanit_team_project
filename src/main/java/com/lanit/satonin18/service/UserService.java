package com.lanit.satonin18.service;

import com.lanit.satonin18.model.User;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserService extends CrudService<User> {

    List<User> searchUserByLastName(String theSearchName) ;

    @Override
    void saveOrUpdate(User organization) ;

    @Override
    void update(User organization) ;

    @Override
    void delete(int id) ;

    @Override
    User getById(int id) ;

    @Override
    List<User> list() ;
}
