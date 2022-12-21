package com.ezen.demo.controller;

import java.util.ArrayList;



import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.dao.JdbcTemDAO;
import com.ezen.demo.service.EmpService;
import com.ezen.demo.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController 
{

	@Autowired
	private EmpService esv;

	
	@GetMapping("/add")
	public String showAddForm()
	{
		log.info("폼 요청함");
		return "/emp/Addform";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String,Object> addEmp(Emp emp)           //여기서 그냥 넣어줌. 단, VO에 선언해준 변수명과 폼의 name명이 같아야 함. 
	{

		boolean added = esv.add(emp);
		Map<String,Object> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	@PostMapping("/find")
	public String findList(String ename,Model m)           
	{
		log.info("ggg");
		m.addAttribute("list",esv.findList(ename));
		return "/emp/findList";
	}
	
	@GetMapping("/list")
	public String list(Model m)
	{
		
		m.addAttribute("list", esv.getList());
		return "/emp/list";
	}
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable(name="num") int num, Model m)
	{
		
		m.addAttribute("emp", esv.getEmp(num));
		return "/emp/detail";
	}
	@PostMapping("/delete")
	@ResponseBody
	public Map<String,Object> deleteEmp(int num)            
	{

		boolean deleted = esv.delete(num);
		Map<String,Object> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}
	@GetMapping("/detailEdit/{empno}")
	public String showEditForm(@PathVariable(name="empno") int empno, Model m)
	{
		log.info("폼 요청함");
		m.addAttribute("emp",esv.getEmp(empno));
		return "/emp/detailEdit";
	}
	@PostMapping("/detailEdit")
	@ResponseBody
	public Map<String,Object> UpdateEmp(Emp emp)           //여기서 그냥 넣어줌. 단, VO에 선언해준 변수명과 폼의 name명이 같아야 함. 
	{ 

		boolean updated = esv.update(emp); 
		Map<String,Object> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}

}
