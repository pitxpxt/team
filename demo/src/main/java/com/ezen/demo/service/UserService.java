package com.ezen.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.demo.dao.UserDAO;
import com.ezen.demo.vo.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService 
{

	@Autowired
	private UserDAO dao;
	
	//@Autowired
	//private HttpSession session;
	
	//@Autowired
	/*public void setSession(HttpSession session)
	{
		log.info(null);
		this.session=session;
	}*/ //setter injection 
	
	//위 둘 중 어떤 걸 써도 상관 없음.
	
	public boolean loginC(User user)
	{
		boolean ok = dao.loginCheck(user);
		//session.setAttribute("userid", user.getId());
		return ok;
	}
	
	public Map<String,Object> loginstat(String userid,String key)
	{
		Map<String,Object> map = new HashMap<>();
		if(userid==null)
		{
			map.put(key,false);
			map.put("msg", "로그인하셔야 이용가능한 기능입니다.");
			map.put("url","/user/form");
			
		}
		return map;
	}
	
	/*
	public boolean loginstat(String userid)
	{
		if(userid==null)
		{
			return false;
		}
		return true;
	}
	*/
}
