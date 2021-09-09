package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.dto.UserDto;
import com.dto.UserInfo;

@Mapper
public interface UserMapper {
//	int addUser(UserDto userDto);

	int signUp (UserDto userDto) throws Exception;

	 
	UserDto userLogin(@Param("userId") String userId) throws Exception;
	
//	String loadUserByUsername(@Param(value = "username") String username) throws Exception;
	
	UserDetails loadUserByUsername(@Param(value = "userSeq") String userSeq) throws Exception;
	
	UserInfo getUserInfo(int userSeq) throws Exception;

}
