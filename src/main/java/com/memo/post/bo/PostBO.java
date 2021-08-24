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

		// 게시글번호 10 9 8 | 7 6 5 | 4 3 2 | 1
		// 1) 다음 가장 작은수 (오른쪽 값) => nextId 쿼리 : nextIdParm보다 작은 3개(limit)를 가져오기
		// 2) 이전 가장 큰 수(왼 쪽 값) => prevId 쿼리 : prevIdParm보다 큰 3개(limit)를 가져오기
		// 순서가 뒤집히므로 코드정렬
		String direction = null;
		Integer standardId = null;
		
		if (prevId != null) {
			//이전버튼 클릭
			direction = "prev";
			standardId = prevId;
			
			List<Post> postlist= postDAO.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
			Collections.reverse(postlist);
			return postlist;
		}else if(nextId != null) {
			//다음버튼 클릭
			direction = "next";
			standardId = nextId;
		}
		
		return postDAO.selectPostListByUserId(userId,direction,standardId,POST_MAX_SIZE);
	}
	
	
	//가장 오른쪽 페이지인가?
	public boolean isLastPage(int userId,int nextId) {
		return nextId == postDAO.selectPostByUserIdAndSort(userId, "ASC");
	}
	
	//가장 오른쪽 페이지인가?
	public boolean isFirstPage(int userId,int prevId) {
		return prevId == postDAO.selectPostByUserIdAndSort(userId, "DESC");
	}
	
	
	
	public Post getPostByPostIdAndUserId(int postId,int userId) {
		
		return postDAO.selectPostByPostIdAndUserId(postId,userId);
		
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
		return postDAO.insertPost(userId, subject, content, imageUrl);
	}
	
	
	public int updatePost(int postId,int userId,String userLoginId,
			String subject, String content, MultipartFile file) {
		String imageUrl = null;
		
		if(file !=null) {		
			try {
				//컴퓨터에 파일 업로드 후 웹으로 접근할 수 있는 imageURL을 얻어낸다.
				Post post = postDAO.selectPostByPostIdAndUserId(postId, userId);
				imageUrl= fileManagerSurvice.saveFile(userLoginId,file);		
				
				String oldimage =post.getImagePath();
				
				if(oldimage !=null && imageUrl !=null) {	
				fileManagerSurvice.deleteFile(oldimage);
				}
				
			}catch (Exception e) {
				log.error("[파일 업로드 ]"+e.getMessage());
			}
			
		}
		
		
		
		
		return postDAO.updatePost(postId, userId, subject, content, imageUrl);
	}
	
	
	
	
	
	
}
