package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;

public interface BoardService {

	void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception;
	
	List<BoardDto> getBoardList(int userSeq) throws Exception;
	
	List<BoardDto> getAllBoardList(int userSeq) throws Exception;
	
	int likeOnOf(int userSeq, int boardSeq) throws Exception;
	
	void deleteBoard(int boardSeq) throws Exception;
}
