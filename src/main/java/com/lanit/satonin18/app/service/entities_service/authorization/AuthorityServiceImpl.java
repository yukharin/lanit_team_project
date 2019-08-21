package com.lanit.satonin18.app.service.entities_service.authorization;

import com.lanit.satonin18.app.entity.authorization.Authority;
import com.lanit.satonin18.app.repository.authorization.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorityServiceService")
public class AuthorityServiceImpl
        implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void save(Authority authority){
        authorityRepository.save(authority);
    }

    @Override
    @Transactional
    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

    @Override
    public Authority findById(int id) {
        return authorityRepository.findById(id).get();
    }

    @Override
    public List<Authority> findAll(){
        return authorityRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id){
        authorityRepository.deleteById(id);
    }
}
