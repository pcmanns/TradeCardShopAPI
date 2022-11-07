package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
		
		@Autowired
		UserRepository repo;
	
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			 
			Optional<User> user = repo.findByUsername(username);
			
			if(user.isEmpty()) {
				throw new UsernameNotFoundException("User with username "+username+" not found");
			}
			
			return new MyUserDetails(user.get());
		}

}


