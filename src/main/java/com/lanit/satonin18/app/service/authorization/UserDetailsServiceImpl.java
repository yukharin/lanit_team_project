package com.lanit.satonin18.app.service.authorization;


import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.repository.authorization.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserAccountRepository userAccountRepository;

//   @Transactional(readOnly = true)
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      UserAccount userAccount = userAccountRepository.findByUsername(username);
      org.springframework.security.core.userdetails.User.UserBuilder builder = null;
      if (userAccount != null) {

         builder = org.springframework.security.core.userdetails.User.withUsername(username);
         builder.disabled(!userAccount.isEnabled());
         builder.password(userAccount.getPassword());
         String[] authorities = userAccount.getAuthorities()
                 .stream().map(a -> a.getAuthority()).toArray(String[]::new);

         builder.authorities(authorities);
      } else {
         throw new UsernameNotFoundException("UserAccount not found.");
      }
      return builder.build();
   }
}