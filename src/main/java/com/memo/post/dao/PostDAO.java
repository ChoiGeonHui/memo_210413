package com.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.post.model.Post;

@Repository
public interface PostDAO {
	
	
	public List<Post> selectPostListByUserId(int userId);

	public Post selectPostListByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int insertPost(
			@Param("userLoginId") String userLoginId,
			@Param("subject") String subject,
			@Param("content") String content, 
			@Param("imagePath") String imagePath);

}
