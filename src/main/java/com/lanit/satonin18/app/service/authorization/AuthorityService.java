package com.lanit.satonin18.app.service.authorization;

import com.lanit.satonin18.app.entity.authorization.Authority;
import com.lanit.satonin18.app.service.CrudService;

import java.util.List;

public interface AuthorityService  extends CrudService<Authority> {
    @Override
    void save(Authority authority) ;

    @Override
    Authority findById(int id) ;

    @Override
    List<Authority> findAll() ;

    void deleteById(int id) ;
}
