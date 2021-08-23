package com.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.common.EncryptUtils;
import com.memo.user.bo.UserBO;

/**화면만 구성하는 컨트롤러
 * @author 
 * 
 */


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserBO userBO;
	
	
	
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/sign_up");
		
		return "templete/layout";
	}
	
	/**
	 * 회원가입 서브밋
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return 로그인화면으로 리다이렉트
	 */
	
	@RequestMapping("/signup")
	public String signUp(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email){
		
		//암호화
		String  enpPassword= EncryptUtils.md5(password);
		
		//DB insert
		userBO.addUser(loginId, enpPassword, name, email);
		
		return "redirect:/user/sign_in_view";
		//redirect는 @responseBody가 아닌@Controller에서 작동
	}
	
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/sign_in");
		return "templete/layout";
	}
	
	
	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userId");
		
		return "redirect:/user/sign_in_view";
	}
	

}
