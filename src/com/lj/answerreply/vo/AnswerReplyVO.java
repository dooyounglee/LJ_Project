package com.lj.answerreply.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class AnswerReplyVO {

	private int ra_no;
	private int a_no;
	private String id;
	private String content;
	private String reg_date;

	public AnswerReplyVO() {
		super();
	}

	public AnswerReplyVO(int ra_no, int a_no, String id, String content, String reg_date) {
		this.ra_no = ra_no;
		this.a_no = a_no;
		this.id = id;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getRa_no() {
		return ra_no;
	}

	public void setRa_no(int ra_no) {
		this.ra_no = ra_no;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "AnswerReplyVO [ra_no=" + ra_no + ", a_no=" + a_no + ", id=" + id + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}



	
}