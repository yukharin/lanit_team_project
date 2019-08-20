package com.lanit.satonin18.app.service.authorization;

import com.lanit.satonin18.app.entity.authorization.Authority;
import com.lanit.satonin18.app.repository.authorization.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorityServiceService")
public class AuthorityServiceImpl
        implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public void save(Authority authority){
        authorityRepository.save(authority);
    }

    @Override
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
    public void deleteById(int id){
        authorityRepository.deleteById(id);
    }
}
