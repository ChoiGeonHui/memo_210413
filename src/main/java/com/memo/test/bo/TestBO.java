package com.memo.test.bo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.test.dao.TestDAO;

@Service
public class TestBO {
	
	@Autowired
	TestDAO testDAO;
	
	public Map<String, Object> getTest(){
		return testDAO.selectUser();
	}

}
