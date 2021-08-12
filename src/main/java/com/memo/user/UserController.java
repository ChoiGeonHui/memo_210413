package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**화면만 구성하는 컨트롤러
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
