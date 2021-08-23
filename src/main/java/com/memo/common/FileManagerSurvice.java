package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component //일반적을 스프링빈
public class FileManagerSurvice {
	
	private Logger logger = LoggerFactory.getLogger(FileManagerSurvice.class);
	
	//실제 이미지가 (컴퓨터에) 저장될 경로
	public final static String 	FILE_UPLOAD_PATH=
			"G:\\workspace\\6.project\\ex\\memo\\Memo\\src\\main\\resources\\static\\images/";
	// 이미지 저장 -> uri path 리턴
	public String saveFile(String userLoginId, MultipartFile file) throws IOException {
		//파일을 컴퓨터에 저장
		
		//1. 파일 디렉토리 경로 만들기(중복불가)
		//예) 사용자명_11382734/이미지.jpg
		String directoryName = userLoginId +"_"+System.currentTimeMillis()+"/";
		String filepath = FILE_UPLOAD_PATH+directoryName;
		
		File directory = new File(filepath);
		if(directory.mkdir()==false) {	//파일을 업로드할 filepath 경로에 폴더 생성을 한다.
			logger.error("[파일업로드] : 디렉토리 생성 실패");
			//throw new RuntimeException();
			return null;
		};
		
		// 파일 업로드 = > byte 단위로 업로드한다.
		byte[] bytes = file.getBytes();
		Path path = Paths.get(filepath + file.getOriginalFilename());
		//getOriginalFilename : input에서 사용자가 올린 파일명
		Files.write(path, bytes);
		
		//이미지 Url 만들어 리턴
		return "/images/"+directoryName+ file.getOriginalFilename();
	}
	
	
	//파일 삭제
	
	public void deleteFile(String imagePath) throws IOException {
		
		Path path = Paths.get(FILE_UPLOAD_PATH+imagePath.replace("/images/", ""));
		//파일 삭제
		if(Files.exists(path)) {
			logger.info("%%%%%%%%%%%%%%%%%%: "+path);
			Files.delete(path);
			
		}
		
		
		//디렉토리 삭제
		path = path.getParent();
		if(Files.exists(path)) {
			logger.info("################: "+path);
			Files.delete(path);
		}
		
		
		
		
	}
	
	
	
	
	
}
