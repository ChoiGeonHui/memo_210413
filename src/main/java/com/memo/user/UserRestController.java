package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.bo.UserBO;
import com.memo.user.model.User;

/**
 * 데이터만 처리하는 API용 컨트롤러
 * @author
 *
 */
@RequestMapping("/user")
@RestController //@Controller +@ResponsBody
public class UserRestController {
	
	@Autowired
	UserBO userBO;
	
	@RequestMapping("/is_dulicated_id")
	public Map<String, Boolean> isDulicatedId(
			@RequestParam("loginId")String loginId){
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		User user = userBO.getUserByid(loginId);
		if(user==null) {
			result.put("result", false);
		}else {
			result.put("result", true);
		}
		return result;
	}
	
	@PostMapping("/signup_forajax")
	public Map<String, String> signupforajax(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email
			){
		
		//암호화
		String enpPassword= EncryptUtils.md5(password);
		
		//insert DB
		userBO.addUser(loginId, enpPassword, name, email);
	
		Map<String, String> result= new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			HttpServletRequest request){
		
		//password를 md5로 해싱한다.
		String encrpytPassword = EncryptUtils.md5(password);
		//loginId, password로user를 가져와서 있으면 로그인 성공
		User user = userBO.getUserByIdPassword(loginId, encrpytPassword);		
		Map<String, String> result = new HashMap<>();
		//성공 : 세션에 저장(로그인 상태 유지)
		if(user !=null) {		
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userId", user.getId());
			result.put("result", "success");
		
		
		}
		//실패 : 에러 리턴
		else {	
			result.put("result", "fail");
			
		}
		
		return result;
	}
	
	

}
