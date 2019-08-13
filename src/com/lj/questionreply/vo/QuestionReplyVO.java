package com.lj.questionreply.vo;

import java.sql.Timestamp;

public class QuestionReplyVO {

	private int rq_no;
	private int q_no;
	private String id;
	private String content;
	private Timestamp reg_date;
	
	public QuestionReplyVO() {
	}

	public QuestionReplyVO(int rq_no, int q_no, String id, String content, Timestamp reg_date) {
		this.rq_no = rq_no;
		this.q_no = q_no;
		this.id = id;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getRq_no() {
		return rq_no;
	}

	public void setRq_no(int rq_no) {
		this.rq_no = rq_no;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
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

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "QuestionReplyVO [rq_no=" + rq_no + ", q_no=" + q_no + ", id=" + id + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}


	
}