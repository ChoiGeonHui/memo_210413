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
	public String postListView(
			@RequestParam(value = "prevId",required = false) Integer prevIdParm,
			@RequestParam(value = "nextId",required = false) Integer nextIdParm,
			Model model,
			HttpServletRequest request) {
		
		HttpSession session =  request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId ==null) {
			//세션정보에 로그인 아이디가 없으면 => 로그인 페이지로 리다이렉트
			return"redirect:/user/sign_in_view";
		}
		
		//게시글번호 10 9 8 |  7 6 5 | 4 3 2 | 1
		// 1) 다음 가장 작은수 (오른쪽 값) => nextId  쿼리 : nextIdParm보다 작은 3개(limit)를 가져오기
		// 2) 이전 가장 큰 수(왼 쪽 값) => prevId  쿼리 : prevIdParm보다  큰 3개(limit)를 가져오기
		//순서가 뒤집히므로 코드정렬
			
		int prevId = 0;
		int nextId = 0;
		List<Post> postlist= postBO.getPostListByUserId(userId, prevIdParm, nextIdParm);
		
		if(postlist.isEmpty()==false) {
			prevId = postlist.get(0).getId();
			nextId = postlist.get(postlist.size()-1).getId();
			
		}
		
		if(postBO.isLastPage(userId, nextId)) {
			nextId = 0;
		}
		if(postBO.isFirstPage(userId, prevId)) {
			prevId = 0;
		}
		
		
		model.addAttribute("postlist", postlist);
		model.addAttribute("viewName", "post/list_view");
		model.addAttribute("prev", prevId);//리스트중 가장 앞쪽
		model.addAttribute("next", nextId);//리스트 중 가장 뒷쪽 		
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
			//세션에 로그인 아이디가 없다면 로그인이 안된 것이므로 로그인페이지로 리다이렉트
			return "redirect:/user/sign_in_view";
		}

		
		Post post = postBO.getPostByPostIdAndUserId(postId, userId);
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_detail");
		
		return "/templete/layout";
	}
	
	

}
