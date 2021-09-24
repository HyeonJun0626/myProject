package com.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;
import com.dto.UserDto;
import com.dto.UserInfo;

public interface UserService {
		
	String signUp(UserDto userDto) throws Exception;
	
	String userLogin(UserDto userDto, HttpServletResponse response) throws Exception;
	
//	UserDto getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	UserInfo getUserInfo(int userSeq) throws Exception;
	
	void profileInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;
	
	void deleteProfileImg(int userSeq) throws Exception;
	
	UserInfo addFollow(int fromUserSeq, int toUserSeq) throws Exception;
	void disFollow(int fromUserSeq, int toUserSeq) throws Exception;
	
	List<UserInfo> getFollowList(int userSeq) throws Exception;
	List<UserInfo> getFollowerList(int userSeq) throws Exception;

}
