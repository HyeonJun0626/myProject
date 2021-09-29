package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;
import com.dto.ReplyDto;

public interface BoardService {

	void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;
	void boardUpdate(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;
	
	List<BoardDto> getBoardList(int userSeq) throws Exception;
	
	List<BoardDto> getAllBoardList(int userSeq, int startNum) throws Exception;
	
	int likeOnOf(int userSeq, int boardSeq) throws Exception;
	
	void deleteBoard(int boardSeq) throws Exception;
	
	BoardDto getReWriteBoard(int boardSeq) throws Exception;
	
	ReplyDto inputReply(ReplyDto reply) throws Exception;
	
	void deleteReply(int replySeq) throws Exception;
}
