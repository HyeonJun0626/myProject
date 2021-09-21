package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.FileUtils;
import com.dto.BoardDto;
import com.dto.BoardFileDto;
import com.mapper.BoardMapper;



@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileutils;

	
	@Override
	public void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		
		boardMapper.boardInsert(boardDto);
		if (!"".equals(Integer.toString(boardDto.getBoardSeq())) & Integer.toString(boardDto.getBoardSeq()) != null) {
			
			List<BoardFileDto> list = fileutils.parseFileInfo(boardDto, image);
			
			if (CollectionUtils.isEmpty(list) == false) {
				
				boardMapper.boardImgInsert(list);
				
			}
		}
	}
	
	@Override
	public List<BoardDto> getBoardList(int userSeq) throws Exception {
		List<BoardDto> board = boardMapper.getBoardList(userSeq);
		
		for (int i = 0; i < board.size(); i++) {
			List<BoardFileDto> imgList = boardMapper.getBoardImg(board.get(i).getBoardSeq());
			int boardLike = boardMapper.getAllBoardLike(board.get(i).getBoardSeq());
			board.get(i).setImgList(imgList);
			board.get(i).setLikeCnt(boardLike);
		}
		return board;
	}
	
	@Override
	public List<BoardDto> getAllBoardList(int userSeq) throws Exception {
		List<BoardDto> board = boardMapper.getAllBoardList();
		
		for (int i = 0; i < board.size(); i++) {
			List<BoardFileDto> imgList = boardMapper.getBoardImg(board.get(i).getBoardSeq());
			board.get(i).setImgList(imgList);
			String userImg = boardMapper.getUserImg(board.get(i).getUserSeq());
			int boardLike = boardMapper.getAllBoardLike(board.get(i).getBoardSeq());
			int likeNy = boardMapper.checkMyLike(userSeq, board.get(i).getBoardSeq());
			int followCheck = boardMapper.followCheck(userSeq, board.get(i).getUserSeq());
			board.get(i).setFollowCheck(followCheck);
			board.get(i).setLikeNy(likeNy);
			board.get(i).setUserImg(userImg);
			board.get(i).setLikeCnt(boardLike);
		}
		return board;
	}
	
	@Override
	public int likeOnOf(int userSeq, int boardSeq) throws Exception {
		int checkLike = boardMapper.checkMyLike(userSeq, boardSeq);
		int result = 0;
		if(checkLike == 0) {
			boardMapper.addLike(userSeq, boardSeq);
			result = 1;
		}
		else {
			boardMapper.disLike(userSeq, boardSeq);
			result = 0;
		}
		return result;
	}
	
	@Override
	public void deleteBoard(int delBoardSeq) throws Exception {
		boardMapper.deleteBoard(delBoardSeq);
		boardMapper.deleteBoardImg(delBoardSeq);
	}
		

}
