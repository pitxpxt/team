package javass;

import java.io.*;
import java.util.*;

public class BoardService 
{
	private String fpath = "C:/codetest/board.ser";
	private Board board;
	
	public Board getBoard()
	{
		return board;
	}
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	public boolean addBoard()
	{	
		List<Board> list = deserialize();
		Collections.sort(list);
		
		int num = 1;
		if(list.size()>0)
		{
			num=list.get(list.size()-1).getNum()+1;
		}
		
		board.setNum(num);
		list.add(board);
		
		return serialize(list);
	}
	
	private boolean serialize(List<Board> list)
	{
		File f = new File(fpath);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(list);
			oos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	private List<Board> deserialize()
	{
		File f = new File(fpath);
		List<Board> list = null;
		
		if(!f.exists())
		{
			list = new ArrayList<Board>();
		}else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				list = (List<Board>) ois.readObject();
				ois.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	return list;
	}
	
	public List<Board> getList()
	{
		return deserialize();
	}
	
	public Board getBoard(Board key)
	{
		List<Board> list = getList();
		if(list.contains(key))
		{
			return list.get(list.indexOf(key));
		}
		System.out.println("링크 가지고 오기 실패");
		return null;
		
	}

}
