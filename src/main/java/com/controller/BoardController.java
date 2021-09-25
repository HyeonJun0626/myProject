package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;
import com.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@CrossOrigin(exposedHeaders = "authorization")
	@PostMapping ("/insert")
	public void boardInsert(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		boardService.boardInsert(boardDto, image);
	}
	@CrossOrigin(exposedHeaders = "authorization")
	@PostMapping ("/boardUpdate")
	public void boardUpdate(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		boardService.boardUpdate(boardDto, image);
	}
	
	@GetMapping("/getBoardList")
	public Object getBoardList(@RequestParam(value="userSeq") int userSeq) throws Exception {
		List<BoardDto> board = boardService.getBoardList(userSeq); 
		return board;
	}
	
	@PostMapping("/getAllBoardList")
	public Object getAllBoardList(@RequestParam(value="userSeq") int userSeq) throws Exception {
		List<BoardDto> board = boardService.getAllBoardList(userSeq);
		return board;
	}
	
	@PostMapping("/likeOnOf")
	public int likeOnOf(@RequestParam(value="userSeq") int userSeq, @RequestParam(value="boardSeq")int boardSeq) throws Exception {
		
		return boardService.likeOnOf(userSeq, boardSeq);
	}
	
	@PostMapping("/deleteBoard")
	public void deleteBoard(@RequestParam(value="delBoardSeq") int delBoardSeq) throws Exception {
		boardService.deleteBoard(delBoardSeq);
	}
	
	@GetMapping("/getReWriteBoard")
	public BoardDto getReWriteBoard(@RequestParam(value="boardSeq") int boardSeq) throws Exception {
		return boardService.getReWriteBoard(boardSeq);
	}
}
