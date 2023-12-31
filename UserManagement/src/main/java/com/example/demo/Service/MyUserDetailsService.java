package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> userList=userRepo.findByUsername(username);
		if(userList==null || userList.size()<=0) {
			throw new UsernameNotFoundException("no user found with that username");
		}
		else {
			User user=userList.get(0);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(user.getRole()))));
		}
	}


}
