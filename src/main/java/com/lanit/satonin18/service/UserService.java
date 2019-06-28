package com.lanit.satonin18.service;

import com.lanit.satonin18.model.User;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserService extends CrudService<User> {

    public List<User> searchUserByLastName(String theSearchName) ;

    @Override
    public void saveOrUpdate(User organization) ;

    @Override
    public void update(User organization) ;

    @Override
    public void delete(int id) ;

    @Override
    public User getById(int id) ;

    @Override
    public List<User> list() ;
}
