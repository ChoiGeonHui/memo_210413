package com.memo.post.bo;


import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerSurvice;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final int POST_MAX_SIZE =3;
	
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerSurvice fileManagerSurvice;
	
	public List<Post> getPostListByUserId(int userId, Integer prevId,Integer nextId){

		// �Խñ۹�ȣ 10 9 8 | 7 6 5 | 4 3 2 | 1
		// 1) ���� ���� ������ (������ ��) => nextId ���� : nextIdParm���� ���� 3��(limit)�� ��������
		// 2) ���� ���� ū ��(�� �� ��) => prevId ���� : prevIdParm���� ū 3��(limit)�� ��������
		// ������ �������Ƿ� �ڵ�����
		String direction = null;
		Integer standardId = null;
		
		if (prevId != null) {
			//������ư Ŭ��
			direction = "prev";
			standardId = prevId;
			
			List<Post> postlist= postDAO.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
			Collections.reverse(postlist);
			return postlist;
		}else if(nextId != null) {
			//������ư Ŭ��
			direction = "next";
			standardId = nextId;
		}
		
		return postDAO.selectPostListByUserId(userId,direction,standardId,POST_MAX_SIZE);
	}
	
	
	//���� ������ �������ΰ�?
	public boolean isLastPage(int userId,int nextId) {
		return nextId == postDAO.selectPostByUserIdAndSort(userId, "ASC");
	}
	
	//���� ������ �������ΰ�?
	public boolean isFirstPage(int userId,int prevId) {
		return prevId == postDAO.selectPostByUserIdAndSort(userId, "DESC");
	}
	
	
	
	public Post getPostByPostIdAndUserId(int postId,int userId) {
		
		return postDAO.selectPostByPostIdAndUserId(postId,userId);
		
	}
	
	
	public int create(int userId,String userLoginId,
			String subject,
			String content, MultipartFile image) {
		//������ ������ image URL�� �����ϰ� DB�� �ִ´�.
		String imageUrl = null;
		if(image !=null) {
			
			try {
				//��ǻ�Ϳ� ���� ���ε� �� ������ ������ �� �ִ� imageURL�� ����.
				imageUrl= fileManagerSurvice.saveFile(userLoginId,image);						
			}catch (Exception e) {
				log.error("[���� ���ε� ]"+e.getMessage());
			}
			
		}	
		log.info("#########�̹��� �ּ� : "+ imageUrl);
		return postDAO.insertPost(userId, subject, content, imageUrl);
	}
	
	
	public int updatePost(int postId,int userId,String userLoginId,
			String subject, String content, MultipartFile file) {
		String imageUrl = null;
		
		if(file !=null) {		
			try {
				//��ǻ�Ϳ� ���� ���ε� �� ������ ������ �� �ִ� imageURL�� ����.
				Post post = postDAO.selectPostByPostIdAndUserId(postId, userId);
				imageUrl= fileManagerSurvice.saveFile(userLoginId,file);		
				
				String oldimage =post.getImagePath();
				
				if(oldimage !=null && imageUrl !=null) {	
				fileManagerSurvice.deleteFile(oldimage);
				}
				
			}catch (Exception e) {
				log.error("[���� ���ε� ]"+e.getMessage());
			}
			
		}
		
		
		
		
		return postDAO.updatePost(postId, userId, subject, content, imageUrl);
	}
	
	
	
	
	
	
}
