package com.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;
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
	public String signUp(@RequestBody UserDto userDto) throws Exception {
		LOG.info("SignUp Request Success !");
		return userService.signUp(userDto);
	}
	
	@CrossOrigin(exposedHeaders = "authorization") // 리스폰스 관련 설정인듯
	@PostMapping("/login")
	public Object login(@RequestBody UserDto userDto, HttpServletResponse response) throws Exception{
//		LOG.info("Login Request Success !");
//		userService.userLogin(userDto);
		return userService.userLogin(userDto, response);
	}
	
	@PostMapping("/getUserInfo") 
	public UserInfo getUserInfo(Authentication authentication) throws Exception {
		
		int userSeq = (int) ((UserDto) authentication.getPrincipal()).getUserSeq();
//		System.out.println(userService.getUserInfo(userSeq));
		return userService.getUserInfo(userSeq);
	}
	
	@PostMapping("/profileInsert")
	public void profileInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		userService.profileInsert(boardDto, image);
	}
	
	@PostMapping("/deleteProfileImg")
	public void deleteProfileImg(@RequestParam(value="userSeq") int userSeq) throws Exception {
		userService.deleteProfileImg(userSeq);
	}
	
	@PostMapping("/addFollow")
	public UserInfo addFollow(int fromUserSeq, int toUserSeq) throws Exception {
		UserInfo follow = userService.addFollow(fromUserSeq, toUserSeq);
		return follow;
	}
	@PostMapping("/disFollow")
	public void disFollow(int fromUserSeq, int toUserSeq) throws Exception {
		userService.disFollow(fromUserSeq, toUserSeq);
	}
	
	@GetMapping("/getFollowList")
	public Object getFollowList(@RequestParam(value="userSeq") int userSeq) throws Exception {
		List<UserInfo> followList = userService.getFollowList(userSeq);
		return followList;
	}
	@GetMapping("/getFollowerList")
	public Object getFollowerList(@RequestParam(value="userSeq") int userSeq) throws Exception {
		List<UserInfo> followerList = userService.getFollowerList(userSeq);
		return followerList;
	}
	
	@GetMapping("/getTopUserList")
	public Object getTopUserList() throws Exception {
		List<UserInfo> topUserList = userService.getTopUserList();
		return topUserList;
	}
}

