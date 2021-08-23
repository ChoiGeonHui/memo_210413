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

/**ȭ�鸸 �����ϴ� ��Ʈ�ѷ�
 * @author 
 * 
 */


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserBO userBO;
	
	
	
	/**
	 * ȸ������ ȭ��
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/sign_up");
		
		return "templete/layout";
	}
	
	/**
	 * ȸ������ �����
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return �α���ȭ������ �����̷�Ʈ
	 */
	
	@RequestMapping("/signup")
	public String signUp(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email){
		
		//��ȣȭ
		String  enpPassword= EncryptUtils.md5(password);
		
		//DB insert
		userBO.addUser(loginId, enpPassword, name, email);
		
		return "redirect:/user/sign_in_view";
		//redirect�� @responseBody�� �ƴ�@Controller���� �۵�
	}
	
	
	/**
	 * �α��� ȭ��
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/sign_in");
		return "templete/layout";
	}
	
	
	/**
	 * �α׾ƿ�
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
