package com.memo.post.bo;

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

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerSurvice fileManagerSurvice;
	
	public List<Post> getPostListByUserId(int userId){
		return postDAO.selectPostListByUserId(userId);
	}
	
	
	public Post getPostByPostIdAndUserId(int postId,int userId) {
		
		return postDAO.selectPostListByPostIdAndUserId(postId,userId);
		
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
		return postDAO.insertPost(userLoginId, subject, content, imageUrl);
	}
}
