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
		//파일을 가지고 image URL로 구성하고 DB에 넣는다.
		String imageUrl = null;
		if(image !=null) {
			
			try {
				//컴퓨터에 파일 업로드 후 웹으로 접근할 수 있는 imageURL을 얻어낸다.
				imageUrl= fileManagerSurvice.saveFile(userLoginId,image);						
			}catch (Exception e) {
				log.error("[파일 업로드 ]"+e.getMessage());
			}
			
		}	
		log.info("#########이미지 주소 : "+ imageUrl);
		return postDAO.insertPost(userLoginId, subject, content, imageUrl);
	}
}
