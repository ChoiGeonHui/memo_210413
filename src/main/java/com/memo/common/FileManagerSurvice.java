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

@Component //�Ϲ����� ��������
public class FileManagerSurvice {
	
	private Logger logger = LoggerFactory.getLogger(FileManagerSurvice.class);
	
	//���� �̹����� (��ǻ�Ϳ�) ����� ���
	public final static String 	FILE_UPLOAD_PATH=
			"G:\\workspace\\6.project\\ex\\memo\\Memo\\src\\main\\resources\\static\\images/";
	// �̹��� ���� -> uri path ����
	public String saveFile(String userLoginId, MultipartFile file) throws IOException {
		//������ ��ǻ�Ϳ� ����
		
		//1. ���� ���丮 ��� �����(�ߺ��Ұ�)
		//��) ����ڸ�_11382734/�̹���.jpg
		String directoryName = userLoginId +"_"+System.currentTimeMillis()+"/";
		String filepath = FILE_UPLOAD_PATH+directoryName;
		
		File directory = new File(filepath);
		if(directory.mkdir()==false) {	//������ ���ε��� filepath ��ο� ���� ������ �Ѵ�.
			logger.error("[���Ͼ��ε�] : ���丮 ���� ����");
			//throw new RuntimeException();
			return null;
		};
		
		// ���� ���ε� = > byte ������ ���ε��Ѵ�.
		byte[] bytes = file.getBytes();
		Path path = Paths.get(filepath + file.getOriginalFilename());
		//getOriginalFilename : input���� ����ڰ� �ø� ���ϸ�
		Files.write(path, bytes);
		
		//�̹��� Url ����� ����
		return "/images/"+directoryName+ file.getOriginalFilename();
	}
	
	
	//���� ����
	
	public void deleteFile(String imagePath) throws IOException {
		
		Path path = Paths.get(FILE_UPLOAD_PATH+imagePath.replace("/images/", ""));
		//���� ����
		if(Files.exists(path)) {
			logger.info("%%%%%%%%%%%%%%%%%%: "+path);
			Files.delete(path);
			
		}
		
		
		//���丮 ����
		path = path.getParent();
		if(Files.exists(path)) {
			logger.info("################: "+path);
			Files.delete(path);
		}
		
		
		
		
	}
	
	
	
	
	
}
