package com.ezen.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.EmpDAO;
import com.ezen.demo.dao.JdbcTemDAO;
import com.ezen.demo.vo.Emp;

@Service
public class DaoService 
{
	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private JdbcTemDAO jtd;

	public boolean add(Emp emp)
	{
		return jtd.add(emp);
	}
	public List<Emp> getList()
	{
		return jtd.getList();
	}
	public List<Emp> getDeptList(int deptno)
	{
		return jtd.getDeptList(deptno);
	}
	public List<Emp> getEmp(int empno)
	{
		return jtd.getEmp(empno);
	}
	public boolean update(Emp emp)
	{
		return jtd.update(emp);
	}
	public boolean delete(int empno)
	{
		return jtd.delete(empno);
	}
	public List<Emp> findEmp(String ename)
	{
		return jtd.find(ename);
	}
	
}
