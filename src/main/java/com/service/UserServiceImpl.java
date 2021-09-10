package com.service;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.configuration.JwtTokenProvider;
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
	public void signUp(UserDto userDto) throws Exception {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String encodedPassword = passwordEncoder.encode(userDto.getUserPw());
         userDto.setUserPw(encodedPassword);
//         userDto.setToken(jwtTokenProvider.createToken(userDto));
         userMapper.signUp(userDto);
         LOG.info("SignUp Request Success !");
     };
     
     
     @Override
     public String userLogin(UserDto userDto, HttpServletResponse response) throws Exception {
    	 // 회원 아이디 체크
    	 UserDto signUser = userMapper.userLogin(userDto.getUserId());
    	 // 아이디 틀렷을 때
    	 if (signUser == null) {
    		 LOG.info("User ID None");
    	 }
    	 // 암호화 한 패스워드와 비교

    	 boolean pwCheck = passwordEncoder.matches(userDto.getUserPw(), signUser.getUserPw());
    	 // 로그인 성공
    	 String Token = null;
    	 String result = null;
    	 if (pwCheck) {
    		 LOG.info("Login Success !");
    		 result = "true";
    		 Token = jwtTokenProvider.createToken(signUser.getUserSeq());
    		 response.setHeader("Authorization", Token);
    	 }
    	 else {
    		 result = "false";
    	 }
    	 
    	 return result; 
    	 
    	 
     }
     
     public UserInfo getUserInfo(int userSeq) throws Exception {
    	 return userMapper.getUserInfo(userSeq);
     }


}
