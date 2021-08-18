package com.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.dao.UserDAO;
import com.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	UserDAO userDAO;
	
	public User getUserByid(String loginId) {
		return userDAO.selectUserByLoginId(loginId);
	}
	
	public User getUserByIdPassword(String loginId, String password) {
		return userDAO.getUserByLoginId(loginId, password);
	}
	
	public void addUser(String loginId, String password,
			String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}

}
