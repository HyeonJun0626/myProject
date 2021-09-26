package com.dto;

import lombok.Data;

@Data
public class ReplyDto {
	private int replySeq;
	private int boardSeq;
	private int userSeq;
	private String userNick;
	private String content;
	private String createDt;
}
