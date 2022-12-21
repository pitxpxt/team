package com.ezen.demo.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.service.DaoService;
import com.ezen.demo.service.UserService;
import com.ezen.demo.vo.Emp;
import com.ezen.demo.vo.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@SessionAttributes("id")
@Slf4j
public class UserController 
{

	@Autowired
	private UserService usv;
	
	@Autowired
	private DaoService dao;
	
	@GetMapping("/form")
	public String showLoginForm()
	{
		return "/login/login";
	}
	
	@PostMapping("/logon")
	@ResponseBody
	public Map<String,Boolean> login(User user,Model m)
	{
		boolean ok = usv.loginC(user);
		if(ok) {
			m.addAttribute("id", user.getId());
			log.info("로그인성공");
		}
		Map<String,Boolean> map = new HashMap<>();
		map.put("login", ok);
		return map;
		
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public Map<String,Boolean> logout(SessionStatus status)
	{
		status.setComplete();
		boolean logout = status.isComplete();
		Map<String,Boolean> map = new HashMap<>();
		map.put("logout", logout);
		return map;
	}
	
	@GetMapping("/test")
	@ResponseBody
	public Map<String,String> isLogged(@SessionAttribute(name="id", required=false) String userid)
	{
	
		Map<String,String> map = new HashMap<>();
		map.put("id", userid);
		return map;
	}

	/*
	@PostMapping("/delete")
	@ResponseBody
	public Map<String,Boolean> delete(int empno)
	{
	
			Map<String,Boolean> map = new HashMap<>();
			boolean deleted = esv.delete(empno);
			map.put("deleted", deleted);
			return map;
			
	}
	*/
	@PostMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(int empno,@SessionAttribute(name="id", required=false) String userid)
	{
		Map<String,Object> map = usv.loginstat(userid, "deleted");
		if(map.containsKey("msg")) return map;
	
				boolean deleted = dao.delete(empno);
				map.put("deleted", deleted);
				return map;	
	}
	@PostMapping("/add")
	@ResponseBody
	public Map<String,Object> delete(Emp emp,@SessionAttribute(name="id", required=false) String userid)
	{
		Map<String,Object> map = usv.loginstat(userid, "added");
		if(map.containsKey("msg")) return map;
	
				boolean added = dao.add(emp);
				map.put("added", added);
				return map;	
	}
	@PostMapping("/detailEdit")
	@ResponseBody
	public Map<String,Object> update(Emp emp,@SessionAttribute(name="id", required=false) String userid)
	{
		Map<String,Object> map = usv.loginstat(userid, "updated");
		if(map.containsKey("msg")) return map;
	
				boolean updated = dao.update(emp);
				map.put("updated", updated);
				return map;	
	}
}
