package com.service;


import javax.servlet.http.HttpServletResponse;

import com.dto.UserDto;
import com.dto.UserInfo;

public interface UserService {
		
	void signUp(UserDto userDto) throws Exception;
	
	String userLogin(UserDto userDto, HttpServletResponse response) throws Exception;
	
//	UserDto getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	UserInfo getUserInfo(int userSeq) throws Exception;

}
