package com.xms.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.dao.UserMapper;
import com.xms.entity.User;

@Controller
@RequestMapping("/registe")
public class RegisteController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/toRegiste")
	public String toRegiste() {
		return "jsp/register";
	}
	
	@RequestMapping("/validateEmail")
	@ResponseBody
	public boolean validateEmail(String email) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		User user = userMapper.findUserByEmail(email);
		
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/createCode")
	@ResponseBody
	public boolean createCode(HttpServletRequest request) {
		
		String [] numbers = {"0","1","2","3","4","5","6","7","8","9"};
		
		Random random = new Random();
		StringBuffer sb = new StringBuffer(); 
		for(int i=0;i<6;i++) {
			sb.append(numbers[random.nextInt(numbers.length)]);
		}
		
		System.out.println(sb.toString());
		
		request.getSession().setAttribute("code",sb.toString());
		
		return true;
		
	}
	
	@RequestMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(String code,HttpServletRequest request) {
		
		if (code.equals(request.getSession().getAttribute("code"))) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@RequestMapping("/registe")
	public String registe(User user,HttpServletRequest request) {
		
//		user.setRegistedate(new Timestamp(System.currentTimeMillis()));
		
		userMapper.save(user);
		
		request.getSession().setAttribute("user",user);
		
		return "redirect:/main/toIndex";
		
	}
	
}
