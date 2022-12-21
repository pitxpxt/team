package com.ezen.demo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.Emp;

@Repository
public class JdbcTemDAO 
{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public List<Emp> getList()
	{
	String sql="SELECT * FROM emp2";

	List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		
	Emp emp = new Emp();
	emp.setEmpno(rs.getInt("EMPNO"));
	emp.setEname(rs.getString("ENAME"));
	emp.setDeptno(rs.getInt("DEPTNO"));
	emp.setSal(rs.getInt("SAL"));
	emp.setHiredate(rs.getDate("HIREDATE"));

	return  emp;
	});
	
	return list;

	}
	
	public List<Emp> getDeptList(int deptno)
	{
	String sql="SELECT * FROM emp2 WHERE DEPTNO=?";

	List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		
	Emp emp = new Emp();
	emp.setEmpno(rs.getInt("EMPNO"));
	emp.setEname(rs.getString("ENAME"));
	emp.setDeptno(rs.getInt("DEPTNO"));
	emp.setSal(rs.getInt("SAL"));
	emp.setHiredate(rs.getDate("HIREDATE"));

	return  emp;
	}, deptno);
	
	return list;

	}
	
	public List<Emp> getEmp(int empno)
	{
	String sql="SELECT * FROM emp2 WHERE EMPNO=?";

	List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		
	Emp emp = new Emp();
	emp.setEmpno(rs.getInt("EMPNO"));
	emp.setEname(rs.getString("ENAME"));
	emp.setDeptno(rs.getInt("DEPTNO"));
	emp.setSal(rs.getInt("SAL"));
	emp.setHiredate(rs.getDate("HIREDATE"));

	return  emp;
	}, empno);
	
	return list;

	}
	
	public boolean add(Emp emp)
	{
		String sql="INSERT INTO EMP2 (empno, ename, deptno, sal, hiredate) VALUES (?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql,emp.getEmpno(),emp.getEname(),emp.getDeptno(),
				emp.getSal(),emp.getHiredate());
		
		if(rows>0)
		{
			return true;
		}
		return false;
		
	}
	
	
	public boolean delete(int empno)
	{
		String sql="DELETE FROM EMP2 WHERE EMPNO=?";
		int rows = jdbcTemplate.update(sql,empno);
		
		if(rows>0)
		{
			return true;
		}
		return false;
		
	}
	
	
	public boolean update(Emp emp)
	{
		String sql="UPDATE EMP2 SET SAL= ? WHERE EMPNO=?";
		int rows = jdbcTemplate.update(sql,emp.getSal(),emp.getEmpno());
		
		if(rows>0)
		{
			return true;
		}
		return false;
		
	}
	

	public boolean addAndGetPK(Emp emp){

		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		int row = jdbcTemplate.update((conn)->{
		PreparedStatement pstmt;
		String sql="INSERT INTO EMP2(empno,ename,deptno,sal,hiredate)"+
					"VALUES(EMP_SEQ.NEXTVAL,?,?,?,?)";
		pstmt = conn.prepareStatement (sql, new String[]{"empno"});
		pstmt.setInt(1,emp.getEmpno());
		pstmt.setString(2,emp.getEname());
		pstmt.setInt(3, emp.getDeptno());
		pstmt.setInt(4, emp.getSal());
		pstmt.setDate(5, emp.getHiredate());
		return pstmt;
		},kh);
		
		return row>0;

		}
	
	public List<Emp> find(String ename)
	{
		String sql="SELECT * FROM EMP2 WHERE ENAME=?";
		List<Emp> list= jdbcTemplate.query(sql,(rs,i)->{
	
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setDeptno(rs.getInt("DEPTNO"));
			emp.setSal(rs.getInt("SAL"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			
			return emp;
			
		}, ename);
		return list;
	}


}
