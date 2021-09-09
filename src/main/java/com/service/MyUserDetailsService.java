package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
		
		UserDetails User = null;
		try {
			User = userMapper.loadUserByUsername(userSeq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (userSeq == null) {
            throw new UsernameNotFoundException(userSeq + "is not found.");
        }

		return User;
	}


}
