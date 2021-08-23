package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("viewName", "post/list_view");
		return"/templete/layout";
	}
	
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/post_create");
		
		return "/templete/layout";
	}
	
	@RequestMapping("/post_detail_view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			HttpServletRequest request
			,Model model) {
		HttpSession  session=request.getSession();
		Integer userId = (Integer)  session.getAttribute("userId");
		
		if(userId ==null) {
			//���ǿ� �α��� ���̵� ���ٸ� �α����� �ȵ� ���̹Ƿ� �α����������� �����̷�Ʈ
			return "redirect:/user/sign_in_view";
		}

		
		Post post = postBO.getPostByPostIdAndUserId(postId, userId);
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_detail");
		
		return "/templete/layout";
	}
	
	

}
