package com.ezen.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.ezen.demo.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Component

public class EmpDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn()
	{
		 try {
	         Class.forName("oracle.jdbc.OracleDriver"); 
	         conn = DriverManager.getConnection(
	                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER"); 
	       
	         this.conn=conn;
	         
	         return conn;
	         
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }
		return null;
	}
	
	private void closeAll()
	{
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(Emp emp)
	{
		getConn();
		
		
		try {
			String sql = "INSERT INTO EMP2 (empno, ename, deptno, sal, hiredate) VALUES (?,?,?,?,?)";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,emp.getEmpno());
			pstmt.setString(2,emp.getEname());
			pstmt.setInt(3,emp.getDeptno());
			pstmt.setInt(4,emp.getSal());
			pstmt.setDate(5,emp.getHiredate());
			
			if(pstmt.executeUpdate()>0)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public List<Emp> getList()
	{
		getConn();
		
		
		try {
			String sql="SELECT * FROM EMP2 ";
			this.pstmt=conn.prepareStatement(sql);
			this.rs=pstmt.executeQuery();
			
			List<Emp> list = new ArrayList<>();
			while(rs.next())
			{
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setSal(rs.getInt("sal"));
				emp.setHiredate(rs.getDate("hiredate"));
				
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public Emp getEmp(int empno)
	{
		getConn();
		
		
		try {
			String sql="SELECT * FROM EMP2 WHERE EMPNO= ?";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			this.rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setSal(rs.getInt("sal"));
				emp.setHiredate(rs.getDate("hiredate"));
		
				return emp;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public boolean update(Emp emp)
	{
		getConn();
		
		
		try {
			String sql = "UPDATE EMP2 SET SAL= ? WHERE EMPNO=?";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,emp.getSal());
			pstmt.setInt(2,emp.getEmpno());
	
			if(pstmt.executeUpdate()>0)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public boolean delete(int empno)
	{
		getConn();
		
		
		try {
			String sql = "DELETE FROM EMP2 WHERE EMPNO=?";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,empno);
	
			if(pstmt.executeUpdate()>0)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public Emp find(String ename)
	{
		getConn();
		
		try {
			String sql="SELECT * FROM EMP2 WHERE ENAME=?";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			this.rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setSal(rs.getInt("sal"));
				emp.setHiredate(rs.getDate("hiredate"));
				
				return emp;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
}
