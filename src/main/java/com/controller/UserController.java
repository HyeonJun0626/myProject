package com.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDto;
import com.dto.UserInfo;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	final private static Logger LOG = Logger.getGlobal();
	
	@PostMapping("/signup")
	public void signUp(@RequestBody UserDto userDto) throws Exception {
		LOG.info("SignUp Request Success !");
		userService.signUp(userDto);
	}

	@PostMapping("/login")
	public Object login(@RequestBody UserDto userDto, HttpServletResponse response) throws Exception{
//		LOG.info("Login Request Success !");
//		userService.userLogin(userDto);
		return userService.userLogin(userDto, response);
	}
	
	@PostMapping("/getUserInfo") 
	public UserInfo getUserInfo(Authentication authentication) throws Exception {
		
		int userSeq = (int) ((UserDto) authentication.getPrincipal()).getUserSeq();
		UserInfo userInfo = userService.getUserInfo(userSeq);
		System.out.println(userInfo);
		return userInfo;
	}

}
