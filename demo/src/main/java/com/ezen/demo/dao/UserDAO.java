package com.ezen.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.ezen.demo.vo.User;

@Component
public class UserDAO 
{
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
	
	public boolean loginCheck(User uvo)
	{
		getConn();
		
		try {
			String sql="SELECT * FROM EMP2 WHERE ename=? AND empno=?";
			this.pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uvo.getId().toUpperCase());
			//toUpperCase: 소문자 => 대문자로 만드는 문자열 
			pstmt.setString(2, uvo.getPw());
			this.rs=pstmt.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	
}
