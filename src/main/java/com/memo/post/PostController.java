package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.memo.post.bo.PostBO;
import com.memo.post.model.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/post_list_view")
	public String postListView(Model model,
			HttpServletRequest request) {
		
		HttpSession session =  request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId ==null) {
			//���������� �α��� ���̵� ������ => �α��� �������� �����̷�Ʈ
			return"redirect:/user/sign_in_view";
		}
		
		List<Post> postlist= postBO.getPostListByUserId(userId);
		model.addAttribute("postlist", postlist);
		model.addAttribute("viewName", "/post/list_view");
		return"/templete/layout";
	}
	
	
	

}
