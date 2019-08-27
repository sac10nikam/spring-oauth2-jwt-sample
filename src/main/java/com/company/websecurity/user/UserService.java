package com.company.websecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
	
   @Autowired
   OAuthDao oauthDao;

   @Override
   public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
      try {
         return new CustomUser(oauthDao.getUserDetails(username));
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new UsernameNotFoundException("User " + username + " was not found in the database");
      }
   }
} 