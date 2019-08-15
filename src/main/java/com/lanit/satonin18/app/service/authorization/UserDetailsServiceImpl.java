package com.lanit.satonin18.app.service.authorization;


import com.lanit.satonin18.app.entity.authorization.Account;
import com.lanit.satonin18.app.repository.authorization.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private AccountRepository accountRepository;

//   public void save(Account account) {
//      accountRepository.save(account);
//   }
//
//   public void delete(Account account) {
//      accountRepository.delete(account);
//   }
//
//   public Account findById(int id) {
//      return accountRepository.findById(id).get();
//   }
//
//   public List<Account> findAll() {
//      return accountRepository.findAll();
//   }
//
//   public void deleteById(int id) {
//      accountRepository.deleteById(id);
//   }
//
//   public Account findByUsername(String username) {
//      return accountRepository.findByUsername(username);
//   }

//   @Transactional(readOnly = true)
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Account account = accountRepository.findByUsername(username);
      org.springframework.security.core.userdetails.User.UserBuilder builder = null;
      if (account != null) {

         builder = org.springframework.security.core.userdetails.User.withUsername(username);
         builder.disabled(!account.isEnabled());
         builder.password(account.getPassword());
         String[] authorities = account.getAuthorities()
                 .stream().map(a -> a.getAuthority()).toArray(String[]::new);

         builder.authorities(authorities);
      } else {
         throw new UsernameNotFoundException("Account not found.");
      }
      return builder.build();
   }
}