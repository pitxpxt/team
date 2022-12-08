package tDiary;

import java.io.*;
import java.sql.Date;

public class Diary implements Serializable 
{
	private java.sql.Date date;
	private String contents;
	
	public Diary() {}
	
	public Diary(Date date, String contents) {
		this.date = date;
		this.contents = contents;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	

}
