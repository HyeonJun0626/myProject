package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dto.BoardDto;
import com.dto.BoardFileDto;

@Mapper
public interface BoardMapper {
	
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
	
	void deleteBoard(@Param("delBoardSeq") int delBoardSeq) throws Exception;
	void deleteBoardImg(@Param("delBoardSeq") int delBoardSeq) throws Exception;
}
