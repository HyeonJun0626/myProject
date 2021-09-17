package com.dto;

import lombok.Data;

@Data
public class BoardFileDto {
	
	private int imgSeq;
	private int userSeq;
	private int boardSeq;
	private String oriImgName;
	private String storedImgPath;
	private Long imgSize;
	private String delYn;

}