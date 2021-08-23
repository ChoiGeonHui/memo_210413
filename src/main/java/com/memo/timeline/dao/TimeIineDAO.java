package com.memo.timeline.dao;



import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;



@Repository
public interface TimeIineDAO {
	
	
	public int insertPost(
			@RequestParam("content") String content,
			@RequestParam("image")String imagepath);

}
