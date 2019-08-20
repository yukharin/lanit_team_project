package com.lanit.satonin18.app.service.authorization;

import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.service.CrudService;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserAccountService extends CrudService<UserAccount> {
    @Override
    void save(UserAccount userAccount) ;

    @Override
    UserAccount findById(int id) ;

    @Override
    List<UserAccount> findAll() ;

    void deleteById(int id) ;

    UserAccount findByUsername(String theSearchName) ;
}
