package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.dto.BoardFileDto;
import com.dto.UserDto;
import com.dto.UserInfo;

@Mapper
public interface UserMapper {
//	int addUser(UserDto userDto);

	int signUp (UserDto userDto) throws Exception;
	
	int idCheck (String userId) throws Exception;

	 
	UserDto userLogin(@Param("userId") String userId) throws Exception;
	
//	String loadUserByUsername(@Param(value = "username") String username) throws Exception;
	
	UserDetails loadUserByUsername(@Param(value = "userSeq") String userSeq) throws Exception;
	
	UserInfo getUserInfo(int userSeq) throws Exception;
	UserInfo getProfileImg(int userSeq) throws Exception;
	int getWriteCnt(int userSeq) throws Exception;
	
	int checkDefaultProfile(int userSeq) throws Exception;
	void profileUpdate(List<BoardFileDto> list) throws Exception;
	void profileInsert(List<BoardFileDto> list) throws Exception;
	void reNick(Map<String, Object> data);
	void deleteProfileImg(int userSeq) throws Exception;

}
