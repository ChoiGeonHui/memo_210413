package com.memo.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.memo.test.bo.TestBO;


@Controller
public class TestController {
	
	@Autowired
	TestBO testBO;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "Hello World!";
	}
	
	@RequestMapping("/test_db")
	@ResponseBody
	public Map<String, Object> testDB() {	
		Map<String, Object>	result =testBO.getTest();
		return result;
	}
	
	@RequestMapping("/test_jsp")
	public String testJsp() {
		return"test/test";
	}

}
