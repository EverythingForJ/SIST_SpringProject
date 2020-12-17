package com.example.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BbsVO {
	private int bno;
	private String title;
	private String content;
	private String email;
	private String writeday;
	private int readnum;
	private String userid;
	private String writer;
}
