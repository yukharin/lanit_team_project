package com.lanit.satonin18.app.service.entities_service;

import java.util.List;

import com.lanit.satonin18.app.repository.UserRepository;
import com.lanit.satonin18.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImp
        implements UserService  {

   @Autowired
   private UserRepository userRepository;

   @Override
   @Transactional
   public void save(User user) {
      userRepository.save(user);
   }

   @Override
   @Transactional
   public void delete(User user) {
      userRepository.delete(user);
   }

   @Override
   public User findById(int id) {
      return userRepository.findById(id).get();
   }

   @Override
   public List<User> findAll() {
      return userRepository.findAll();
   }

   @Override
   @Transactional
   public void deleteById(int id) {
      userRepository.deleteById(id);
   }

   @Override
   public List<User> findByLastNameIgnoreCaseContaining(String theSearchName) {
      return userRepository.findByLastNameIgnoreCaseContaining(theSearchName);
   }
}