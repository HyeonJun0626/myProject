package com.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardDto {

	private int userSeq;
	private int boardSeq;
	private int likeCnt;
	private int likeNy;
	private int followCheck;
	private String userNick;
	private String content;
	private String createDt;
	private String delYn;
	private String userImg;
	private List<BoardFileDto> imgList;
	
}
