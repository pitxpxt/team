package com.ezen.demo.controller;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.service.GuguService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController
{
	@Autowired
	private GuguService svc;  // Dependency Injection
	
	@GetMapping("/") 
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/gugu") 
	public String gugu(HttpServletRequest request)
	{
		String sDan = request.getParameter("dan");
		int dan = 2;
		if(sDan!=null) {
			dan = Integer.parseInt(sDan);
		}
		request.setAttribute("dan", dan);
		
		return "gugu";
	} 

	@GetMapping("/gugu2")
	public String gugu2(@RequestParam(value="dan", defaultValue="2" ) int dan, Model m)
	{
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	@GetMapping("/gugu3/{dan}")
	public String gugu3(@PathVariable int dan, Model m)
	{
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	// 다수개의 URL을 상대하는 메소드
	@GetMapping({"/gugu4/", "/gugu4/{dan}"})
	public String gugu4(@PathVariable("dan") Optional<Integer> opt, Model m)
	{
		int dan = 2;
		if(opt.isPresent()) {
			dan = opt.get();
		}
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	// 다수개의 PathVariable을 한개씩 받아 저장하는 예
	@GetMapping("/add/{a}/{b}")
	@ResponseBody
	public Map<String,String> add(@PathVariable("a") int a, @PathVariable("b") int b)
	{
		String msg = String.format("%d + %d = %d", a, b, a+b);
		Map<String,String> map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
	
	// 다수개의 PathVariable들을 한꺼번에 Map 자료구조에 저장하는 예
	@GetMapping("/mul/{a}/{b}")
	@ResponseBody
	public Map<String,String> mul(@PathVariable Map<String, String> map)
	{
		String sA = map.get("a");
		String sB = map.get("b");
		
		int a = Integer.parseInt(sA);
		int b = Integer.parseInt(sB);
		
		String msg = String.format("%d * %d = %d", a, b, a*b);
		
		Map<String,String> map2 = new HashMap<>();
		map2.put("msg", msg);
		return map2;
	}
}
