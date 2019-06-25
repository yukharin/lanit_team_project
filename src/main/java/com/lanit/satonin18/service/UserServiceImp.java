package com.lanit.satonin18.service;

import java.util.List;

import com.lanit.satonin18.model.User;
import com.lanit.satonin18.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDAO userDao;

   @Transactional
   public void save(User user) {
      userDao.saveOrUpdate(user);
   }

   @Transactional(readOnly = true)
   public List<User> list() {
      return userDao.list();
   }

}
