package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.RoleDao;
import com.lanit.lkz_project.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public Role getRole(long id) {
        return roleDao.getRole(id);
    }
}
