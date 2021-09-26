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
	
	UserInfo getUserInfo(@Param(value = "userSeq") int userSeq) throws Exception;
	UserInfo getProfileImg(@Param(value = "userSeq") int userSeq) throws Exception;
	int getWriteCnt(@Param(value = "userSeq") int userSeq) throws Exception;
	
	int checkDefaultProfile(@Param(value = "userSeq") int userSeq) throws Exception;
	void profileUpdate(List<BoardFileDto> list) throws Exception;
	void profileInsert(List<BoardFileDto> list) throws Exception;
	void reNick(Map<String, Object> data);
	void boardReNick(Map<String, Object> data);
	void deleteProfileImg(@Param(value = "userSeq") int userSeq) throws Exception;
	
	void addFollow(@Param(value = "fromUserSeq") int fromUserSeq, @Param(value = "toUserSeq") int toUserSeq) throws Exception;
	void addFollowCnt(@Param(value="toUserSeq") int userSeq) throws Exception;
	void disFollow(@Param(value = "fromUserSeq") int fromUserSeq, @Param(value = "toUserSeq") int toUserSeq) throws Exception;
	void disFollowCnt(@Param(value="toUserSeq") int userSeq) throws Exception;
	UserInfo getFollow(@Param(value = "toUserSeq") int toUserSeq) throws Exception;
	
	List<UserInfo> getFollowList(@Param(value = "userSeq") int userSeq) throws Exception;
	List<UserInfo> getFollowerList(@Param(value = "userSeq") int userSeq) throws Exception;
	
	List<UserInfo> getTopUserSeq() throws Exception;




}
