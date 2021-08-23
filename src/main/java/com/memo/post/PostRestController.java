package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> postCreate(
			@RequestParam("subject")String subject,
			@RequestParam("content")String content,
			@RequestParam(value =  "image", required = false) MultipartFile image,
			HttpServletRequest request
			) {
		HttpSession session= request.getSession();
		Integer userId =(Integer) session.getAttribute("userId");
		String userLoginId =(String) session.getAttribute("userLoginId");
		
		int insert = postBO.create(userId, userLoginId,subject, content, image);
		Map<String, String> result = new HashMap<String, String>();
		
		
		result.put("result", "succcess");
		return result;
	}

}
