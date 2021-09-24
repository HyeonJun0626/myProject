package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.FileUtils;
import com.configuration.JwtTokenProvider;
import com.dto.BoardDto;
import com.dto.BoardFileDto;
import com.dto.UserDto;
import com.dto.UserInfo;
import com.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	final private static Logger LOG = Logger.getGlobal();
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider; 
	
	@Autowired
	private FileUtils fileutils;
	
	
//    @Autowired
//    public UserServiceImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
	
//	사용자의 비밀번호를 BCrypt 해시 함수를 사용해 비밀번호를 해시하는 PasswordEncoder
//	Password Cracking에 대한 저항력을 높이기 위해 의도적으로 느리게 설정
//	전문 장비를 이용하면 1초에 수억번 이상을 시도할 수 있음 이런 유형의 공격을 어렵게 만들기 위해 1개의 암호를 확인하는데 약 1초 정도의 시간이 걸리도록 하는것은 권장
//	시스템에 맞게 테스트하면서 속도를 조정해줘야 함
//	BCryptPasswordEncoder의 속도는 강도(strength)를 조정해서 조절할 수 있음 4~31 default: 10
	@Override
	public String signUp(UserDto userDto) throws Exception {
		String ErrorInfo = null;
		int result = userMapper.idCheck(userDto.getUserId());
		if (result == 0) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(userDto.getUserPw());
			userDto.setUserPw(encodedPassword);
			userMapper.signUp(userDto);
			LOG.info("SignUp Request Success !");
			ErrorInfo = "true";
		}
		else {
			ErrorInfo = "이미 가입된 계정입니다.";
		}
		return ErrorInfo;
		 
     };
     
     
     @Override
     public String userLogin(UserDto userDto, HttpServletResponse response) throws Exception {
    	 String Token = null;
    	 String result = null;
    	 // 회원 아이디 체크
    	 UserDto signUser = userMapper.userLogin(userDto.getUserId());
    	 // 아이디 틀렷을 때
    	 if (signUser == null) {
    		 LOG.info("User ID None");
    		 result = "가입되지 않은 계정입니다.";
    	 }
    	 else {
    		 result = "IdCheckOk";
    		 boolean pwCheck = passwordEncoder.matches(userDto.getUserPw(), signUser.getUserPw());
    		 // 로그인 성공
    		 if (pwCheck) {
    			 LOG.info("Login Success !");
    			 result = "true";
    			 Token = jwtTokenProvider.createToken(signUser.getUserSeq());
    			 response.setHeader("Authorization", Token);
    		 }
    		 else {
    			 result = "비밀번호를 확인하세요";
    		 }
    	 }
    	 // 암호화 한 패스워드와 비교

    	 
    	 return result; 
    	 
    	 
     }
     @Override
     public UserInfo getUserInfo(int userSeq) throws Exception {
    	 UserInfo userInfo = userMapper.getUserInfo(userSeq); 
    	 UserInfo imgInfo = userMapper.getProfileImg(userSeq);
    	 int writeCnt = userMapper.getWriteCnt(userSeq);
    	 userInfo.setWriteCnt(writeCnt);
    	 if (imgInfo != null) {
    		 userInfo.setImgSeq(imgInfo.getImgSeq());
    		 userInfo.setStoredImgPath(imgInfo.getStoredImgPath());
    	 }
    	 else {
    		 userInfo.setStoredImgPath(null);
    	 }
    	 return userInfo;
     }
     
 	@Override
 	public void profileInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
 		
 		List<BoardFileDto> list = fileutils.parseFileInfo(boardDto, image);
 		
 		if (CollectionUtils.isEmpty(list) == false) {
 			int check = userMapper.checkDefaultProfile(boardDto.getUserSeq());
 			if (check == 0) {
 				userMapper.profileInsert(list); 				
 			}
 			else {
 				userMapper.profileUpdate(list);
 			}
 		}
 		
 		if (!"".equals(boardDto.getUserNick()) & boardDto.getUserNick() != null) {
 			Map <String, Object> data = new HashMap<String, Object>();
 			data.put("userSeq", boardDto.getUserSeq());
 			data.put("userNick", boardDto.getUserNick());
 			userMapper.reNick(data);
 			userMapper.boardReNick(data);
 		}

 	}
    
 	@Override
    public void deleteProfileImg(int userSeq) throws Exception {
    	 userMapper.deleteProfileImg(userSeq);
    }
     
    @Override
    public UserInfo addFollow(int fromUserSeq, int toUserSeq) throws Exception {
    	userMapper.addFollow(fromUserSeq, toUserSeq);
    	return userMapper.getFollow(toUserSeq);
    }
    @Override
    public void disFollow(int fromUserSeq, int toUserSeq) throws Exception {
    	userMapper.disFollow(fromUserSeq, toUserSeq);
    }
    
    @Override
    public List<UserInfo> getFollowList(int userSeq) throws Exception {
    	List<UserInfo> follow = userMapper.getFollowList(userSeq);
    	return follow;
    }
    @Override
    public List<UserInfo> getFollowerList(int userSeq) throws Exception {
    	List<UserInfo> follower = userMapper.getFollowerList(userSeq);
    	return follower;
    }


}
