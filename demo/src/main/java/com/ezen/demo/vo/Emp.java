package com.ezen.demo.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude = {"ename","deptno","sal","hiredate"})
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable, Comparable<Emp>
{
	private int empno;
	private String ename;
	private int deptno;
	private int sal;
	private Date hiredate;
	
	@Override
	public int compareTo(Emp o) {
		
		if(this.empno>o.empno) return 1;
		else if (this.empno==o.empno) return 0;
		else return -1;
	}

}
