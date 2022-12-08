package javass;

import java.io.Serializable;

public class Member implements Serializable, Comparable<Member>
{
	private int num;
	private String name;
	private String gender;
	private String email;
	private int careerT;
	private String[] subject;
	private String introduce;
	
	public Member() {}
	
	public Member(int num, String name, String gender, String email, int careerT, String[] subject, String introduce) {
		super();
		this.num = num;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.careerT = careerT;
		this.subject = subject;
		this.introduce = introduce;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		Member other =(Member) obj;
		return this.num==other.num;
	}

	
	@Override
	public int compareTo(Member o) {
		if(this.num>o.num) return 1;
		else if(this.num==o.num) return 0;
		else return -1;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCareerT() {
		return careerT;
	}

	public void setCareerT(int careerT) {
		this.careerT = careerT;
	}

	public String[] getSubject() {
		return subject;
	}

	public void setSubject(String[] subject) {
		this.subject = subject;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
	
}
