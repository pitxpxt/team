package com.ezen.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor //
@NoArgsConstructor //기본 생성자
public class User 
{

	private String id;
	private String pw;
}
