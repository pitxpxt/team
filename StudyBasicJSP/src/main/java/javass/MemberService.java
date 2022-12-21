package javass;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class MemberService 
{
	private HttpServletRequest request;
	private Member member;
	
	private String fpath = "C:/codetest/member.ser";
	
	public MemberService() {}
	
	public MemberService(HttpServletRequest request)
	{
		this.request= request;
	}
	
	public void setMember(Member member)
	{
		this.member = member;
	}
	
	public boolean addMember()
	{
		List<Member> list = deserialize();
		Collections.sort(list);
		int num = 1;
		num = list.get(list.size()-1).getNum()+1;
		member.setNum(num);
		list.add(member);
		return serialize(list);
	}
	
	private boolean serialize(List<Member> list)
	{
		File f = new File(fpath);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(list);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}	return false;
	}
	private List<Member> deserialize()
	{
		File f = new File(fpath);
		List<Member> list = null;
		
		if(!f.exists()) {
			list = new ArrayList<Member>();
		}else
		{
			try {
				ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
				list = (List<Member>)oin.readObject();
				oin.close();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
		}
		
		for(int i=0 ;i<list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		
		return list;
	}
	
}
