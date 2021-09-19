package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dto.BoardDto;
import com.dto.BoardFileDto;

@Mapper
public interface BoardMapper {
	
	void profileInsert(List<BoardFileDto> list) throws Exception;
//	void reNick(@Param("userSeq") int userSeq, @Param("userNick") String userNick) throws Exception;
	void reNick(Map<String, Object> data);
	
	void boardInsert(BoardDto boardDto) throws Exception;
	void boardImgInsert(List<BoardFileDto> list) throws Exception;
	
	List<BoardDto> getBoardList(@Param("userSeq") int userSerq) throws Exception;
	List<BoardFileDto> getBoardImg(@Param("boardSeq") int boardSeq) throws Exception;
	
	List<BoardDto> getAllBoardList() throws Exception;
	int getAllBoardLike(@Param("boardSeq") int boardSeq) throws Exception;
	int followCheck(@Param("userSeq") int userSeq, @Param("boardUserSeq") int userSeq1) throws Exception;
	String getUserImg(@Param("userSeq") int userSeq) throws Exception;
	
	int checkMyLike(@Param("userSeq") int userSeq, @Param("boardSeq") int boardSeq) throws Exception;
	
	void addLike(@Param("userSeq") int userSeq, @Param("boardSeq") int boardSeq) throws Exception;
	
	void disLike(@Param("userSeq") int userSeq, @Param("boardSeq") int boardSeq) throws Exception;
}
