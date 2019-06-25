package com.lanit.satonin18.service;

import com.lanit.satonin18.model.User;

import java.util.List;

public interface UserService {
   void save(User user);

   List<User> list();
}
