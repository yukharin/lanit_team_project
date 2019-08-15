package com.lanit.satonin18.app.service.authorization;


import com.lanit.satonin18.app.entity.authorization.Account;
import com.lanit.satonin18.app.service.entities_service.CrudService;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface AccountService extends CrudService<Account> {
    @Override
    void save(Account organization) ;

    @Override
    Account findById(int id) ;

    @Override
    List<Account> findAll() ;

    void deleteById(int id) ;

    Account findByUsername(String theSearchName) ;
}
