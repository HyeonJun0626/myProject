package com.dto;

import lombok.Data;

@Data
public class UserInfo {
	private long userSeq;
	private String userId;
	private String userNick;
	private int roles;
	
}
