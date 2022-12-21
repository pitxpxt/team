package com.ezen.demo.controller;

import java.sql.*;
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

import com.ezen.demo.service.DaoService;
import com.ezen.demo.service.EmpService;
import com.ezen.demo.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController 
{
	@Autowired
	private DaoService dsc;
	
   private Connection conn;
   private Statement stmt;
   private ResultSet rs;
   
   @GetMapping("/oracle")
   public void test()
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
         
         while(rs.next())
         {
            int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            float salary = rs.getFloat("SAL");
            
            System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,salary);
         }
         
         rs.close();
         stmt.close();
         conn.close();
         
        } catch (Exception e) {
            e.printStackTrace();;
        }
   }
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

		boolean added = dsc.add(emp);
		Map<String,Object> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	@GetMapping("/list")
	public String list(Model m)
	{
		
		m.addAttribute("list", dsc.getList());
		return "/emp/list";
	}
	@GetMapping("/deptlist/{deptno}")
	public String deptlist(Model m,@PathVariable (name="deptno")int deptno)
	{
		
		m.addAttribute("list", dsc.getDeptList(deptno));
		return "/emp/list";
	}
	@GetMapping("/detail/{empno}")
	public String detail(@PathVariable(name="empno") int empno, Model m)
	{
		
		m.addAttribute("list", dsc.getEmp(empno));
		return "/emp/detail";
	}
	@GetMapping("/detailEdit/{empno}")
	public String showEditForm(@PathVariable(name="empno") int empno, Model m)
	{
		log.info("폼 요청함");
		m.addAttribute("emp",dsc.getEmp(empno));
		return "/emp/detailEdit";
	}
	@PostMapping("/detailEdit")
	@ResponseBody
	public Map<String,Object> UpdateEmp(Emp emp)           //여기서 그냥 넣어줌. 단, VO에 선언해준 변수명과 폼의 name명이 같아야 함. 
	{ 

		boolean updated = dsc.update(emp); 
		Map<String,Object> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	
	@PostMapping("/find")
	public String findByEname(String ename,Model m)         
	{
		m.addAttribute("list",dsc.findEmp(ename));
		return "/emp/findList";
	
	}
	@PostMapping("/delete")
	@ResponseBody
	public Map<String,Object> DeleteEmp(int empno)           //여기서 그냥 넣어줌. 단, VO에 선언해준 변수명과 폼의 name명이 같아야 함. 
	{ 

		boolean deleted = dsc.delete(empno); 
		Map<String,Object> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}

   
   
}