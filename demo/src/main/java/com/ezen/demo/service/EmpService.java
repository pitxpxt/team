package com.ezen.demo.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.demo.vo.Emp;

@Service
public class EmpService 
{
	private String fpath = "D:\\Java_Test\\file.ser";

	public boolean serialize(List<Emp> list)
	{
		
		try {
			FileOutputStream fos = new FileOutputStream(fpath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Emp> deserialize()
	{
		
		List<Emp> list = null;
		try {
			File f = new File(fpath);
		if(!f.exists())
		{
			list=new ArrayList<>();
		}else {
		
				ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
				list = (List<Emp>)oin.readObject();
				oin.close();
		}
			return list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return null;

	}
	
	public boolean add(Emp emp)
	{
		List<Emp> list = deserialize();
		/*int empno = 1;
		if(list.size()>0)
			empno=list.get(list.size()-1).getEmpno()+1;
		emp.setEmpno(empno);*/
		list.add(emp);
		return serialize(list);
	}
	public boolean delete(int empno)
	{
		List<Emp> list = getList();
		Emp emp = new Emp();
		emp.setEmpno(empno);
		
		if(list.contains(emp))
		{
			list.remove(emp);
			return serialize(list);
		}
		return false;
	}
	
	public boolean update(Emp emp)
	{
		List<Emp> list = getList();
		if(list.contains(emp))
		{
			Emp newEmp = list.get(list.indexOf(emp));
			newEmp.setSal(emp.getSal());
			return serialize(list);
		}
		return false;
	}
	
	public List<Emp> getList()
	{
		List<Emp> emp =  deserialize();
		Collections.sort(emp);
		return emp;
	}
	
	public Emp getEmp(int empno)
	{
		List<Emp> list = getList();
		Emp emp = new Emp();
		emp.setEmpno(empno);
		if(list.contains(emp))
		{
			return list.get(list.indexOf(emp));
		}
		return null;
	}
	
	/*public List<Emp> findList(String ename)
	{
		List<Emp> list = getList();
		List<Emp> list2 = new ArrayList<>();
		for(int i=0 ;i<list.size(); i++)
		{
			if((list.get(i).getEname()).equals(ename))
			{
				list2.add(list.get(i));
			}
			if(list2.size()==0) list2=null;
			return list2;
			
		}

		return null;
	}*/
	
	
	/*@Override
	public boolean equals(Object obj) {
		Emp emp = new Emp();
		Emp emp2=(Emp) obj;
		if(this.ename.equals(emp2.getEname())) {
			return true;
		}
		return false;
	}*/
	
	public Emp findList(String ename)
	{
		List<Emp> list = getList();
		Emp emp = new Emp();
		emp.setEname(ename);
		if(list.contains(emp))
		{
			return list.get(list.indexOf(emp));
		}
		return null;
	}


}
