package javass;

import java.io.Serializable;
import java.util.Date;

public class Board implements Comparable<Board> , Serializable 
{
	private int num;
	private java.sql.Date date;
	private String title;
	private String contents;
	
	public Board() {}

	
	public Board(int num, java.sql.Date date, String title, String contents) {
		super();
		this.num = num;
		this.date = date;
		this.title = title;
		this.contents = contents;
	}

	

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public java.sql.Date getDate() {
		return date;
	}


	public void setDate(java.sql.Date date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	@Override
	public int compareTo(Board o) {
		if(this.num>o.num) return 1;
		else if(this.num==o.num) return 0;
		else return -1;
		
	}


	@Override
	public boolean equals(Object obj) {
		Board other=(Board) obj;
		return this.num==other.num;
	}
	
	

	
	
}
