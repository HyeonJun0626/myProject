package com.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.BoardDto;
import com.dto.BoardFileDto;

@Component

public class FileUtils {
	
	public List<BoardFileDto> parseFileInfo(BoardDto boardDto, MultipartHttpServletRequest image) throws Exception {
		if (ObjectUtils.isEmpty(image)) {	
			return null;
		}
		
		List<BoardFileDto> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		
		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = image.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = image.getFiles(iterator.next());
			
			for (MultipartFile multiFile : list) {
				if (multiFile.isEmpty() == false) {
					contentType = multiFile.getContentType();
					
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					}
					else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
						else {
							break;
						}
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					if (boardDto.getBoardSeq() == 0) {
						BoardFileDto boardFile = new BoardFileDto();
						
						boardFile.setUserSeq(boardDto.getUserSeq());
						boardFile.setImgSize(multiFile.getSize());
						boardFile.setOriImgName(multiFile.getOriginalFilename());
						boardFile.setStoredImgPath(path + "/" + newFileName);
						fileList.add(boardFile);
						
						file = new File(path + "/" + newFileName);
						multiFile.transferTo(file);						
					}
					else {
						BoardFileDto boardFile = new BoardFileDto();
						
						boardFile.setUserSeq(boardDto.getUserSeq());
						boardFile.setBoardSeq(boardDto.getBoardSeq());
						boardFile.setImgSize(multiFile.getSize());
						boardFile.setOriImgName(multiFile.getOriginalFilename());
						boardFile.setStoredImgPath(path + "/" + newFileName);
						fileList.add(boardFile);
						
						file = new File(path + "/" + newFileName);
						multiFile.transferTo(file);
					}
				}
			}
		}
		return fileList;
	}
}