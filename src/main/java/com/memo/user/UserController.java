package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**ȭ�鸸 �����ϴ� ��Ʈ�ѷ�
 * @author 
 * 
 */


@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/sign_up");
		
		return "templete/layout";
	}
	

}
