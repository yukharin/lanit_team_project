package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.Organization;

import java.util.List;

public interface OrganizationDAO extends CrudDAO<Organization>{
    @Override
    public void save(Organization organization) ;
    @Override
    public void update(Organization organization);

    @Override
    public void delete(Organization organization) ;

    @Override
    public Organization getById(int id);

    @Override
    public List<Organization> list();

    public void deleteById(int id);
}
